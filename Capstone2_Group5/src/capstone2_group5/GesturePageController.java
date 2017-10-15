/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alec
 */
public class GesturePageController implements Initializable {
    
    private GestureCapturer capturer;
    
    @FXML
    private Button captureBtn;
    
    @FXML
    private Button exitBtn;    
    
    @FXML
    private void handleExitBtn(ActionEvent event) throws IOException, Exception{
        
        if(event.getSource() == exitBtn){
            
            LeapService.stop();
            
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            Parent mainPage = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            
            Scene scene = new Scene(mainPage);
            
            stage.setScene(scene);
            stage.show();
            
        }
    }
    
    @FXML
    private void handleCaptureBtn(ActionEvent event) throws IOException, Exception{
        if(event.getSource() == captureBtn){
            
            Gesture newGesture;
            try {
                newGesture = capturer.capture();
                if(newGesture == null){
                    System.out.println("Invalid hand");
                } else {
                    TextInputDialog dialog = new TextInputDialog("Gesture Name");
                    dialog.setTitle("Gesture Name");
                    dialog.setHeaderText("Please, enter a name for the new gesture.");
                    dialog.setContentText("Please enter gesture name:");

                    Optional<String> result = dialog.showAndWait();

                    result.ifPresent(name -> newGesture.name = name);
                }

            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error capturing gesture");
                alert.setContentText("Error. Invalid hand.");
                alert.showAndWait();
            }    
        }  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
            GestureRecognizer recognizer = new GestureCapturer();
            capturer = (GestureCapturer)recognizer;
            LeapService.start(recognizer);
        
    }    
    
}
