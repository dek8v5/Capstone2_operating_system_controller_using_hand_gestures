/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Cameron
 */
public class UserManager implements java.io.Serializable {
    private static int numManagers = 0;
    private static UserManager manager;
    private static final OSControl osControl = Capstone2_Group5.getOSController();
    
//    private final HashMap<String, User> namesToProfiles = new HashMap();
    private ArrayList<User> users = new ArrayList();
    private User currentUser;
    private transient Event switchedUser;
    private transient Event createdUser;
    private transient Event deletedUser;
    private transient Event userListChanged;
    
    private static ArrayList<String> gestureTest = new ArrayList(Arrays.asList("Gesture1", "Gesture2", "Gesture3", "Gesture4", "Gesture5", "Gesture6"));
    
    
    private UserManager() throws Exception{
        if(numManagers == 0){
            numManagers = 1;
            switchedUser = new Event(Event.TYPE.USER_SWITCHED);
            createdUser = new Event(Event.TYPE.USER_CREATED);
            deletedUser = new Event(Event.TYPE.USER_DELETED);
            userListChanged = new Event(Event.TYPE.USER_LIST_CHANGED);
            loadProfiles();
            manager = this;
        } else {
            throw new Exception("Only one user profile manager can be active at one time");
        }
    }
    
    private static void initializeManager(){
        if(manager == null){
            try {
                manager = new UserManager();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void createProfile(String name) throws Exception{
        initializeManager();
        manager._createProfile(name);
    }
    
    private void _createProfile(String name) throws Exception{
        if(name.equals("")){
            throw new Exception("Name must not be empty.");
        }
        for(User user : users){
            if(user.getName().equals(name)){
                throw new Exception("Profile with name <" + name + "> already exists.");
            }
        }
        User profile = new User(name);
        addProfile(profile);
        createdUser.addDetail("user", profile);
        createdUser.trigger();
        _setCurrentUser(name);
    }
    
    private void addProfile(User profile) throws Exception{
        if(profile == null){
            throw new Exception("Invalid profile");
        }
        users.add(profile);
        userListChanged.trigger();
    }
    
    public static ArrayList<User> getAllUsers(){
        initializeManager();
        return manager._getAllUsers();
    }
    
    private ArrayList<User> _getAllUsers(){
        return new ArrayList(users);
    }
    
    private void loadProfiles(){
        System.out.println("TODO: load/store profiles");
    }
    
    public static void setCurrentUser(String name) throws Exception{
        initializeManager();
        manager._setCurrentUser(name);
    }
    
    private void _setCurrentUser(String name) throws Exception{
        Boolean switched = false;
        for(User user : users){
            if(user.getName().equals(name)){
                currentUser = user;
                switchedUser.addDetail("user", currentUser);
                switchedUser.trigger();
                switched = true;
                break;
            }
        }
        if(!switched){
            throw new Exception("Profile with name <" + name + "> does not exist.");
        }
    }
    
    public static void setCurrentUser(User user) throws Exception{
        initializeManager();
        manager._setCurrentUser(user);
    }
    
    private void _setCurrentUser(User user) throws Exception{
        if(!users.contains(user)){
            throw new Exception("Profile " + user.getName() + " does not exist");
        }
        if(user != currentUser){
            currentUser = user;
            switchedUser.addDetail("user", currentUser);
            switchedUser.trigger();
        }
    }
    
    public static void handleGesturePerformed(Gesture gesture){
        initializeManager();
        if(manager.currentUser != null){
            Command command = manager.currentUser.getCommand(gesture);
            osControl.performCommand(command);
        }
    }
    
    public static User getCurrentUser(){
        initializeManager();
        return manager._getCurrentUser();
    }
    
    public User _getCurrentUser(){
        return currentUser;
    }
    
    public ArrayList<Gesture> getGesturesForCurrentUser(){
        if(currentUser != null){
            return currentUser.getGestures();
        } else {
            return null;
        }
    }
    
    public static void deleteUser(String name) throws Exception{
        initializeManager();
        manager._deleteUser(name);
    }
    
    private void _deleteUser(String name) throws Exception{
        Boolean deleted = false;
        for(User user : users){
            if(user.getName().equals(name)){
                deleted = users.remove(user);
                userListChanged.trigger();
                deletedUser.addDetail("user", user);
                deletedUser.trigger();
            }
        }
        if(!deleted){
            throw new Exception("User is not in the profile list");
        }
    }
    
    public static void deleteUser(User user) throws Exception{
        initializeManager();
        manager._deleteUser(user);
    }
    
    private void _deleteUser(User user) throws Exception{
        if(!users.contains(user)){
            throw new Exception("User is not in the profile list");
        }
        users.remove(user);
        userListChanged.trigger();
        deletedUser.addDetail("name", user);
        deletedUser.trigger();
    }
    
    public static void storeInFile(){
        initializeManager();
//        Path file = Paths.get("the-file-name.txt");
//        Byte[] byteArray = manager.
//        Files.write(file, lines, Charset.forName("UTF-8"));
//        FileOutputStream fout = null;
//        ObjectOutputStream oos = null;
//        try{
//            fout = new FileOutputStream("UserManager.txt");
//            oos = new ObjectOutputStream(fout);
//            oos.writeObject(manager);
//            System.out.println("manager written");
//        }catch(Exception e){
//            java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
//        } finally{
//            if(fout != null){
//                try{
//                    fout.close();
//                }catch(IOException e){
//                    java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
//                }
//            }
//            if(oos != null){
//                try{
//                    oos.close();
//                }catch(IOException e){
//                    java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
//                }
//            }
//        }
    }
    
    public List<Command> getCommandList(){
        //System.out.println(java.util.Arrays.asList(Command.values()));
        return java.util.Arrays.asList(Command.values());
    }
    
    public static List<Command> getCommandList2(){
        //System.out.println(java.util.Arrays.asList(Command.values()));
        return java.util.Arrays.asList(Command.values());
    }
    
    public ArrayList<String> getGesture(){
        return gestureTest;
    }
    
   
    
    
    
}
