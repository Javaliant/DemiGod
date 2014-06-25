/* Author: Luigi Vincent
A simple application to log workouts
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.io.*;
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
		add(abs);
		add(squat);
	}

	public static void main(String[] args) throws Exception {
		JFrame frame = new Workout();
		frame.setTitle("Luigi's Workout App");
		JButton log = new JButton("Log");
		// Get current date information
		Calendar cal = Calendar.getInstance();
		// Set format
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm a");
    	File file = new File("C:/Users/Legato/Desktop/Workout Log.txt");
    	PrintWriter output = new PrintWriter(new FileWriter(file, true));
		output.println(sdf.format(cal.getTime()));

    	log.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Closing...");
				output.close();
			}
		});
    	frame.add(log);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
