/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * FXML Controller class
 *
 * @author dewikharismawati
 */
public class MainPageController implements Initializable {

    GestureRecognizer decisionTree = new AdvancedRecognizer();
    
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
    private ComboBox comboName;

    @FXML
    private TableView<UserManager> gestureMappingTable;

    @FXML
    private TableColumn<UserManager, Gesture> columnGesture;
    
    @FXML
    private TableColumn<UserManager, Command> columnCommand;
 
    
    ArrayList<User> users;
          
    
    String newName;
    
   
    @FXML
    private void handleNewGesture(ActionEvent event) throws IOException, Exception{
        if(event.getSource() == btnNewGesture){
            if(UserManager.getCurrentUser() == null){
                throw new Exception("A user profile must be selected before creating a new gesture.");
            }
            LeapService.stop();
            Stage stage = (Stage) btnNewGesture.getScene().getWindow();
            
            Parent gesturePage = FXMLLoader.load(getClass().getResource("GesturePage.fxml"));
            Scene scene = new Scene(gesturePage);
            
            stage.setScene(scene);
            stage.show();
        }  
    }
    
   
    
    @FXML
    private void handleStart(ActionEvent event) {
        LeapService.start(decisionTree);
        DecisionTreeViewer.start();
    }
    
    @FXML
    private void handleNewProfile(ActionEvent event){
      showNewProfile();      
    }
    
    @FXML
    private void handleSaveNewProfile(ActionEvent event) throws IOException, Exception{
        newName = profileName.getText();
        UserManager.createProfile(newName);
        populateProfileList();
        hideNewProfile();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideNewProfile();
        populateProfileList();  
        
        
        //System.out.println(UserManager.getCommandList());
        //System.out.println(UserManager.getGesture());
        
        //printTest();
        //populateTable();
       
        //populateGestureColumn();
        //populateCommandColumn();
        
        
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
    
    //action for selected user profile combobox
    @FXML
    private void handleComboProfile(ActionEvent event) throws IOException, Exception{
      // String selectedProfile = comboName.getSelectionModel().getSelectedItem();
      // UserManager.setCurrentUser(selectedProfile);
       //show the current user gesture lists
    }
    
   
    public void populateCommandColumn(){
        System.out.println(UserManager.getCurrentUser().getCommandsAndGestures());
        
        /*      
        System.out.println("test");
        columnCommand.setCellValueFactory((CellDataFeatures<UserManager, Command> param) -> {
            System.out.println("Dewi");
            return (ObservableValue<Command>) param.getValue().getCommandList(); //To change body of generated methods, choose Tools | Templates.
        });
**/
    }
 //       gestureMappingTable.getItems().addAll((Object)UserManager.getCommandList2());
}