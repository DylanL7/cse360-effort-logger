package EffortLogger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//Code written by Justin Koehle
public class EffortLogger extends Application {
    protected String username;
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
     
        //Set the FXML loader to load the main console interface which will be loaded after accepting a username
        Parent console = FXMLLoader.load(getClass().getResource("Effort Log Console.fxml"));
       	Scene consoleScene = new Scene(console);

        //Sets the window's title
        primaryStage.setTitle("EffortLogger Login Interface");
        
        BorderPane root = new BorderPane();

        //Text entry field for the username
        TextField loginPrompt = new TextField("Enter your username");
     
        //Login button instantiation and event handling
        Button btn = new Button();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                username = loginPrompt.getText();
                root.setCenter(new EffortConsole());
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
