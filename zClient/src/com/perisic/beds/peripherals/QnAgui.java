package com.perisic.beds.peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.perisic.beds.questionnaire.QuestionSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class QnAgui {

	JFrame frame;
	
	private ButtonGroup bg = new ButtonGroup();
	//private ButtonGroup bg2 = new ButtonGroup();

	private JTextArea lblQuestion;
	
	private JLabel temp;
	private JRadioButton rdbtnNewRadioButton_0;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
	
	private int i=0;
	private int q=10;
	
	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QnAgui window = new QnAgui(null); 
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private String fieldname;
	public QnAgui(String field) {
		
		fieldname = field;
		initialize();
	}

	private QuestionSet questionnaire = new QuestionSet(); 
	private JLabel lblAnswer;
	
	
	
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		if(fieldname == "Science")
		{
			addQuestion(i);
		}else if(fieldname=="Sport")		
		{
			addQuestion(q);
		}
		
		//questionnaire.submitAnswer(0, options0[bg.hashCode()]);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!temp.getText().isEmpty()) {	
					
					if( !fieldname.isEmpty()  )    
					{
						
						if (fieldname=="Science")
						{
							if(i < 10 - 1) 
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
								frame.setVisible(false);
							}
						  }
						if (fieldname=="Sport")
						{
							if(q < 15 - 1) 
							{
								q++;
							
							
							
							frame.getContentPane().remove(lblQuestion);
							frame.getContentPane().remove(temp);
							frame.getContentPane().remove(rdbtnNewRadioButton_0);
							frame.getContentPane().remove(rdbtnNewRadioButton_1);
							frame.getContentPane().remove(rdbtnNewRadioButton_2);
							frame.getContentPane().remove(rdbtnNewRadioButton_3);
		
							frame.getContentPane().validate();
							frame.getContentPane().repaint();
							
							addQuestion(q);		
							}
							else
							{
								frame.setVisible(false);
							}
						  }
					}
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"Select a answer");
				}
				
				
				
			}
		});
		btnNext.setBounds(448, 368, 109, 44);
		frame.getContentPane().add(btnNext);
		
		lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(60, 379, 65, 14);
		frame.getContentPane().add(lblAnswer);
		
		
		
		
		
	}
	
	
	public boolean checkScienceButton()
	{
		return true;
	}
	
	public void addQuestion(int x) {
		
		//lblquestion = new JTextArea();
		//lblquestion.setEditable(false);
		//lblquestion.setBounds(22, 64, 441, 38);
		//frame.getContentPane().add(lblquestion);
		
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
			}
		});
		
		rdbtnNewRadioButton_0.setBounds(45, 120, 190, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_0);
		
		rdbtnNewRadioButton_1 = new JRadioButton(options0[1]);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setText(rdbtnNewRadioButton_1.getText());
			}
		});
		
		rdbtnNewRadioButton_1.setBounds(337, 118, 222, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton(options0[2]);
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setText(rdbtnNewRadioButton_2.getText());
			}
		});
		rdbtnNewRadioButton_2.setBounds(50, 259, 185, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton(options0[3]);
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setText(rdbtnNewRadioButton_3.getText());
			}
		});
		rdbtnNewRadioButton_3.setBounds(341, 262, 235, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_3);
		
		bg.add(rdbtnNewRadioButton_0);
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_3);
		
		
	}
}
