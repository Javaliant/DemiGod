/* Author: Luigi Vincent
A simple application to keep track of workouts
TO DO:
 ** A way to check that set and rep are actually numbers - DONE
 ** Not display them on the log if fields are left blank - DONE
 ** Functionality to checkboxes - DONE
 ** Consolidation of redundant information - DONE
 ** Ensure file exists or create if it doesn't - DONE
 ** Add analytics to keep track information e.g. weekly progres, progress since last workout, etc
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class WorkoutTracker extends JFrame {

	public WorkoutTracker() {
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
    	
    	// Creates file object and sets pointer
    	File file = new File("Workout Log.txt"); // File Location
    	// Ensures file exists and is not a directory
    	if ( !file.exists() || file.isDirectory() ) {
			PrintWriter writer = new PrintWriter("Workout Log.txt", "UTF-8");
			writer.close();
		}

    	PrintWriter output = new PrintWriter(new FileWriter(file, true)); // Write to file without overwriting current contents
    	output.println(); // Line break
		output.println(sdf.format(cal.getTime())); // Add date to file
		// Create Frame 
		JFrame frame = new WorkoutTracker();
		frame.setTitle("Luigi's Workout Tracker"); // title
		// Set Icon image
		ImageIcon appIcon = new ImageIcon("Images/Muscle.png");
		frame.setIconImage(appIcon.getImage());

		// workout, set, and rep fields for data
		JTextField[] workouts = { new JTextField("Push ups"), new JTextField("Dumbell Presses"), new JTextField("Dumbell Curls") };
		JTextField[] sets = new JTextField[3];
		JTextField[] reps = new JTextField[3];

		// add textfield pointers + add textfields to frame
		for (int i = 0; i < 3; i++) {
			frame.add(workouts[i]);
			sets[i] = new JTextField(); 
			frame.add(sets[i]);
			reps[i] = new JTextField();
			frame.add(reps[i]);
		}

		// Create checkboxes
		JCheckBox abs = new JCheckBox("Abs?");
		JCheckBox squat = new JCheckBox("Squats?");
		// Create Listener
		ActionListener checkListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == abs) {
					if (abs.isSelected()) {
                    	output.println("Ab Workout done!");
                	}
				}
				else if (e.getSource() == squat) {
					if (squat.isSelected()) {
                   		output.println("Squat Workout done!");
                	}
				}
			}
		};
		// Register listener with checkboxes
		abs.addActionListener(checkListener);
		squat.addActionListener(checkListener);
		// Add checkboxes to frame
		frame.add(abs);
		frame.add(squat);
		JButton log = new JButton("Log"); // To log everything
		// Register + create Event Listener
    	log.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Comitting everything to file.\nClosing...");
				for (int i = 0; i < 3; i++) {
					// Checks if fields are populated
					if (sets[i].getText().length() > 0 && reps[i].getText().length() > 0) {
						if (Integer.parseInt(sets[i].getText()) > 0 && Integer.parseInt(reps[i].getText()) > 0) {
							output.println( sets[i].getText() + " x " + reps[i].getText() + " " + workouts[i].getText() );
						}
					}
				}
				output.close();
				try {
				   Thread.sleep(500);
				} 
				catch (InterruptedException i) {
				   i.printStackTrace();
				}
				System.exit(0);
				}
		});
    	frame.add(log);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
