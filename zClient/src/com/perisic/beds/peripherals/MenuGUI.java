package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuGUI {

	private JFrame frame;

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
		
		JButton btnScience = new JButton("Science");
		btnScience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QnAGUI qna = new QnAGUI("Science");
				qna.frame.setVisible(true); 
				
			}
		});
		btnScience.setBounds(163, 37, 89, 40);
		frame.getContentPane().add(btnScience);
		
		JButton btnSports = new JButton("Sports");
		btnSports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QnAGUI qna = new QnAGUI("Sport");
				qna.frame.setVisible(true); 
				
			}
		});
		btnSports.setBounds(163, 158, 89, 40);
		frame.getContentPane().add(btnSports);
		
		
	}
	

}
