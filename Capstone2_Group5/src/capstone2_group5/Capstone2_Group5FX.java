/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package capstone2_group5;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Vector;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
//                LeapService.stop();
//                System.out.println("shutdown hook executed");
//            }
//        });
    }
    
    @Override
    public void stop() throws Exception{
        LeapService.stop();
        super.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
//            GestureBone vRange = new GestureBone();
//            vRange.type = Bone.Type.TYPE_DISTAL;
//            vRange.allowedDirection.setCenter(new Vector());
//            vRange.allowedDirection.setAllRanges(2.2);
//            System.out.println("first vRange: " + vRange.toPrettyString());
//            String vRangeString = vRange.makeJSONString();
//            System.out.println("first vRange as json string: " + vRangeString);
//            GestureBone secondRange = new GestureBone();
//            secondRange.makeSelfFromJSON(vRangeString);
//            System.out.println("second vRange from json string: " + secondRange.toPrettyString());
            UserManager.loadFromFile();
            System.out.println(UserManager.manager.makeJSONString());
//            GestureFinger finger = new GestureFinger(Finger.Type.TYPE_INDEX);
//            finger.allowedDirection.setCenter(new Vector(3, 4, 5));
//            finger.allowedDirection.setAllRanges(2.3);
//            System.out.println("First finger: " + finger.toPrettyString());
//            String fingerString = finger.makeJSONString();
//            GestureFinger secondFinger = new GestureFinger();
//            secondFinger.makeSelfFromJSON(fingerString);
////            GestureFinger secondFinger = (GestureFinger)JSONOld.makeJavaObject(fingerString);
//            System.out.println("Second finger: " + secondFinger.toPrettyString());
        } catch (Exception ex) {
            Logger.getLogger(Capstone2_Group5FX.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
    }
}  
