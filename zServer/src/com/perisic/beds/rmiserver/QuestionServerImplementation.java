package com.perisic.beds.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		
		String[] answers1 = {"1) Frictional force", "2) Tension force", "3) Air Resistance","4) Gravitational Force" }; 
		Question question1 = new Question("1. Which force is created by contacting and sliding two surfaces with each other?", answers1 ); 
		myQuestions.add(question1); 

		String[] answers2 = {"1) 20%", "2) 50%", "3) 100%","4) 70%" }; 
		Question question2 = new Question("2. When total internal reflection occurs from a prism, ____ of incident light is reflected back to denser \n medium. Fill on the blank.", answers2 );
		myQuestions.add(question2); 
		
		String[] answers3 = {"1) To detect electromagnetic waves", "2) To measure electrostatic charge", "3) To detect gamma rays and beta particles","4) To measure electric current" }; 
		Question question3 = new Question("3. What is Geiger counter used for?", answers3 );
		myQuestions.add(question3); 

		String[] answers4 = {"1) Liver", "2) Small Intestine", "3) Kidneys","4) Pancreas" }; 
		Question question4 = new Question("4. Which organ produces insulin in the body?", answers4 );
		myQuestions.add(question4); 
		
		String[] answers5 = {"1) Vein", "2) Artery", "3) Capillary","4) Nerve" }; 
		Question question5 = new Question("5. Which of the following is a large blood vessel that carries blood away from the heart?", answers5 );
		myQuestions.add(question5); 
		
		String[] answers6 = {"1) Clavicle", "2) Scapula", "3) Thoracic","4) Humerus" }; 
		Question question6 = new Question("6. In anatomy, what is the name given for wing bone in the human skeleton?", answers6 );
		myQuestions.add(question6); 
		
		String[] answers7 = {"1) Carbon", "2) Manganese", "3) Mercury","4) Calcium" }; 
		Question question7 = new Question("7. Which element does not conduct electricity?", answers7 );
		myQuestions.add(question7); 
		
		String[] answers8 = {"1) Oxygen", "2) Hydrogen sulphide", "3) Carbon dioxide","4) Nitrogen" }; 
		Question question8 = new Question("8. Brass gets discoloured in air because of the presence of which of the following gases in air?", answers8 );
		myQuestions.add(question8); 
		
		String[] answers9 = {"1) Graphite", "2) Silicon", "3) Charcoal","4) Phosphorous" }; 
		Question question9 = new Question("9. Which of the following is used in pencils?", answers9 );
		myQuestions.add(question9); 
		
		String[] answers10 = {"1) NaAlO2", "2) H2O", "3) NaCl","4) CaSiO3" }; 
		Question question10 = new Question("10. Chemical formula for salt is?", answers10 );
		myQuestions.add(question10); 
		
		String[] answers11 = {"1) India", "2) England", "3) Sri Lanka","4) Australia" }; 
		Question question11 = new Question("11. Which team has won the most world cups in cricket?", answers11 );
		myQuestions.add(question11); 
				
		String[] answers12 = {"1) Ricky ponting", "2) Bryan Lara", "3) Sachin Tendulkar","4) Virat Kholi" }; 
		Question question12 = new Question("12. Who is the leading run scorer in ODI cricket?", answers12 );
		myQuestions.add(question12); 
				
		String[] answers13 = {"1) Germany", "2) Argentina", "3) France","4) Brazil" }; 
		Question question13 = new Question("13. Which team has won the most world cups in football?", answers13 );
		myQuestions.add(question13);
				
		String[] answers14 = {"1) Cristiano Ronaldo", "2) Lionel Messi", "3) Kaka","4) Archie Thompson" }; 
		Question question14 = new Question("14. Who has scored the highest number of goals in one match?", answers14 );
		myQuestions.add(question14);
				
		String[] answers15 = {"1) Guillermo Vilas", "2) Rafael Nadal", "3) Ivan Lendi","4) Thomas Muster" }; 
		Question question15 = new Question("15. Who has the most clay court titles in Tennis?", answers15 );
		myQuestions.add(question15);
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
	public boolean insertAnswers(int id, String userName, String question, String answer) throws RemoteException {
		String query = "INSERT INTO Answers(id, username, question, answer) VALUES(?, ?, ?, ?) ";
		try{
			PreparedStatement preparedStmt = dbConnect.getConn(query);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, question);
			preparedStmt.setString(4, answer);
		
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

}
