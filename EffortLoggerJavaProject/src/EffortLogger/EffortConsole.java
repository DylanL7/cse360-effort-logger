package EffortLogger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import EffortLogger.DateTimePicker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class EffortConsoleEntry {
	
	private String taskName;
	
	private String startTime;
	
	private String endTime;
	
	private String userName;
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public EffortConsoleEntry() {}
}


public class EffortConsole extends Pane {

	  public EffortConsole() {
		  
		this.setMinSize(600, 550);
	
		// Create Table 
	    TableView tableView = new TableView();
	
	    TableColumn<EffortConsoleEntry, String> column1 = new TableColumn<>("Task Name");
	    column1.setCellValueFactory(
	        new PropertyValueFactory<>("taskName"));
	
	
	    TableColumn<EffortConsoleEntry, String> column2 = new TableColumn<>("Start Time");
	    column2.setCellValueFactory(
	        new PropertyValueFactory<>("startTime"));
	    
	    TableColumn<EffortConsoleEntry, String> column3 = new TableColumn<>("End Time");
	    column2.setCellValueFactory(
	        new PropertyValueFactory<>("endTime"));
	    
	    TableColumn<EffortConsoleEntry, String> column4 = new TableColumn<>("User Name");
	    column2.setCellValueFactory(
	        new PropertyValueFactory<>("userName"));
	
	
	    tableView.getColumns().add(column1);
	    tableView.getColumns().add(column2);
	    tableView.getColumns().add(column3);
	    tableView.getColumns().add(column4);
	    
	    
	    // Create New Entry Fields
	    HBox new_entry_fields_1 = new HBox();
	    
	    
	    Label task_name_label = new Label("Task Name");
	    TextField task_name_input = new TextField();
	    
	    Button submit_button = new Button("Submit");
	    
	    
	    new_entry_fields_1.getChildren().addAll(task_name_label, task_name_input, submit_button);
	   
	    HBox new_entry_fields_2 = new HBox();
	   	    
	    Label start_time_label = new Label("Start Time");
	    DateTimePicker start_time_input = new DateTimePicker();
	    
	    Label end_time_label = new Label("End Time");
	    DateTimePicker end_time_input = new DateTimePicker();
	    
	    new_entry_fields_2.getChildren().addAll(start_time_label, start_time_input, end_time_label, end_time_input);
	    
	
	    GridPane grid = new GridPane();
	    
	    grid.setAlignment(Pos.CENTER); 
	    grid.setPadding(new Insets(10, 10, 10, 10)); 
	    grid.setVgap(5); 
	    grid.setHgap(5); 
	    
	    grid.add(tableView, 0, 0);
	    grid.add(new Label("New Entry:"), 0, 1);
	    grid.add(new_entry_fields_1, 0, 2);	
	    grid.add(new_entry_fields_2, 0, 3);
	    
	    
	    this.getChildren().add(grid);
	  }
}
