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
	}

	public static void main(String[] args) throws Exception {
		// Get current date information
		Calendar cal = Calendar.getInstance();
		// Set format to month/day/year hours:minutes
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm a");
    	File file = new File("C:/Users/Legato/Desktop/Workout Log.txt"); // File Location
    	PrintWriter output = new PrintWriter(new FileWriter(file, true)); // Write to file without overwriting current contents
    	output.println();
		output.println(sdf.format(cal.getTime())); // Add date to file

		JFrame frame = new Workout();
		frame.setTitle("Luigi's Workout Tracker");
		ImageIcon appIcon = new ImageIcon("C:/MyWork/Images/Muscle.png");
		frame.setIconImage(appIcon.getImage());
		JTextField workout1 = new JTextField("Push ups");
		JTextField set1 = new JTextField();
		JTextField rep1 = new JTextField();
		frame.add(workout1);
		frame.add(set1);
		frame.add(rep1);

		JTextField workout2 = new JTextField("Dumbell Presses");
		JTextField set2 = new JTextField();
		JTextField rep2 = new JTextField();
		frame.add(workout2);
		frame.add(set2);
		frame.add(rep2);

		JTextField workout3 = new JTextField("Dumbell Curls");
		JTextField set3 = new JTextField();
		JTextField rep3 = new JTextField();
		frame.add(workout3);
		frame.add(set3);
		frame.add(rep3);

		JCheckBox abs = new JCheckBox("Abs?");
		JCheckBox squat = new JCheckBox("Squats?");
		frame.add(abs);
		frame.add(squat);
		JButton log = new JButton("Log"); // To log everything
		// Register + create Event Listener
    	log.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Comitting everything to file.\nClosing...");
				output.println("\n" + set1.getText() + " x " + rep1.getText() + " " + workout1.getText());
				output.println(set2.getText() + " x " + rep2.getText() + " " + workout2.getText());
				output.println(set3.getText() + " x " + rep3.getText() + " " + workout3.getText());
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
