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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI {

	private JFrame login;
	private static JTextField username;
	private static JTextField txtpassword;
	private static JPasswordField password;
	
	private Authentication authentication;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				
				try {
					LoginGUI window = new LoginGUI();
					window.login.setVisible(true);
					
//					authentication = (Authentication)Naming.lookup("rmi://localhost/AuthService1008");
//						
//					if(authentication !=null)
//						System.out.print("dsada");
//					
//					Scanner scanner = new Scanner(System.in);
//					
//					String uname = username.getText();
//					
//					String pw = txtpassword.getText();
//					
//					boolean status = authentication.authenticate(uname,pw);
//					
//					if(status) {
//						String success = "Login successful";					
//						JOptionPane.showMessageDialog(null, success);
//						
//					}
//					else {
//						String unsuccess = "Login unsuccessful ";
//						JOptionPane.showMessageDialog(null, unsuccess);
//					}
//					scanner.close();
//					}catch (MalformedURLException e) {
//						
//						System.out.println(e.getMessage());
//						e.printStackTrace();
//					} catch (RemoteException e) {
//						
//						System.out.println(e.getMessage());
//						e.printStackTrace();
//					} catch (NotBoundException e) {
//
//						System.out.println(e.getMessage());
//						e.printStackTrace();
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
		login.setBounds(100, 100, 450, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(91, 91, 82, 14);
		login.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(91, 142, 46, 14);
		login.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(175, 88, 86, 20);
		login.getContentPane().add(username);
		username.setColumns(10);
		
//		txtpassword = new JTextField();
//		txtpassword.setBounds(175, 185, 86, 20);
//		login.getContentPane().add(txtpassword);
//		txtpassword.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(175, 139, 86, 20);
		login.getContentPane().add(password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				
				boolean status = authenticate();
				
				if(status) {
					String success = "Login successful";					
					JOptionPane.showMessageDialog(null, success);
					
					StartGUI sg = new StartGUI();
					sg.main(null);
					
				}
			
				else {
					String unsuccess = "Login unsuccessful ";
					JOptionPane.showMessageDialog(null, unsuccess);
				}
				
			}
		});
		btnLogin.setBounds(175, 216, 89, 23);
		login.getContentPane().add(btnLogin);
	}
	
	public boolean authenticate() {
		
		
		String uname = username.getText();
		
		String pw = txtpassword.getText();
		
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
}
