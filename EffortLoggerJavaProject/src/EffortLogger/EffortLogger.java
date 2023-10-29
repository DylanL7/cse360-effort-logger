package EffortLogger;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

//Code written by Justin Koehle and Mitch Zakocs
public class EffortLogger extends Application {
    protected String username;

    //Main launches the JavaFX application
    public static void main(String[] args) {
       String appId = "EffortLogger";
        boolean alreadyRunning;
        try {
            JUnique.acquireLock(appId);
            alreadyRunning = false;
        } catch (AlreadyLockedException e) {
            alreadyRunning = true;
        }
        if (!alreadyRunning) { //No instance currently running
            launch(args); // JavaFX start sequence 
        }else{ //Already running project
            System.out.print(appId + " is already running.");
        	System.exit(1);
        }
        //left in for testing purposes
        //launch(args);
    }

    //JavaFX logic contained within start
    public void start(Stage primaryStage) throws IOException {

     //Creates the mainline console scene launched on login
    	Parent console = FXMLLoader.load(getClass().getResource("Effort Log Console.fxml"));
    	Scene consoleScene = new Scene(console);
    	
        primaryStage.setTitle("EffortLogger Login Interface");
        
        BorderPane root = new BorderPane();
        TextField loginPrompt = new TextField("Enter your username");
     
        //Login button instantiation and event handling
        Button btn = new Button();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                username = loginPrompt.getText();
                //root.setCenter(new EffortConsole());
                primaryStage.setScene(consoleScene);
                primaryStage.show();
            }
        });     
        
        //Add the elements to the VBox
        VBox login_screen = new VBox();
        login_screen.getChildren().add(loginPrompt);
        login_screen.getChildren().add(btn);
        login_screen.setAlignment(Pos.CENTER);
        login_screen.setSpacing(20);
        
        root.setCenter(login_screen);
        
        //Display the login screen
        primaryStage.setScene(new Scene(root, 700, 550));
        primaryStage.show();
    }
}
