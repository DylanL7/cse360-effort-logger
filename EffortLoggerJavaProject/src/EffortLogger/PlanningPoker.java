package EffortLogger;

import EffortLogger.CSVInterface.LogEntry;
import EffortLogger.EffortLogger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;

import EffortLogger.DateTimePicker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



public class PlanningPoker extends GridPane {
	
	private TabPane root;
	private Stage mainStage;
	@FXML
	public TableView historyTable;
	
	@FXML
	private TableColumn<LogEntry, String> taskNameColumn; 
	
	@FXML
	private TableColumn<LogEntry, String> userNameColumn;
	
	@FXML
	private TableColumn<LogEntry, Integer> estimatedStoryPointsColumn; 
	
	@FXML
	private TableColumn<LogEntry, Integer> actualStoryPointsColumn; 
	
	@FXML
	private TextArea descriptionTextArea;
	
	
	@FXML
	 ToggleGroup choices;
	
	@FXML public void handleEstimationButton(ActionEvent event) throws Exception {
		RadioButton rb = (RadioButton)choices.getSelectedToggle(); 
		  
        if (rb != null) { 
            int s = Integer.parseInt(rb.getText()); //get selected value 1-6
            System.out.println(s);
            
            root.getSelectionModel().select(1);
            
            CSVInterface.WriteEstimatedPoints(s);
        }
    }
	public PlanningPoker(TabPane root, Stage mainStage, String taskName, String description) throws Exception {
		this.root = root;
		this.mainStage = mainStage;
		
		this.setMinWidth(1000);
		this.setMinHeight(500);
		
		// Setup FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Planning Poker UI.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		loader.load();
		// Setup table
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		taskNameColumn.setCellValueFactory(log_entry -> new SimpleObjectProperty<>(log_entry.getValue().task_name));
		userNameColumn.setCellValueFactory(log_entry -> new SimpleObjectProperty<>(log_entry.getValue().user_name));
		estimatedStoryPointsColumn.setCellValueFactory(log_entry -> new SimpleObjectProperty<>(log_entry.getValue().estimated_story_points));
		actualStoryPointsColumn.setCellValueFactory(log_entry -> new SimpleObjectProperty<>(log_entry.getValue().actual_story_points_hours));
		ArrayList<LogEntry> entries = CSVInterface.ReadLoggedData();
		for (LogEntry entry : entries) {
			historyTable.getItems().add(entry);
		}
		
		// Setup name and desc
		if (!taskName.isEmpty() && !description.isEmpty()) {
			String desc = "";
			desc += "Task Name: ";
			desc += taskName;
			desc += "\n";
			desc += "Description: ";
			desc += description;
			descriptionTextArea.setText(desc);
		}
	}
}
