package com.perisic.beds.peripherals;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
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
import java.awt.event.ActionEvent;

public class LoginGUI {

	private JFrame login;
	 public  static JTextField username;
	 public JPasswordField password;
	 JLabel lblFakeUsername;
	private String user;
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	private Authentication authentication;
	private Authentication registerNewUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				
				try {
					LoginGUI window = new LoginGUI();
					window.login.setVisible(true);
					
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
	public LoginGUI() {
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
		login = new JFrame();
		login.setBounds(100, 100, 400, 300);
		login.setLocationRelativeTo(null);  
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		
		JLabel lblTopic = new JLabel("Online Quiz");
		lblTopic.setBounds(185, 10, 82, 14);
		login.getContentPane().add(lblTopic);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(91, 70, 82, 14);
		login.getContentPane().add(lblUsername);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(91, 120, 82, 14);
		login.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(200, 70, 86, 20);
		login.getContentPane().add(username);
		username.setColumns(10);
		
	    
		
		password = new JPasswordField();
		password.setBounds(200, 120, 86, 20);
		login.getContentPane().add(password);
		setUser(userType().get(1));
		
		
		JButton btnLogin = new JButton("Login");
		
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				setUser(userType().get(1));
				boolean status = authenticate();
				String user = userType().get(0);
				
				if(status) {
					String success = "Login successfull";					
					JOptionPane.showMessageDialog(null, success);
					//check user type
					
					if(user.equals("Student")) {
						login.dispose();
						StartGUI sg = new StartGUI();
						sg.main(null);
					}
					if(user.equals("Teacher")) {
						login.dispose();
						AnswerGUI sg = new AnswerGUI();
						sg.main(null);
					}
					if(user.equals("Admin")) {
						login.dispose();
						AdminGUI sg = new AdminGUI();
						sg.main(null);
					}
				}
			
				else {
					String unsuccess = "Login unsuccessfull ";
					JOptionPane.showMessageDialog(null, unsuccess);
				}
				
			}
		});
		
		
		btnLogin.setBounds(155, 216, 89, 23);
		
		login.getContentPane().add(btnLogin);
		
	}
	
	public boolean authenticate() {
		
		
		String uname = username.getText();
	
		char[] pw = password.getPassword();
		
		boolean status = false;
		try {
			status = authentication.authenticate(uname,pw);
			authentication.insertUser(uname);
			String s = authentication.getUser(uname, pw);
			
			System.out.println(s);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
    public List<String> userType() {
		List<String> newList = new ArrayList();
		String uname = username.getText();
		
		char[] pw = password.getPassword();
		
		String userType = "";
		try {
			userType = authentication.getUser(uname,pw);
			newList.add(userType);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newList.add(uname);
		
		return newList;
	}
}
