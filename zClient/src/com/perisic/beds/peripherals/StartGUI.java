package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JLabel;

//import com.perisic.beds.questionnaire.QuestionSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

import com.perisic.beds.rmiinterface.Authentication;

public class StartGUI {

	private JFrame StartFrame;
	private Authentication authentication;
	JLabel lblLogin ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					StartGUI window = new StartGUI();
					window.StartFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	private QuestionSet questionnaire = new QuestionSet(); 

	/**
	 * Create the application.
	 */
	
  
	public StartGUI() {
		try {
			authentication = (Authentication)Naming.lookup("rmi://localhost:1088/AuthService");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		StartFrame = new JFrame();
		StartFrame.setBounds(100, 100, 450, 300);
		StartFrame.setLocationRelativeTo(null); 
		StartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StartFrame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Go To Quiz");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartFrame.dispose();
				MenuGUI mg = new MenuGUI();
				mg.main(null);
				
				
				
			}
		});
		
		JLabel lblLogged = new JLabel("Logged as: "+loggedUser());
		lblLogged.setBounds(325, 8, 95, 14);
		StartFrame.getContentPane().add(lblLogged);
		
		lblLogin = new JLabel("Go Back");
		lblLogin.setBounds(10, 8, 82, 14);
		StartFrame.getContentPane().add(lblLogin);
		
		lblLogin.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StartFrame.dispose();
		    	LoginGUI sg = new LoginGUI();
				sg.main(null);

		    }  
		});
		
		
		
		btnStart.setBounds(147, 93, 149, 68);
		StartFrame.getContentPane().add(btnStart);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(431, 257, -426, -254);
		StartFrame.getContentPane().add(layeredPane);
		

		
		
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
	
}

