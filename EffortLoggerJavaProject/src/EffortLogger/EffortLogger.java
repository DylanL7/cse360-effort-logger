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
import java.util.regex.Pattern;
import javafx.fxml.FXML;

public class EffortLoggerFXMLController {
    
    @FXML protected void handleEffortLoggerEditorButton(ActionEvent event) {
    	Node source = (Node) .getSource();
        Window stage = source.getScene().getWindow();
        
        source.getScene().getRoot().
    }

}

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
                username = loginPrompt.getText();
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
