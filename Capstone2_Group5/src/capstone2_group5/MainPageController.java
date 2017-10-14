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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    String newName;
    
    String namelist;
    
    ArrayList<User> users;
    int profileListChangedHandlerId = Event.registerHandler(Event.TYPE.USER_LIST_CHANGED, (event) -> {
        this.populateProfileList();
    });
    //ObservableList<UserProfile> list = FXCollections.observableArrayList(
            
    //);
    
    
         
    
    @FXML
    private void handleNewProfile(ActionEvent event) throws IOException, Exception{
       showNewProfile();
        
    }
    
    @FXML
    private void handleSaveNewProfile(ActionEvent event) throws IOException, Exception{
        if((profileName.getText()).isEmpty()){
            testLabel.setText("New profile name is empty");
        }
        else
        {
        newName = profileName.getText();
        UserManager.createProfile(newName);
        users = UserManager.getAllUsers();
            //print all names on lists
            for(User user : users){
                System.out.println(user.getName());
            }
        //testLabel.setText("New profile " + profileName.getText() + " is created");    
        populateProfileList();
        }
        
        hideNewProfile();
    }
         
    @FXML
    private void handleCancelNewProfile(ActionEvent event) throws IOException, Exception{
        hideNewProfile();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideNewProfile();
        populateProfileList();  
    }
    
    
    public void populateProfileList(){
       
        comboName.getItems().removeAll(comboName.getItems());
        users = UserManager.getAllUsers();
        for(User user : users){               
            comboName.getItems().add(user.getName()); 
        }
    }
    
    public void hideNewProfile(){
        profileName.setVisible(false);
        btnProfileCancel.setVisible(false);
        btnProfileSave.setVisible(false);
    }
    
    public void showNewProfile(){
        profileName.setVisible(true);
        btnProfileCancel.setVisible(true);
        btnProfileSave.setVisible(true); 
    }
}

 