package com.perisic.beds.rmiinterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
/**
 * RMI interface to enable to retrieve questions from the server and to submit data
 * to the server. 
 * @author Marc Conrad
 *
 */
public interface RemoteQuestions extends Remote {
	/**
	 * Number of questions on the server.
	 * @return
	 * @throws RemoteException
	 */
	public int getNumberOfQuestions() throws RemoteException; 
	/**
	 * Retrieve specific question from the server. 
	 * @param i number of the question. 
	 * @return the Question. 
	 * @throws RemoteException
	 */
	public Question getQuestion(int i) throws RemoteException; 
	/**
	 * Submit the answer to the question number i.
	 * @param i question where the answer belongs to.
	 * @param answer the answer given to this question. 
	 * @throws RemoteException
	 */
	void submitAnswer(int i, String answer) throws RemoteException;  
	/**
	 * Returns the answers to the questions given. 
	 * @return answers to the questions. 
	 * @throws RemoteException
	 */
	public Vector<Question> getData() throws RemoteException; 
	
	public boolean insertQnA(int id, String category, String question, String answer1, String answer2, String answer3, String answer4, String Canswer) throws RemoteException;
	
	public Map<String, List<String>> getQnA() throws RemoteException;
	
	public boolean insertAnswers(int id, String username, String question, String answer, int mark) throws RemoteException;

	public String getAnswer(String question) throws RemoteException;
	
	public Map<String, Integer> getFinalResults() throws RemoteException;
}
