package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.perisic.beds.rmiinterface.Authentication;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class MenuGUI {

	private JFrame frame;
	private Authentication authentication;
	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI window = new MenuGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuGUI() {
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogged = new JLabel("Logged as: "+loggedUser());
		lblLogged.setBounds(325, 8, 95, 14);
		frame.getContentPane().add(lblLogged);
		
		JLabel lblText = new JLabel("Exam Contains 40 questions");
		lblText.setBounds(136, 180, 195, 40);
		frame.getContentPane().add(lblText);
		JLabel lblText2 = new JLabel("Time Limit: 40 mins");
		lblText2.setBounds(160, 210, 195, 40);
		frame.getContentPane().add(lblText2);
		
		JButton btnScience = new JButton("Time Starts Now");
		btnScience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				QnAGUI qna = new QnAGUI("questions");
				qna.frame.setVisible(true); 
				
			}
		});
		btnScience.setBounds(143, 97, 135, 40);
		frame.getContentPane().add(btnScience);
		

		
		
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
