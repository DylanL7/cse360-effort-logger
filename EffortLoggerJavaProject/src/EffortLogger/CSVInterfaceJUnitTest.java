package EffortLogger;

import java.util.ArrayList;

import EffortLogger.CSVInterface.LogEntry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// Class with static entries, this is not meant to be used as an object, rather just a group of functions
public class CSVInterfaceJUnitTest {
	
	@Test
	public void Test1() {
		CSVInterface.WriteInitialTaskName("task1", "user1", "description");
		CSVInterface.WriteEstimatedPoints(5);
		CSVInterface.WriteActualPoints(3);
		
		ArrayList<LogEntry> read_entries = CSVInterface.ReadLoggedData();
		// this should only have 1 value, check each property
		
		assertEquals("task1", read_entries.get(0).task_name);
		assertEquals("user1", read_entries.get(0).user_name);
		assertEquals("description", read_entries.get(0).description);
		assertEquals(5, read_entries.get(0).estimated_story_points);
		assertEquals(3, read_entries.get(0).actual_story_points_hours);
	}

}

