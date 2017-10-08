/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

/**
 *
 * @author Cameron
 */
public class UserManager implements java.io.Serializable {
    private static int numManagers = 0;
    private static UserManager manager;
    private static final OSControl osControl = Capstone2_Group5.getOSController();
    
    private final HashMap<String, User> namesToProfiles = new HashMap();
    private User currentUser;
    private transient Event switchedUser;
    private transient Event createdUser;
    private transient Event deletedUser;
    private transient Event userListChanged;
    
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
        if(namesToProfiles.containsKey(name)){
            throw new Exception("Profile with name <" + name + "> already exists.");
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
        namesToProfiles.put(profile.getName(), profile);
        userListChanged.trigger();
    }
    
    public static ArrayList<User> getAllUsers(){
        initializeManager();
        return manager._getAllUsers();
    }
    
    private ArrayList<User> _getAllUsers(){
        return new ArrayList(namesToProfiles.values());
    }
    
    private void loadProfiles(){
        System.out.println("TODO: load/store profiles");
    }
    
    public static void setCurrentUser(String name) throws Exception{
        initializeManager();
        manager._setCurrentUser(name);
    }
    
    private void _setCurrentUser(String name) throws Exception{
        if(!namesToProfiles.containsKey(name)){
            throw new Exception("Profile with name <" + name + "> does not exist.");
        }
        User user = namesToProfiles.get(name);
        if(user != currentUser){
            currentUser = namesToProfiles.get(name);
            switchedUser.addDetail("user", currentUser);
            switchedUser.trigger();
        }
    }
    
    public static void setCurrentUser(User user) throws Exception{
        initializeManager();
        manager._setCurrentUser(user);
    }
    
    private void _setCurrentUser(User user) throws Exception{
        if(!namesToProfiles.containsValue(user)){
            throw new Exception("Profile " + user + " does not exist");
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
    
    public static void deleteUser(String name) throws Exception{
        initializeManager();
        manager._deleteUser(name);
    }
    
    private void _deleteUser(String name) throws Exception{
        if(!namesToProfiles.containsKey(name)){
            throw new Exception("User is not in the profile list");
        }
        User deleted = namesToProfiles.remove(name);
        userListChanged.trigger();
        deletedUser.addDetail("user", deleted);
        deletedUser.trigger();
    }
    
    public static void deleteUser(User user) throws Exception{
        initializeManager();
        manager._deleteUser(user);
    }
    
    private void _deleteUser(User user) throws Exception{
        if(!namesToProfiles.containsValue(user)){
            throw new Exception("User is not in the profile list");
        }
        namesToProfiles.remove(user.getName(), user);
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
}
