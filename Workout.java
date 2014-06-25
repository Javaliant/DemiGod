/* Author: Luigi Vincent
A simple application to log workouts
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Workout extends JFrame {

	public Workout() {

		setLayout(new GridLayout(5, 3));

		// Labels
		add(new JLabel("Workout"));
		add(new JLabel("Sets"));
		add(new JLabel("Reps"));

		JTextField workout1 = new JTextField("Push ups");
		JTextField set1 = new JTextField();
		JTextField rep1 = new JTextField();
		add(workout1);
		add(set1);
		add(rep1);

		JTextField workout2 = new JTextField("Dumbell Press");
		JTextField set2 = new JTextField();
		JTextField rep2 = new JTextField();
		add(workout2);
		add(set2);
		add(rep2);

		JTextField workout3 = new JTextField("Dumbell Curls");
		JTextField set3 = new JTextField();
		JTextField rep3 = new JTextField();
		add(workout3);
		add(set3);
		add(rep3);

		JCheckBox abs = new JCheckBox("Abs?");
		JCheckBox squat = new JCheckBox("Squats?");
		JButton log = new JButton("Log");
		log.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Get this button to do stuff");
			}

		});
		add(abs);
		add(squat);
		add(log);
	}

	public static void main(String[] args) {

		// Get current date information
		Calendar cal = Calendar.getInstance();
		// Set format to hour
    	SimpleDateFormat sdf = new SimpleDateFormat("HH/mm/ss");


		JFrame frame = new Workout();
		frame.setTitle("Luigi's Workout App");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
