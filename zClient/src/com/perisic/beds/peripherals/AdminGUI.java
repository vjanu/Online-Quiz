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

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AdminGUI {

	private JFrame admin;
	private static JTextField username;
	private static JPasswordField password;
	
	private Authentication authentication;
	private Authentication registerNewUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				
				try {
					AdminGUI window = new AdminGUI();
					window.admin.setVisible(true);
				
					}
				 	catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminGUI() {
		try{
			authentication = (Authentication)Naming.lookup("rmi://localhost:1088/AuthService");
			
			
			
		
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
		admin = new JFrame();
		admin.setBounds(100, 100, 400, 300);
		admin.setLocationRelativeTo(null);  
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.getContentPane().setLayout(null);
		
		
		
		JLabel lblBack = new JLabel("Go Back");
		lblBack.setBounds(315, 8, 82, 14);
		admin.getContentPane().add(lblBack);
		
		JButton btnAddQuestion = new JButton("Add Question");
		JButton btnShowResult = new JButton("Show Result");
		JButton btnAddUser = new JButton("Add User");
		
		lblBack.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	admin.dispose();
		    	LoginGUI sg = new LoginGUI();
				sg.main(null);

		    }  
		});
		
		
		
		btnShowResult.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				admin.dispose();
				AdminResultGUI window = new AdminResultGUI();
				window.main(null);
			}
		});
		
		btnAddQuestion.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				admin.dispose();
				AddQGUI window = new AddQGUI();
				window.main(null);

			}
		});
		
		btnAddUser.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				admin.dispose();
				RegisterGUI window = new RegisterGUI();
				window.main(null);

			}
		});
		
		btnAddQuestion.setBounds(130, 70, 120, 23);
		btnShowResult.setBounds(130, 120, 120, 23);
		btnAddUser.setBounds(130, 170, 120, 23);
		admin.getContentPane().add(btnShowResult);
		admin.getContentPane().add(btnAddQuestion);
		admin.getContentPane().add(btnAddUser);
	}
	
	public boolean authenticate() {
		
		
		String uname = username.getText();
		
		char[] pw = password.getPassword();
		
		boolean status = false;
		try {
			status = authentication.authenticate(uname,pw);
			String s = authentication.getUser(uname, pw);
			
			System.out.println(s);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
    public String userType() {
		
		String uname = username.getText();
		
		char[] pw = password.getPassword();
		
		String userType = "";
		try {
			userType = authentication.getUser(uname,pw);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userType;
	}
}
