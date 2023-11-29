package EffortLogger;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.regex.Pattern;

import EffortLogger.CSVInterface.LogEntry;
import javafx.fxml.FXML;

//import it.sauronsoftware.junique.AlreadyLockedException;
//import it.sauronsoftware.junique.JUnique;

//Code written by Justin Koehle and Mitch Zakocs
public class EffortLogger extends Application {
	
    protected String username;
    private String taskName;
    
    private Stage mainStage;
    private TabPane root;

    @FXML
    private TextField taskNameTextField;

    @FXML
    private TextField descriptionTextField;
    
    
    @FXML
    private Button startButton;
    
    private long startTime;
    private Boolean planned;
	
    
    @FXML public void handlePlanningPokerButton(ActionEvent event) throws Exception {
    	String taskname = taskNameTextField.getText();
    	String description = descriptionTextField.getText();
    	if (taskname.isEmpty() || description.isEmpty() || planned) {
    		return;
    	}
    	planned = true;
    	taskNameTextField.setEditable(false);
    	descriptionTextField.setEditable(false);
    	CSVInterface.WriteInitialTaskName(taskname, username, description);
    	PlanningPoker pp = new PlanningPoker(root, mainStage, taskname, description);
//    	mainStage.setScene(new Scene(pp));
//    	mainStage.show();
    	Tab tab = new Tab();
        tab.setContent(pp);
        root.getTabs().add(tab);
        root.getSelectionModel().select(tab);
        mainStage.sizeToScene();
        
        
    }
    
    @FXML public void handleStartButton(ActionEvent event) throws Exception {
    	startTime = System.currentTimeMillis();
    	startButton.setDisable(true);
    }
    
    @FXML public void handleStopButton(ActionEvent event) throws Exception {
		if (startTime == 0) {
			return;
		}
		taskNameTextField.clear();
		taskNameTextField.setEditable(true);
		descriptionTextField.clear();
    	descriptionTextField.setEditable(true);
    	startButton.setDisable(false);
    	long endtime = System.currentTimeMillis() - startTime;
    	endtime = Math.round(endtime / (60*1000));
    	CSVInterface.WriteActualPoints(Math.toIntExact(endtime));
    	startTime = 0;
    	planned = false;
    }
    
    //Main launches the JavaFX application
    public static void main(String[] args) {
//       String appId = "EffortLogger";
//        boolean alreadyRunning;
//        try {
//            JUnique.acquireLock(appId);
//            alreadyRunning = false;
//        } catch (AlreadyLockedException e) {
//            alreadyRunning = true;
//        }
//        if (!alreadyRunning) { //No instance currently running
//            launch(args); // JavaFX start sequence 
//        }else{ //Already running project
//            System.out.print(appId + " is already running.");
//        	System.exit(1);
//        }
        //left in for testing purposes
        launch(args);
    }

    //JavaFX logic contained within start
    public void start(Stage primaryStage) throws IOException {
    	startTime = 0;
    	planned = false;
     //Creates the mainline console scene launched on login
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Effort Log Console.fxml"));
    	loader.setController(this);
    	this.mainStage = primaryStage;
    	Parent console = loader.load();
    	Scene consoleScene = new Scene(console);
    	
        primaryStage.setTitle("EffortLogger Login Interface");
        
        TextField loginPrompt = new TextField("Username");
        
        // SQL Injection Prevention
        Pattern sqlInjectionPattern = Pattern.compile("^[a-zA-Z0-9]*$"); // only allows alphanumeric chars
        
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
        	String text = change.getControlNewText() ;
            if (sqlInjectionPattern.matcher(text).matches()) {
            	return change;
            }
            // TODO: Probably get rid of the alerts and just return null
            else if (text.length() > 100) {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
            	alert.setTitle("Error");
                alert.setHeaderText("Username too long!");
                alert.showAndWait();
                return null;
            }
            else {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
            	alert.setTitle("Error");
                alert.setHeaderText("Invalid Character!");
                alert.showAndWait();
                return null;
            }
        });

        loginPrompt.setTextFormatter(textFormatter);
     
        //Login button instantiation and event handling
        Button btn = new Button();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
            	loginChecker nameCheck = new loginChecker();
                username = loginPrompt.getText();
                    //Verify the provided credentials are approved
                	if (nameCheck.verify(username))
                	{
                        root.getSelectionModel().select(1);
//                		primaryStage.setScene(consoleScene);
//                        primaryStage.show();
                	}
            }
        });  
        
        //Add the elements to the VBox
        VBox login_screen = new VBox();
        login_screen.getChildren().add(loginPrompt);
        login_screen.getChildren().add(btn);
        login_screen.setAlignment(Pos.CENTER);
        login_screen.setSpacing(20);
        
        root = new TabPane();
        root.getStylesheets().add("tabbar.css");
        // Create login tab
        Tab tab0 = new Tab();
        tab0.setContent(login_screen);
        // Create console tab
		Tab tab1 = new Tab();
		tab1.setContent(console);
		root.getTabs().addAll(tab0, tab1);
		
		root.setMinWidth(1000);
		root.setMinHeight(500);
		mainStage.setMinWidth(1000);
		mainStage.setMinHeight(500);
		
        //Display the login screen
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }
}