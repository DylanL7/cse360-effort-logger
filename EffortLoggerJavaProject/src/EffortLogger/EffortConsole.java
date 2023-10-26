package EffortLogger;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
	
	    VBox vbox = new VBox(tableView);
	
	    this.getChildren().add(vbox);
	  }
}
