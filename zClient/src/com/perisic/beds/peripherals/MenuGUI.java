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
		
		JButton btnScience = new JButton("Category 1");
		btnScience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				QnAGUI qna = new QnAGUI("Science");
				qna.frame.setVisible(true); 
				
			}
		});
		btnScience.setBounds(163, 37, 95, 40);
		frame.getContentPane().add(btnScience);
		
		JButton btnSports = new JButton("Category 2");
		btnSports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				QnAGUI qna = new QnAGUI("Sport");
				qna.frame.setVisible(true); 
				
			}
		});
		btnSports.setBounds(163, 158, 95, 40);
		frame.getContentPane().add(btnSports);
		
		
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
