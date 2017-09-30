/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author alec
 */
public class Capstone2_Group5FX extends Application {
    
    private EventListeners controller = new EventListeners();
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Leave");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    Capstone2_Group5.main("0");
            }
        });
        btn.setAlignment(Pos.BOTTOM_RIGHT);
        
        Button btn2 = new Button();
        btn2.setText("Capture a Gesture");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    Capstone2_Group5.main("1");
            }
        });
        btn2.setAlignment(Pos.BOTTOM_LEFT);
        
        Button btn3 = new Button();
        btn3.setText("Use Gestures");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    Capstone2_Group5.main("2");
            }
        });
        btn3.setAlignment(Pos.TOP_RIGHT);
        
                
        Button btn4 = new Button();
        btn4.setText("Run Tests");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    Capstone2_Group5.main("3");
            }
        });
        btn4.setAlignment(Pos.TOP_LEFT);
        
        GridPane root = new GridPane();
        
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        
        root.add(btn,0,1);
        root.add(btn2,0,2);
        root.add(btn3,0,3);
        root.add(btn4,0,4);
        
        Scene scene = new Scene(root, 300, 275);
        
        primaryStage.setTitle("Select Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
