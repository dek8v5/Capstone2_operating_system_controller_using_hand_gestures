/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author alec
 */
public class CreateGestureFX {
    
    private Scene scene;

    public CreateGestureFX(Stage primaryStage, Scene primaryScene){
        
        Button btn1 = new Button();
        btn1.setText("Exit");
        btn1.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(primaryScene);
        });
        btn1.setAlignment(Pos.CENTER);
        
        Button btn2 = new Button();
        btn2.setText("Capture a Gesture");
        btn2.setOnAction((ActionEvent event) -> {
            Capstone2_Group5.main("1");
        });
        btn2.setAlignment(Pos.BOTTOM_LEFT);
       
        Image image = new Image("/test.jpg");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(375);
        iv1.setFitHeight(600);
        //iv1.setPreserveRatio(true);
        //iv1.setSmooth(true);
        
        GridPane root = new GridPane();
       
        root.setAlignment(Pos.CENTER);
        root.setHgap(50);
        root.setVgap(25);
        root.setPadding(new Insets(10, 10, 10, 10)); //top,right,bottom,left
        
        root.add(iv1, 0, 0, 2, 5);
        root.add(btn2, 3, 1);
        root.add(btn1, 3, 2);
       
        scene = new Scene(root, 700, 650);
       
    }
    
    public Scene getScene(){
        return scene;
    }
    
}
