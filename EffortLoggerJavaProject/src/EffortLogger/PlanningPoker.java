package EffortLogger;

import EffortLogger.CSVInterface.LogEntry;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



public class PlanningPoker extends Pane {
	
	public class PlanningPokerController {
	
	    @FXML
	    public TableView historyTable;
	    
	    @FXML
	    private TableColumn taskNameColumn; 
	    
	    @FXML
	    private TableColumn userNameColumn;
	    
	    @FXML
	    private TableColumn effortPointsColumn; 
	
	    public void setupTable(ArrayList<LogEntry> entries) {
	    	
	    }
	
	}

  public PlanningPoker() throws Exception {

	  // Setup FXML
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Effort Log Console.fxml"));
	  this.getChildren().add(loader.load());
	  // Setup table
	  PlanningPokerController childController = loader.getController();
	  childController.setupTable(CSVInterface.ReadLoggedData());
  }
}
