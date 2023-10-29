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

//Code written by Justin Koehle and Mitch Zakocs
public class EffortLogger extends Application {
    protected String username;

    //Main launches the JavaFX application
    public static void main(String[] args) {
        launch(args);
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
            	loginChecker nameCheck = new loginChecker();
                username = loginPrompt.getText();
                    //Verify the provided credentials are approved
                	if (nameCheck.verify(username))
                	{
                		primaryStage.setScene(consoleScene);
                        primaryStage.show();
                	}
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
