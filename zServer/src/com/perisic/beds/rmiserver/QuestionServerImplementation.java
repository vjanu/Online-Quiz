package com.perisic.beds.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.perisic.beds.rmiinterface.Question;
import com.perisic.beds.rmiinterface.RemoteQuestions;

/**
 * Implementation of the questionnaire. Note that chosen answers are collected in this
 * object as well. That means that if the object is destroyed, for instance server restart
 * the collected data is all gone. 
 * To do: make data persistent, for instance link collected data to a database or save data
 * in a text file.  
 * @author Marc Conrad
 *
 */
public class QuestionServerImplementation 
extends UnicastRemoteObject implements RemoteQuestions{
	private static final long serialVersionUID = -3763231206310491048L;
	Vector<Question> myQuestions = new Vector<Question>(); 
	/**
	 * All questions and answers are initialised in the constructor of this class. 
	 * To do: read questions and options from an external data file. 
	 * @throws RemoteException
	 */
	QuestionServerImplementation() throws RemoteException {
		super();
		System.out.println("QuestionServerImplementation Created");
		
		Map<String, List<String>> QnAMap = getQnA();
		int count = QnAMap.size();
		
	
			for (Map.Entry<String, List<String>> entry : QnAMap.entrySet())
			{
				String[] answers1 = entry.getValue().toArray(new String[QnAMap.size()]); 
				Question question1 = new Question(entry.getKey(), answers1 ); 
				myQuestions.add(question1); 
			}
	
	}

	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public int getNumberOfQuestions() throws RemoteException {
		
		int size = myQuestions.size();
		return size;
	}
	/**
	 * Implementation of remote interface method. 
	 */
	@Override
	public Question getQuestion(int i) throws RemoteException {
		return myQuestions.elementAt(i);
	}
	/**
	 * Implementation of remote interface method. 
	 */	
	@Override
	public void submitAnswer(int i, String answer) throws RemoteException {
		myQuestions.elementAt(i).addAnswer(answer);
	}
	/**
	 * Implementation of remote interface method. 
	 */	
	@Override
	public Vector<Question> getData() { 
		return myQuestions; 
	}
	
	@Override
	public boolean insertQnA(int id, String category, String question, String answer1, String answer2, String answer3, String answer4, String Canswer) throws RemoteException{
	          String query = "INSERT INTO qna(id, category, question, answer1, answer2, answer3, answer4, correctanswer) VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
		try{
			PreparedStatement preparedStmt = dbConnect.getConn(query);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, category);
			preparedStmt.setString(3, question);
			preparedStmt.setString(4, answer1);
			preparedStmt.setString(5, answer2);
			preparedStmt.setString(6, answer3);
			preparedStmt.setString(7, answer4);
			preparedStmt.setString(8, Canswer);
		
			preparedStmt.executeUpdate();
			
        } catch(SQLException ex){
            ex.printStackTrace();
            
            
        }finally{
            try{
                dbConnect.getConnection().close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
		
		return true;
	}
	@Override
     public Map<String, List<String>> getQnA() throws RemoteException{
	    Map<String, List<String>> qna = new HashMap<String, List<String>>();
	    
	    String question;
	    String ans1; String ans2; String ans3; String ans4;
	    
		String query = "SELECT question, answer1, answer2, answer3, answer4 FROM qna";
		
		try{
			
            
            ResultSet rs = dbConnect.getConnection().executeQuery(query);
			
			
			while(rs.next()){
				List<String> ans = new ArrayList<String>();
				question = rs.getString("question");
				ans1 = rs.getString("answer1");
				ans2 = rs.getString("answer2");
				ans3 = rs.getString("answer3");
				ans4 = rs.getString("answer4");
				ans.add(ans1);
				ans.add(ans2);
				ans.add(ans3);
				ans.add(ans4);
				qna.put(question, ans);
				
			}
			
		
		
            
        } catch(SQLException ex){
        	
            ex.printStackTrace();
            
            
        }finally{
        	
            try{
                dbConnect.getConnection().close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		
		return qna;
	}

	@Override
	public boolean insertAnswers(int id, String username, String question, String answer, int mark) throws RemoteException {
		  String query = "INSERT INTO answers(id, username, question, answer, mark) VALUES(?, ?, ?, ?, ?) ";
			try{
				PreparedStatement preparedStmt = dbConnect.getConn(query);
				preparedStmt.setInt(1, id);
				preparedStmt.setString(2, username);
				preparedStmt.setString(3, question);
				preparedStmt.setString(4, answer);
				preparedStmt.setInt(5, mark);
			
				preparedStmt.executeUpdate();
				
	        } catch(SQLException ex){
	            ex.printStackTrace();
	            
	            
	        }finally{
	            try{
	                dbConnect.getConnection().close();
	                
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                return false;
	            }
	        }
			
			return true;
	}
	@Override
	 public String getAnswer(String question) throws RemoteException{
		    
		    String answer = "";
		    
			String query = "SELECT correctanswer FROM qna where question='"+question+"'";
			
			try{
				
	            
	            ResultSet rs = dbConnect.getConnection().executeQuery(query);
				
				
				if(rs.next()){
					
					answer = rs.getString("correctanswer");
					
					
				}
				
			
			
	            
	        } catch(SQLException ex){
	        	
	            ex.printStackTrace();
	            
	            
	        }finally{
	        	
	            try{
	                dbConnect.getConnection().close();
	                
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
			
			return answer;
		}
	@Override
	 public Map<String, Integer> getFinalResults() throws RemoteException{
		    Map<String, Integer> studMap = new HashMap();
		    int score;
		    String user="";
		    
			String query = "SELECT sum(mark) as score, username FROM answers group by username";
			
			try{
				
	            
	            ResultSet rs = dbConnect.getConnection().executeQuery(query);
				
				
				while(rs.next()){
					
					score = rs.getInt("score");
					user = rs.getString("username");
					studMap.put(user, score);
					
					
				}
				
			
			
	            
	        } catch(SQLException ex){
	        	
	            ex.printStackTrace();
	            
	            
	        }finally{
	        	
	            try{
	                dbConnect.getConnection().close();
	                
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
			
			return studMap;
		}
}
