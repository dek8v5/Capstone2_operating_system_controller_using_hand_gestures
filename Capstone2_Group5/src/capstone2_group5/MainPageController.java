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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

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
    private ComboBox comboName;

    @FXML
    private TableView<UserManager> gestureMappingTable;

    @FXML
    private TableColumn<UserManager, Gesture> columnGesture;
    
    @FXML
    private TableColumn<UserManager, Command> columnCommand;
 
    
    List<Command> command;
    
    String newName;
    
    String namelist;
    
    ArrayList<String> gestureList;
    
    
    ComboBox<String> combo;
    //gestureTest
    
    private ComboBox<String> comboGesture;
    
    ArrayList<User> users;
    int profileListChangedHandlerId = Event.registerHandler(Event.TYPE.USER_LIST_CHANGED, (event) -> {
        this.populateProfileList();
    });
    
    ArrayList<UserManager> commands;
    ArrayList<UserManager> gestures;
    //private ArrayList<String> gestureTest = new ArrayList(Arrays.asList("Gesture1", "Gesture2", "Gesture3", "Gesture4", "Gesture5", "Gesture6"));
    
    //gestureTest
    //private String gestureTest = "Gesture2";
    
    @FXML
    private void handleNewProfile(ActionEvent event) throws IOException, Exception{
       showNewProfile(); 
    }
    
    @FXML
    private void handleSaveNewProfile(ActionEvent event) throws IOException, Exception{
        newName = profileName.getText();
        UserManager.createProfile(newName);
        users = UserManager.getAllUsers();
            //print all names on lists
            for(User user : users){
                System.out.println(user.getName());
            }
        //testLabel.setText("New profile " + profileName.getText() + " is created");    
        populateProfileList();
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
        
        
        //System.out.println(UserManager.getCommandList());
        //System.out.println(UserManager.getGesture());
        
        //printTest();
        //populateTable();
       
        //populateGestureColumn();
        populateCommandColumn();
        
        
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
        
        /*      
        System.out.println("test");
        columnCommand.setCellValueFactory((CellDataFeatures<UserManager, Command> param) -> {
            System.out.println("Dewi");
            return (ObservableValue<Command>) param.getValue().getCommandList(); //To change body of generated methods, choose Tools | Templates.
        });
*/        
        
        
        
        
 //       gestureMappingTable.getItems().addAll((Object)UserManager.getCommandList2());


/*
        //columnCommand.setCellValueFactory(cellData-> cellData.getValue().toString().toLowerCase());
        //
        
        //columnCommand.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().toString().toLowerCase()));
        //gestureMappingTable.getItems().addAll(Command.values());
*/   
    }
    
    
    public void populateGestureColumn(){
/*        System.out.println("test");
        columnGesture.setCellValueFactory((CellDataFeatures<String, Gesture> param) -> {
            System.out.println("Dewi");
            return (ObservableValue<Gesture>) param.getValue(); //To change body of generated methods, choose Tools | Templates.
        });
*/
        //gestureMappingTable.getItems().addAll(gestureTest);
        //columnGesture.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().toString()));
        
    }
 /*   
    public void populateTable(){
        System.out.println("test");
        columnGesture.setCellValueFactory((TableColumn.CellDataFeatures<UserManager, ArrayList<String>> param) -> {
            System.out.println("Dewi");
            System.out.println(param.getValue().getGesture());
            return param.getValue().getGesture();//To change body of generated methods, choose Tools | Templates.
        });

//        columnGesture.setCellValueFactory((TableColumn.CellDataFeatures<UserManager, String> cellData) -> {
//            return cellData.getValue().getGesture();
//        });
    }
 */    
 /*   
    public void populateGestureColumn(){    
        //columnGesture.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue()));
        
        //gestureMappingTable.getItems().addAll(gestureTest);
        //System.out.println("test");
        
        columnGesture.setCellFactory((TableColumn<User, ArrayList<String>> col) -> {
        System.out.println("test1");   
            TableCell<User, ArrayList<String>> cell = new TableCell<User, ArrayList<String>>() {
          
                @Override
            protected void updateItem(ArrayList<String> ges, boolean empty) {
                System.out.println("test2");  
                super.updateItem(ges, empty);
                if (empty) {
                    setGraphic(null);
                 } 
                else 
                {
                System.out.println("test3");      
                User rowItem = (User) gestureMappingTable.getItems().get(getIndex());
                combo.getItems().setAll(ges);
                combo.setValue(rowItem.getSelectedInfo());
                setGraphic(combo);
                }
            }
            };
        System.out.println("test4");  
        combo.valueProperty().addListener((obs, oldValue, newValue) -> 
        gestureMappingTable.getItems().get(cell.getIndex()).setSelectedInfo(newValue));
        System.out.println("test5");  
        return cell ;
        });
        
        //columnCommand.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().toString().toLowerCase()));
        
        
        //gestureMappingTable.getItems().addAll(gestureTest.toString(), Command.values().toString());
        
        //gestureMappingTable.getColumns().addAll(columnGesture, columnCommand);
        //gestureMappingTable.getColumns().addAll(columnGesture, columnCommand);
        //gestureMappingTable.getItems().addAll(gestureTest);
        
        
        
        //gestureMappingTable.getItems().addAll(gestureTest);
        
       // gestureMappingTable.setItems((ObservableList) gestureTest);
        
        //gestureMappingTable.getColumns().addAll(columnGesture);
        
    }
*/
    
    
        
        
        
}
    
    
    

