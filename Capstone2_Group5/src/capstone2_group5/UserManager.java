/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Cameron
 */
public class UserManager extends JSON {
    private static UserManager manager;
    private static final OSControl osControl = Capstone2_Group5.getOSController();
//    private static String baseFilepath = "." + File.separator + "src" + File.separator + "capstone2_group5" + File.separator + "Users" + File.separator;
    private static String baseFilepath = "." + File.separator + "Users" + File.separator;
    private static String managerFilepath = baseFilepath + "Manager.bin";
    
    private transient ArrayList<User> users = new ArrayList<>();
    private HashMap<String, String> userFiles = new HashMap<>();
    private transient User currentUser;
    private String currentUserName = "";
    private transient Event switchedUser;
    private transient Event createdUser;
    private transient Event deletedUser;
    private transient Event userListChanged;
    
    private UserManager(){
        switchedUser = new Event(Event.TYPE.USER_SWITCHED);
        createdUser = new Event(Event.TYPE.USER_CREATED);
        deletedUser = new Event(Event.TYPE.USER_DELETED);
        userListChanged = new Event(Event.TYPE.USER_LIST_CHANGED);
        manager = this;
    }
    
    private static void initializeManager(){
        if(manager != null){
            return;
        }
        try {
            UserManager.loadFromFile();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(manager != null){
                return;
            }
            try {
                manager = new UserManager();
            } catch (Exception ex) {
                Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
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
        users.add(profile);
//        System.out.println("user profile created");
        UserManager.storeUser(profile);
        createdUser.addDetail("user", profile);
        createdUser.trigger();
        userListChanged.trigger();
        _setCurrentUser(profile);
    }
    
    public static ArrayList<User> getAllUsers(){
        initializeManager();
        return manager._getAllUsers();
    }
    
    private ArrayList<User> _getAllUsers(){
        return new ArrayList<>(users);
    }
    
    public static void setCurrentUser(String name) throws Exception{
        initializeManager();
        manager._setCurrentUser(name);
    }
    
    private void _setCurrentUser(String name) throws Exception{
        Boolean switched = false;
        for(User user : users){
            if(user.getName().equals(name)){
                _setCurrentUser(user);
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
        if(user == null){
            throw new Exception("Cannot set current user to null");
        }
        if(!users.contains(user)){
            throw new Exception("Profile " + user.getName() + " does not exist");
        }
        if(user == currentUser){
            return;
        }
        currentUser = user;
        currentUserName = currentUser.getName();
        UserManager.store();
        _readyTree();
        switchedUser.addDetail("user", currentUser);
        switchedUser.trigger();
    }
    
    public static void handleGesturePerformed(Gesture gesture){
        initializeManager();
        if(manager.currentUser == null){
            return;
        }
        Command command = manager.currentUser.getCommand(gesture);
        osControl.performCommand(command);
    }
    
    public static User getCurrentUser(){
        initializeManager();
        return manager._getCurrentUser();
    }
    
    public User _getCurrentUser(){
        return currentUser;
    }
    
    public ArrayList<Gesture> getGesturesForCurrentUser(){
        if(currentUser == null){
            return null;
        }
        return currentUser.getGestures();
    }
    
    public static void deleteUser(String name) throws Exception{
        initializeManager();
        manager._deleteUser(name);
    }
    
    private void _deleteUser(String name) throws Exception{
        Boolean deleted = false;
        for(User user : users){
            if(user.getName().equals(name)){
                _deleteUser(user);
                break;
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
        userFiles.remove(user.getName());
        if(currentUserName.equals(user.getName())){
            currentUser = null;
            currentUserName = "";
        }
        UserManager.store();
        userListChanged.trigger();
        deletedUser.addDetail("name", user);
        deletedUser.trigger();
    }
    
    public static void addGestureToCurrentUser(Gesture gesture) throws Exception{
        initializeManager();
        manager._addGestureToCurrentUser(gesture);
    }
    
    private void _addGestureToCurrentUser(Gesture gesture) throws Exception{
        if(gesture == null){
            throw new Exception("Gesture was null.  Cannot add to current user.");
        }
        checkIfCurrentUserIsSet();
        currentUser.addGesture(gesture);
        UserManager.storeUser(currentUser);
    }
    
    public static void removeGestureFromCurrentUser(Gesture gesture) throws Exception{
        initializeManager();
        manager._removeGestureFromCurrentUser(gesture);
    }
    
    private void _removeGestureFromCurrentUser(Gesture gesture) throws Exception{
        if(gesture == null){
            throw new Exception("Cannot remove null gesture from user");
        }
        checkIfCurrentUserIsSet();
        currentUser.removeGesture(gesture);
        UserManager.storeUser(currentUser);
    }
    
    public static void mapGestureToCommand(Gesture gesture, Command command) throws Exception{
        initializeManager();
        manager._mapGestureToCommand(gesture, command);
    }
    
    private void _mapGestureToCommand(Gesture gesture, Command command) throws Exception{
        checkIfCurrentUserIsSet();
        currentUser.mapCommandToGesture(command, gesture);
        UserManager.storeUser(currentUser);
    }
    
    private void checkIfCurrentUserIsSet() throws Exception{
        if(currentUser == null){
            throw new Exception("No user selected");
        }
    }
    
    public static void readyTree() throws Exception{
        initializeManager();
        manager._readyTree();
    }
    
    private void _readyTree() throws Exception{
        checkIfCurrentUserIsSet();
        DecisionTree.create(currentUser.getGestures());
    }
    
    public static void loadFromFile(){
        Object loadedObj = getObjectFromFile(managerFilepath);
        manager = (UserManager)loadedObj;
        if(manager == null){
            return;
        }
        manager.loadUsers();
        if(manager.currentUserName == null || manager.currentUserName.equals("")){
            return;
        }
        try {
            setCurrentUser(manager.currentUserName);
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadUsers(){
        if(userFiles == null){
            return;
        }
        if(users == null){
            users = new ArrayList<>();
        }
        for(Entry<String, String>entry : userFiles.entrySet()){
            Object userObj = getObjectFromFile(entry.getValue());
            User user = (User)userObj;
            if(user == null){
                continue;
            }
            users.add((User)user);
        }
    }
    
    private static Object getObjectFromFile(String filepath){
        FileInputStream fin = null;
        try{
            File file = new File(filepath);
            if(!file.exists() || file.length() <= 0){
                return null;
            }
            fin = new FileInputStream(filepath);
            byte[] b = new byte[(int)file.length()];
            fin.read(b);
            String fileContents = new String(b);
            JSONParser parser = new JSONParser();
//            System.out.println("read from " + filepath);
            return JSON.toJavaObject((JSONObject)parser.parse(fileContents));
        } catch(Exception e){
            java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            if(fin != null){
                try {
                    fin.close();
                } catch (IOException ex) {
                    Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    public static void storeManagerAndUsers() throws Exception{
        store();
        storeUsers();
    }
    
    public static void store(){
        initializeManager();
        write(managerFilepath, manager.toJsonObject().toString());
    }
    
    public static void storeUsers() throws Exception{
        for(User user : manager.users){
            storeUser(user);
        }
    }
    
    private static void storeUser(User user) throws Exception{
        createDirectory();
        String filepath = baseFilepath + user.getName() + ".bin";
        String toWrite = user.toJsonObject().toString();
        write(filepath, toWrite);
        manager.userFiles.put(user.getName(), filepath);
    }
    
    private static void createDirectory() throws Exception{
        Path path = Paths.get(baseFilepath);
        Files.createDirectories(path);
    }
    
    private static void write(String filepath, String toWrite){
        FileOutputStream fout = null;
        try{
            createDirectory();
            File file = new File(filepath);
            file.createNewFile(); //only creates new file if it doesn't exist
            fout = new FileOutputStream(filepath);
            fout.write(toWrite.getBytes());
//            System.out.println("written to " + filepath);
        }catch(Exception e){
            java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            if(fout != null){
                try{
                    fout.close();
                }catch(IOException e){
                    java.util.logging.Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
