/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Cameron
 */
public class UserProfile {
    private static HashMap<String, UserProfile> namesToProfiles = new HashMap();
    private static ArrayList<UserProfile> profiles = new ArrayList();
    private static UserProfile currentUser;
    private static Event switchedUser;
    private static Event createdUser;
    private static Event deletedUser;
    
    static{
        switchedUser = new Event(Event.TYPE.USER_SWITCHED);
        createdUser = new Event(Event.TYPE.USER_CREATED);
        deletedUser = new Event(Event.TYPE.USER_DELETED);
    }
    
    private String name;
    private ArrayList<Gesture> gestures;
    private UserSettings settings;
    
    public static UserProfile create(String name) throws Exception{
        if(namesToProfiles.containsKey(name)){
            throw new Exception("Profile with name <" + name + "> already exists.");
        }
        UserProfile profile = new UserProfile();
        profile.name = name;
        namesToProfiles.put(name, profile);
        return profile;
    }
    
    public static ArrayList<UserProfile> getAllUsers(){
        return profiles;
    }
    
    public static UserProfile setUser(String name) throws Exception{
        if(!namesToProfiles.containsKey(name)){
            throw new Exception("Profile with name <" + name + "> does not exist.");
        }
        return namesToProfiles.get(name);
    }
    
    public static void handleGesturePerformed(Gesture gesture){
        
    }
    
    private UserProfile(){
        //TODO: 
    }
    
    public UserProfile(String name){
        //TODO: load user profile
    }
    
    public String getName(){
        return name;
    }
  
    
}
