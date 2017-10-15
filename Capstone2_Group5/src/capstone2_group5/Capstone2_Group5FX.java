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


public class Capstone2_Group5FX extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent mainPage = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        
        Scene scene = new Scene(mainPage);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                UserManager.store();
//            }
//        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            UserManager.loadFromFile();
//            UserManager.setCurrentUser("Cameron");
        } catch (Exception ex) {
            Logger.getLogger(Capstone2_Group5FX.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
    }
}  
