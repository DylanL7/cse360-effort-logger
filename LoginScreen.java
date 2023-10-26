package EffortLogger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
 
public class LoginScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EffortLogger Login Interface");
        
        //Login button instantiation and event handling
        Button btn = new Button();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                System.out.println("Credential provided");
            }
        });
        
        TextField loginPrompt = new TextField("Enter your username");
        VBox root = new VBox();
        
        //Add the elements to the VBox
        root.getChildren().add(loginPrompt);
        root.getChildren().add(btn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        
        //Display the login screen
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
