package com.perisic.beds.peripherals;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.perisic.beds.rmiinterface.Authentication;
import com.perisic.beds.rmiinterface.RemoteQuestions;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AddQGUI {

	private JFrame addQnA;
	JComboBox<String> cb;
	JTextArea question;
	JTextField ans1;
	JTextField ans2;
	JTextField ans3;
	JTextField ans4;
	JTextField cans;
   
	
	private RemoteQuestions myQuestions;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					AddQGUI addQGUI = new AddQGUI();
					addQGUI.addQnA.setVisible(true);
					}
				 	catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddQGUI() {
		try{
			myQuestions =   (RemoteQuestions) Naming.lookup("rmi://localhost:1099/QuestionService1819");
			
		
			
			
		
		}catch (MalformedURLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NotBoundException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addQnA = new JFrame();
		addQnA.setBounds(100, 600, 550, 450);
		addQnA.setLocationRelativeTo(null); 
		addQnA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addQnA.getContentPane().setLayout(null);
		
		JLabel lblTopic = new JLabel("Online Quiz");
		lblTopic.setBounds(185, 10, 82, 14);
		addQnA.getContentPane().add(lblTopic);
		
		JLabel lblLogin = new JLabel("Go Back");
		lblLogin.setBounds(470, 8, 82, 14);
		addQnA.getContentPane().add(lblLogin);
		
		
		JLabel lblSubject = new JLabel("Select Subject");
		lblSubject.setBounds(91, 70, 90, 14);
		addQnA.getContentPane().add(lblSubject);
		
		JLabel lblQuestion = new JLabel("Enter Question");
		lblQuestion.setBounds(91, 120, 90, 14);
		addQnA.getContentPane().add(lblQuestion);
		
		JLabel lblAnswer1 = new JLabel("Answer 1");
		lblAnswer1.setBounds(91, 170, 82, 14);
		addQnA.getContentPane().add(lblAnswer1);
		
		JLabel lblAnswer2 = new JLabel("Answer 2");
		lblAnswer2.setBounds(91, 220, 82, 14);
		addQnA.getContentPane().add(lblAnswer2);
		
		JLabel lblAnswer3 = new JLabel("Answer 3");
		lblAnswer3.setBounds(91, 270, 82, 14);
		addQnA.getContentPane().add(lblAnswer3);
		
		JLabel lblAnswer4 = new JLabel("Answer 4");
		lblAnswer4.setBounds(91, 320, 82, 14);
		addQnA.getContentPane().add(lblAnswer4);
		
		JLabel lblCorrectAnswer = new JLabel("Correct Answer");
		lblCorrectAnswer.setBounds(91, 370, 92, 14);
		addQnA.getContentPane().add(lblCorrectAnswer);
		
		 String subject[]={"Science", "Sports"};        
	     cb=new JComboBox<String>(subject);    
	     cb.setBounds(200, 70, 86, 20);   
	     addQnA.getContentPane().add(cb);
	     
	     
	      question = 
	             new JTextArea("",50,50);
	     question.setBounds(200, 120, 300, 40);
	
	 	addQnA.getContentPane().add(question);
	          
		 ans1 = new JTextField();
		ans1.setBounds(200, 170, 86, 20);
		addQnA.getContentPane().add(ans1);
		ans1.setColumns(10);
			
		 ans2 = new JTextField();
		ans2.setBounds(200, 220, 86, 20);
		addQnA.getContentPane().add(ans2);
		ans1.setColumns(10);
		
		 ans3 = new JTextField();
		ans3.setBounds(200, 270, 86, 20);
		addQnA.getContentPane().add(ans3);
		ans1.setColumns(10);
		
		 ans4 = new JTextField();
		ans4.setBounds(200, 320, 86, 20);
		addQnA.getContentPane().add(ans4);
		ans1.setColumns(10);
		
		cans = new JTextField("1/2/3/4");
		cans.setBounds(200, 370, 86, 20);
		addQnA.getContentPane().add(cans);
		cans.setColumns(10);
		
		
		addQnA.getContentPane().add(ans1);
		addQnA.getContentPane().add(ans2);
		addQnA.getContentPane().add(ans3);
		addQnA.getContentPane().add(ans4);
		addQnA.getContentPane().add(cans);
		
		JButton btnAddQ = new JButton("Enter");
		btnAddQ.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				boolean status = insertQnA();
				
				if(status) {
					String success = "Question Added";					
					JOptionPane.showMessageDialog(null, success);
					
					
					
				}
			
				else {
					String unsuccess = "Question cannot be added ";
					JOptionPane.showMessageDialog(null, unsuccess);
				}
				
			}
		});
		
		
	
		btnAddQ.setBounds(350, 370, 140, 23);
		addQnA.getContentPane().add(btnAddQ);
		
		lblLogin.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	addQnA.dispose();
		    	AdminGUI sg = new AdminGUI();
				sg.main(null);

		    }  
		});
		
		
	}
	
	public boolean insertQnA() {
		
		
		String ques = question.getText();
		String answer1 = ans1.getText();
		String answer2 = ans2.getText();
		String answer3 = ans3.getText();
		String answer4 = ans4.getText();
		String Canswer = cans.getText();
		String category = cb.getItemAt(cb.getSelectedIndex());  
		
		boolean status = false;
		try {
			int id = (int)(Math.random() * 50000000 + 1);
			status = myQuestions.insertQnA(id, category, ques, answer1, answer2, answer3, answer4, Canswer);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
}
