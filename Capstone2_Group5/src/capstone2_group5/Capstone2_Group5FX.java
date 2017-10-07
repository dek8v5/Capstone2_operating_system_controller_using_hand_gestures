/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package capstone2_group5;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
        
        
        /* 
        //all for add bew profile button 
        Button btnNewProfile = new Button();
        btnNewProfile.setText("Create New Profile");
        btnNewProfile.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        btnNewProfile.setAlignment(Pos.CENTER_RIGHT);
        btnNewProfile.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println("create new profile page");//send to create new profile
            }
        });
        
        //all for add new gesture button
        Button btnNewGesture = new Button();
        btnNewGesture.setText("Create New Gesture");
        btnNewGesture.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        btnNewGesture.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println("send to create new gesture");//send to create new gesture
            }
        });
        
        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "User 1",
            "User 2",
            "User 3"
        );
        
        final ComboBox comboBox = new ComboBox(options);
        
           //for logo
        Image logo = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imageView = new ImageView();
        
        imageView.setImage(logo);
        imageView.setFitWidth(100);
        imageView.setFitHeight(80);
        
        BorderPane border = new BorderPane();
        border.setLeft(null);
        
        HBox topHB = addHBox();
        topHB.getChildren().addAll(imageView, comboBox, btnNewProfile);
        border.setTop(topHB);
        
        
        HBox bottomHB = addHBox();
        bottomHB.getChildren().addAll(btnNewGesture);
        border.setBottom(bottomHB);
        
        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(border, 600, 700);
        
        primaryStage.setTitle("TigerMotion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }




/*public class Capstone2_Group5FX extends Application {
    
    private EventListeners controller = new EventListeners();
    
    @Override
    public void start(Stage primaryStage) {
       
        
        Button btn = new Button();
        btn.setText("Start the thing");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    Capstone2_Group5.main(null);
            }

    @Override
    public void start(Stage primaryStage) {
        
        Button StartBtn = new Button();
        StartBtn.setText("Create New Gesture");
        StartBtn.setOnAction((ActionEvent event) -> {
            CreateGestureFX createGestureFX = new CreateGestureFX(primaryStage, primaryStage.getScene());
            primaryStage.setScene(createGestureFX.getScene());
        });
        StartBtn.setAlignment(Pos.BOTTOM_RIGHT);
        
        
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Button btn3 = new Button();
        btn3.setText("Use Gestures");
        btn3.setOnAction((ActionEvent event) -> {
            Capstone2_Group5.main("2");
        });
        btn3.setAlignment(Pos.TOP_RIGHT);
        
                
        Button btn4 = new Button();
        btn4.setText("Run Tests");
        btn4.setOnAction((ActionEvent event) -> {
            Capstone2_Group5.main("3");
        });
        btn4.setAlignment(Pos.TOP_LEFT);

        
        GridPane root = new GridPane();
        
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        
        root.add(StartBtn,0,1);
        root.add(btn3,0,3);
        root.add(btn4,0,4);
        
        Scene scene = new Scene(root, 800, 775);
        
        primaryStage.setTitle("Select Page");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        try {
            UserManager.createProfile("Cameron");
        } catch (Exception ex) {
            Logger.getLogger(Capstone2_Group5FX.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserManager.storeInFile();
        
        launch(args);
    }
}  
