package com.perisic.beds.peripherals;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.perisic.beds.rmiinterface.Authentication;
import com.perisic.beds.rmiinterface.Question;
import com.perisic.beds.rmiinterface.RemoteQuestions;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AnswerGUI {

	private JFrame addQnA;
	JTable table ;
   
	
	private RemoteQuestions results;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					AnswerGUI addQGUI = new AnswerGUI();
					addQGUI.addQnA.setVisible(true);
					}
				 	catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AnswerGUI() {
		try{
			results = (RemoteQuestions) Naming.lookup("rmi://localhost:1099/QuestionService1819");
		
			
			
		
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
		
		JLabel x = new JLabel();
		x.setBounds(100, 100, 82, 14);
		addQnA.getContentPane().add(x);
		
		JLabel y = new JLabel();
		y.setBounds(350, 100, 82, 14);
		addQnA.getContentPane().add(y);
		
		JTextArea question = 
		             new JTextArea("",20,300);
		     question.setBounds(170, 60, 160, 300);
		     question.setEditable(false);
		 	addQnA.getContentPane().add(question);
	      
		JButton btnResults = new JButton("Generate Results");
		btnResults.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				int i = 0;
			List<String> studentList = new ArrayList();
			List<Integer> scoreList = new ArrayList();
				Map<String, Integer> results = getResults();
				int size = results.size();
				if(size>0) {
					for (Map.Entry<String, Integer> entry : results.entrySet()) 
					{
					    int score =  entry.getValue();
						String user = entry.getKey().toString();
						studentList.add(user);
						scoreList.add(score);
						question.setFont(question.getFont().deriveFont(Font.BOLD, question.getFont().getSize()));
						question.append(user+"  ---------------------->  "+score+"/10"+"\n");
						
					
					}
			
				}
				else {
					String unsuccess = "Cannot Retrieve Results";
					JOptionPane.showMessageDialog(null, unsuccess);
				}
				
			}
		});
		
		btnResults.setBounds(350, 370, 140, 23);
		addQnA.getContentPane().add(btnResults);
		
		
		lblLogin.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	addQnA.dispose();
		    	LoginGUI sg = new LoginGUI();
				sg.main(null);

		    }  
		});
		
		
	}
	
	public Map<String, Integer> getResults() {
		
		  Map<String, Integer> studMap = new HashMap();
		
		boolean status = false;
		try {
			studMap = results.getFinalResults();

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return studMap;
	}
}
