package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JLabel;

//import com.perisic.beds.questionnaire.QuestionSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class StartGUI {

	private JFrame StartFrame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginGUI window1 = new LoginGUI();
		String user = window1.getUser();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGUI window = new StartGUI(user);
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
	
	}
	

	public StartGUI(String username) {
		super();
		initialize(username);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username) {
		StartFrame = new JFrame();
		StartFrame.setBounds(100, 100, 450, 300);
		StartFrame.setLocationRelativeTo(null); 
		StartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StartFrame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartFrame.dispose();
				MenuGUI mg = new MenuGUI();
				mg.main(null);
				
				
				
			}
		});
		btnStart.setBounds(147, 93, 149, 68);
		StartFrame.getContentPane().add(btnStart);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(431, 257, -426, -254);
		StartFrame.getContentPane().add(layeredPane);
		
		JLabel lblLogin = new JLabel(username);
		lblLogin.setBounds(380, 8, 82, 14);
		StartFrame.getContentPane().add(lblLogin);
	}
}

