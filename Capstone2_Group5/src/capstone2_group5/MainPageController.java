/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dewikharismawati
 */
public class MainPageController implements Initializable {

    @FXML
    private Label label;
    
    @FXML
    private Button btnNewProfile;
    
    @FXML 
    private Button btnProfileCancel;
    
    @FXML
    private Button btnNewGesture;
    
    @FXML
    private Button btnProfileSave;
    
    @FXML
    private TextField profileName;
    
    @FXML
    private Label testLabel;
    
    @FXML
    private Label nameLabelTest;
    
    @FXML
    private ComboBox<String> comboName;
    
    @FXML
    private TableView gestureMappingTable;
    
    
    @FXML
    private TableColumn<Gesture, String> gestureName;
    
    @FXML
    private TableColumn<BasicCommands, String> commandName;        
    
    String newName;
    
    //ObservableList<UserProfile> list = FXCollections.observableArrayList(
            
    //);
    
    @FXML
    private void handleNewProfile(ActionEvent event) throws IOException, Exception{
        Stage stage; 
        Parent root;
        if(event.getSource()==btnNewProfile){
            stage = new Stage();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("newProfile.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Create New Profile");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnNewProfile.getScene().getWindow());
            stage.showAndWait();
        }
        else
        {
            if(event.getSource() == btnProfileCancel){
                stage = (Stage)btnProfileCancel.getScene().getWindow();
                stage.close();
            }
            if(event.getSource() == btnProfileSave){
                //System.out.println("Dewi");
                //stage = (Stage)btnProfileSave.getScene().getWindow();
                if((profileName.getText()).isEmpty()){
                    testLabel.setText("your new profile name is empty");
                }
                else{
                    newName = profileName.getText();
                    UserManager.createProfile(newName);
                
                    System.out.println("----------------");
                    ArrayList<User> users;
                    users = UserManager.getAllUsers();
                    for(User user : users){
                        System.out.println(user.getName());
                    }
                //System.out.println(newName.toString());
                testLabel.setText("New profile " + profileName.getText() + " is created");
            
                //stage=(Stage) profileCancel.getScene().getWindow();
                //root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                }
            } 
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
}

 