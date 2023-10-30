package EffortLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Class with static entries, this is not meant to be used as an object, rather just a group of functions
public class CSVInterface {
	
	// make a class to hold inputs and outputs from the CSV, makes it easier to read
	static class LogEntry {
		public String task_name;
		public LocalDateTime start_time;
		public LocalDateTime end_time;
		public String user_name;
		
		// Create a class constructor for the Main class
		public LogEntry(String task_name, LocalDateTime start_time, LocalDateTime end_time, String user_name) {
			this.task_name = task_name;
			this.start_time = start_time;
			this.end_time = end_time;
			this.user_name = user_name;
		}
		
		public String getFormattedStartTime() {
	        return start_time.toString();
	    }
		public String getFormattedEndTime() {
	        if (end_time != null) {
	            return end_time.toString();
	        } else {
	            return "";
	        }
	    }
		
		// validate each string property of this class for the CSV file - make sure nothing has commas in it
		public boolean validatePropertiesForCSV() {
	        if (task_name.indexOf(',') != -1 || user_name.indexOf(',') != -1) {
	        	return false;
	        }
	        
	        return true; // everything was OK
	    }
	}
	
	
	// verify and write input data to the CSV file
	@SuppressWarnings("finally")
	static void WriteLoggerData(LogEntry entry) {
		
		// validate inputs here
		if (!entry.validatePropertiesForCSV()) {
			System.out.println("Invalid inputs for CSV!");
			return;
		}
		
		String new_entry = entry.task_name + "," + entry.start_time + "," + entry.end_time  + "," + entry.user_name + "\n";
		
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
				LocalDateTime startTime = LocalDateTime.parse(raw_dat[1]);
                LocalDateTime endTime = raw_dat[2].isEmpty() ? null : LocalDateTime.parse(raw_dat[2]);

				// this is the same order it came in
                LogEntry read_entry = new LogEntry(raw_dat[0], startTime, endTime, raw_dat[3]);
				return_array.add(read_entry);
			}
			myReader.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return return_array;
	}
	
	// keep track of start and end times
	public static LogEntry startTask(String taskName, String userName) {
        LocalDateTime startTime = LocalDateTime.now();
        return new LogEntry(taskName, startTime, null, userName);
    }
    public static void endTask(LogEntry entry) {
        entry.end_time = LocalDateTime.now();
    }

    // public class to test this interface with
    public static void main(String[] args) {
        LogEntry le = startTask("TaskA", "User1");
        LogEntry le2 = startTask("TaskB", "User,2");
        try {
            Thread.sleep(2000); // Wait for 2 seconds to simulate task duration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTask(le);
        endTask(le2);
        WriteLoggerData(le);
        WriteLoggerData(le2);

        ArrayList<LogEntry> read_entries = ReadLoggedData();
        for (LogEntry entry : read_entries) {
            System.out.println("Data read: " + entry.task_name + " " + entry.getFormattedStartTime() + " " + 
                               entry.getFormattedEndTime() + " " + entry.user_name);
        }
        System.out.println("Done");
    }
}

	/*
	public static void main(String[] args) {
		
		LogEntry le = new LogEntry("a","b","c","d");
		WriteLoggerData(le);
		
		LogEntry le2 = new LogEntry("1","2","3","4");
		WriteLoggerData(le2);
		
		ArrayList<LogEntry> read_entries = ReadLoggedData();
		for (int i = 0; i < read_entries.size(); i++) {
			System.out.println("Data read: " + read_entries.get(i).task_name + " " + read_entries.get(i).start_time + " " +
								read_entries.get(i).end_time + " " + read_entries.get(i).user_name);
		}
		
		System.out.println("Done");
    }*/

