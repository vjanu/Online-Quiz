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

public class RegisterGUI {

	private JFrame register;
	private static JTextField username;
	private static JPasswordField password;
	ButtonGroup bg=new ButtonGroup();    
	
	private Authentication registerService;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					RegisterGUI registerGUI = new RegisterGUI();
					registerGUI.register.setVisible(true);
					}
				 	catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterGUI() {
		try{
			registerService = (Authentication)Naming.lookup("rmi://localhost:1088/AuthService");
		
			
			
		
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
		register = new JFrame();
		register.setBounds(100, 100, 550, 300);
		register.setLocationRelativeTo(null); 
		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		register.getContentPane().setLayout(null);
		
		JLabel lblTopic = new JLabel("Online Quiz");
		lblTopic.setBounds(185, 10, 82, 14);
		register.getContentPane().add(lblTopic);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(500, 8, 82, 14);
		register.getContentPane().add(lblLogin);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(91, 70, 82, 14);
		register.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(91, 120, 82, 14);
		register.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(200, 70, 86, 20);
		register.getContentPane().add(username);
		username.setColumns(10);
			
		password = new JPasswordField();
		password.setBounds(200, 120, 86, 20);
		register.getContentPane().add(password);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(91, 170, 82, 14);
		register.getContentPane().add(lblType);
		
		JRadioButton r1=new JRadioButton("Student", true);  
		r1.setActionCommand( r1.getText() );
		JRadioButton r2=new JRadioButton("Teacher");
		r2.setActionCommand( r2.getText() );
		JRadioButton r3=new JRadioButton("Admin");  
		r3.setActionCommand( r3.getText() );
		r1.setBounds(200,160,120,30);    
		r2.setBounds(320,160,120,30); 
		r3.setBounds(440,160,120,30);    
		
		bg.add(r1);bg.add(r2); bg.add(r3);   
		register.getContentPane().add(r1);
		register.getContentPane().add(r2);
		register.getContentPane().add(r3);
		
		
		
		JButton btnSignUp = new JButton("SignUp");
		btnSignUp.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				
				boolean status = isRegistered();
				
				if(status) {
					String success = "Registration successful";					
					JOptionPane.showMessageDialog(null, success);
					
					register.dispose();
					LoginGUI sg = new LoginGUI();
					sg.main(null);
					
				}
			
				else {
					String unsuccess = "Registration unsuccessful ";
					JOptionPane.showMessageDialog(null, unsuccess);
				}
				
			}
		});
		
		
	
		btnSignUp.setBounds(215, 216, 89, 23);
		register.getContentPane().add(btnSignUp);
		
		lblLogin.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	register.dispose();
		    	LoginGUI sg = new LoginGUI();
				sg.main(null);

		    }  
		});
		
		
	}
	
	public boolean isRegistered() {
		
		
		String uname = username.getText();
		char[] pw = password.getPassword();
		String type = bg.getSelection().getActionCommand();
		
		boolean status = false;
		try {
			status = registerService.registerUser(uname, pw, type);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
}
