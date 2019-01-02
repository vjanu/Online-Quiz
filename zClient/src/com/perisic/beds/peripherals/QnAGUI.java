package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;

import com.perisic.beds.questionnaire.QuestionSet;
import com.perisic.beds.rmiinterface.Authentication;
import com.perisic.beds.rmiinterface.RemoteQuestions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class QnAGUI {

	JFrame frame;
	
	private ButtonGroup bg = new ButtonGroup();
	//private ButtonGroup bg2 = new ButtonGroup();
	private Authentication authentication;
	private JTextArea lblQuestion;
	RemoteQuestions myQuestions; 
	private JLabel temp;
	private JLabel timerClock;
	private JRadioButton rdbtnNewRadioButton_0;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
	private JButton btnNext;
	JButton btnSubmit;
	public static String username;
	private int i=0;
	private int q=10;
	double timeLeft =600000;
	
	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QnAGUI window = new QnAGUI(null); 
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private String fieldname;
	public QnAGUI(String field) {
		try {
		
			myQuestions =   (RemoteQuestions) Naming.lookup("rmi://localhost:1099/QuestionService1819");
			authentication = (Authentication)Naming.lookup("rmi://localhost:1088/AuthService");
		
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fieldname = field;
		initialize();
	}

	private QuestionSet questionnaire = new QuestionSet(); 
	private JLabel lblAnswer;


	
	
	
	public void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null); 
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		username = loggedUser();
		timerClock = new JLabel("40:00:000");
		timerClock.setBounds(475, 8, 150, 14);
		frame.getContentPane().add(timerClock);
		
		timer.start();
		if(fieldname == "questions")
		{
			addQuestion(i);
		}else if(fieldname=="Sport")		
		{
			addQuestion(q);
		}
		
		//questionnaire.submitAnswer(0, options0[bg.hashCode()]);
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null,"Successfully Submitted");
				frame.setVisible(false);
				
			}
		});
//		JLabel lblLogged = new JLabel("Logged as: "+loggedUser());
//		lblLogged.setBounds(510, 8, 95, 14);
//		frame.getContentPane().add(lblLogged);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertAnswers();
					if (!temp.getText().isEmpty()) {	
						
						if( !fieldname.isEmpty()  )    
						{
							
							if (fieldname=="questions")
							{
								if(i < 40 - 1) 
								{
									i++;
								
								
								
								frame.getContentPane().remove(lblQuestion);
								frame.getContentPane().remove(temp);
								frame.getContentPane().remove(rdbtnNewRadioButton_0);
								frame.getContentPane().remove(rdbtnNewRadioButton_1);
								frame.getContentPane().remove(rdbtnNewRadioButton_2);
								frame.getContentPane().remove(rdbtnNewRadioButton_3);
			
								frame.getContentPane().validate();
								frame.getContentPane().repaint();
								
								addQuestion(i);		
								}
								else
								{
									btnSubmit.setEnabled(true);
									btnNext.setEnabled(false);
//									
								}
							  }
//						
						}
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Select a answer");
					}
					
					
					
				}
				 catch (Exception e2) {
					 JOptionPane.showMessageDialog(null,"Select a answer");
				}
			}
		});
		btnNext.setBounds(380, 368, 109, 30);
		frame.getContentPane().add(btnNext);
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(500, 368, 109, 30);
		frame.getContentPane().add(btnSubmit);
		
		lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(60, 379, 65, 14);
		frame.getContentPane().add(lblAnswer);
		
	
	}
	private static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

	public boolean checkScienceButton()
	{
		return true;
	}
	
	public void addQuestion(int x) {

		
		lblQuestion = new JTextArea(questionnaire.getQuestion(x));
		lblQuestion.setEditable(false);
		lblQuestion.setBounds(20, 32, 588, 65);
		lblQuestion.setAutoscrolls(true);
		
		frame.getContentPane().add(lblQuestion);
		
		String [] options0 = questionnaire.getOptions(x); 
		
		temp = new JLabel("");
		temp.setBounds(119, 379, 190, 14);
		frame.getContentPane().add(temp);
		
	
		
		rdbtnNewRadioButton_0 = new JRadioButton(options0[0]);
		rdbtnNewRadioButton_0.setName("");
		rdbtnNewRadioButton_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setText(rdbtnNewRadioButton_0.getText());
				rdbtnNewRadioButton_0.setActionCommand(rdbtnNewRadioButton_0.getText());
			}
		});
		
		rdbtnNewRadioButton_0.setBounds(45, 120, 190, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_0);
		
		rdbtnNewRadioButton_1 = new JRadioButton(options0[1]);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setText(rdbtnNewRadioButton_1.getText());
				rdbtnNewRadioButton_1.setActionCommand(rdbtnNewRadioButton_1.getText());
			}
		});
		
		rdbtnNewRadioButton_1.setBounds(337, 118, 222, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton(options0[2]);
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setText(rdbtnNewRadioButton_2.getText());
				rdbtnNewRadioButton_2.setActionCommand(rdbtnNewRadioButton_2.getText());
			}
		});
		rdbtnNewRadioButton_2.setBounds(50, 259, 185, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton(options0[3]);
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setText(rdbtnNewRadioButton_3.getText());
				rdbtnNewRadioButton_3.setActionCommand(rdbtnNewRadioButton_3.getText());
			}
		});
		rdbtnNewRadioButton_3.setBounds(341, 262, 235, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_3);
		
		bg.add(rdbtnNewRadioButton_0);
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_3);
		
		
	}
       public boolean insertAnswers() {
		String question = lblQuestion.getText();
		String correctAns = null;
		try {
			correctAns = myQuestions.getAnswer(question);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int mark;
		
		String answerNew="";
		String answer = bg.getSelection().getActionCommand();
		if(rdbtnNewRadioButton_0.getText().equalsIgnoreCase(answer)) {
			answerNew ="1";
		}
		if(rdbtnNewRadioButton_1.getText().equalsIgnoreCase(answer)) {
			answerNew ="2";
		}
		if(rdbtnNewRadioButton_2.getText().equalsIgnoreCase(answer)) {
			answerNew ="3";
		}
		if(rdbtnNewRadioButton_3.getText().equalsIgnoreCase(answer)) {
			answerNew ="4";
		}
		
		if(correctAns.equalsIgnoreCase(answerNew)) {
			mark = 1;
		}
		else {
			mark = 0;
		}
		boolean status = false;
		int id = (int)(Math.random() * 500000000 + 1);
			try {
				status = myQuestions.insertAnswers(id, username, question, answerNew, mark);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		status = true;
		
		
		return status;
	}
       
       
       
       public String loggedUser() {
   		
   		
   		String uname = "";
   		
   		try {
   			uname = authentication.loggedUser();
   		} catch (RemoteException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		
   		return uname;
   	}
       
       ActionListener countDown=new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               timeLeft -= 100;
               SimpleDateFormat df=new SimpleDateFormat("mm:ss:SSS");
               timerClock.setText("Time Left: "+df.format(timeLeft));
               if(timeLeft<=0)
               {
                   timer.stop();
                   btnNext.setEnabled(false);
                   btnSubmit.setEnabled(true);
               }
           }
       };
       Timer timer=new Timer(100, countDown);
}
