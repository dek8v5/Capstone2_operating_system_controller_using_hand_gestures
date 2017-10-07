/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package capstone2_group5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alec
 */


public class Capstone2_Group5FX extends Application {
    
    //private EventListeners controller = new EventListeners();
    
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
        });
        
        
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Basic Page");
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
 /*   
    private HBox addHBox(){
       HBox hbox = new HBox();
       hbox.setPadding(new Insets(20, 20, 20, 20)); //(top/right/bottom/left)
       hbox.setSpacing(100);   // Gap between nodes
       hbox.setStyle("-fx-background-color: #336699;");
      
       
    return hbox;
    }
    
}
*/