package EffortLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Class with static entries, this is not meant to be used as an object, rather just a group of functions
public class CSVInterface {
	
	// make a class to hold inputs and outputs from the CSV, makes it easier to read
	static class LogEntry {
		public String task_name;
		public int actual_story_points_hours;
		public int estimated_story_points;
		public String user_name;
		
		// Create a class constructor for the Main class
		public LogEntry(String task_name, String user_name, int estimated_story_points, int actual_story_points_hours) {
			this.task_name = task_name;
			this.actual_story_points_hours = actual_story_points_hours;
			this.estimated_story_points = estimated_story_points;
			this.user_name = user_name;
		}
		
	}
	
	
	/// Call from the console WHEN PLANNING POKER is clicked
	@SuppressWarnings("finally")
	static void WriteInitialTaskName(String task_name, String user_name) {
		
		// validate input for the CSV (make sure there are no commas)
		if (task_name.indexOf(',') != -1 || user_name.indexOf(',') != -1) {
			System.out.println("Invalid inputs for CSV!");
			return;
		}
		
		String new_entry = task_name + "," + user_name + ","; // start new line, don't end it
		
		try {
			// write to the local file
			FileWriter fw = new FileWriter("localCSV.csv", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(new_entry);
			bw.close();
		} finally {
			return;
		}
	}
	
	/// To be called from the planning poker screen - estimated_points will be the input in that screen
	@SuppressWarnings("finally")
	static void WriteEstimatedPoints(int estimated_points) {
		
		String new_entry = estimated_points + ","; // start new line, don't end it - still need actual points
		
		try {
			// write to the local file
			FileWriter fw = new FileWriter("localCSV.csv", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(new_entry);
			bw.close();
		} finally {
			return;
		}
	}
	
	/// To be called from the console AFTER planning poker
	@SuppressWarnings("finally")
	static void WriteActualPoints(int actual_points) {
		
		String new_entry = actual_points + "\n"; // end the line here - last entry
		
		try {
			// write to the local file
			FileWriter fw = new FileWriter("localCSV.csv", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(new_entry);
			bw.close();
		} finally {
			return;
		}
	}
	
	// returns array of arrays: [task_name, start_time, end_time, user_name]
	static ArrayList<LogEntry> ReadLoggedData() {
		
		ArrayList<LogEntry> return_array = new ArrayList<>();
		
		File myObj = new File("localCSV.csv");
		Scanner myReader;
		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				//System.out.println(data);
				String[] raw_dat = data.split(",", -1);
				
				// this is the same order it came in
                LogEntry read_entry = new LogEntry(raw_dat[0], raw_dat[1], Integer.parseInt(raw_dat[2]), Integer.parseInt(raw_dat[3]));
				return_array.add(read_entry);
			}
			myReader.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return return_array;
	}

    // public class to test this interface with
    public static void main(String[] args) {
    	
    	WriteInitialTaskName("task1", "user1");
    	WriteEstimatedPoints(5);
    	WriteActualPoints(3);
    	
    	WriteInitialTaskName("task2", "user2");
    	WriteEstimatedPoints(3);
    	WriteActualPoints(6);
    	
    	ArrayList<LogEntry> read_entries = ReadLoggedData();
        for (LogEntry entry : read_entries) {
            System.out.println("Data read: " + entry.task_name + " " + entry.estimated_story_points + " " + 
                               entry.actual_story_points_hours + " " + entry.user_name);
        }
        System.out.println("Done");
    }
}

