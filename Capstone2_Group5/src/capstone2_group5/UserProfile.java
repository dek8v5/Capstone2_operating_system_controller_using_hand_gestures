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
    public static HashMap<String, UserProfile> profiles = new HashMap();
    
    private String name;
    private ArrayList<Gesture> gestures;
    private UserSettings settings;
    
    public static UserProfile create(String name) throws Exception{
        if(profiles.containsKey(name)){
            throw new Exception("Profile with name <" + name + "> already exists.");
        }
        UserProfile profile = new UserProfile();
        profile.name = name;
        profiles.put(name, profile);
        return profile;
    }
    
    public static UserProfile get(String name) throws Exception{
        if(!profiles.containsKey(name)){
            throw new Exception("Profile with name <" + name + "> does not exist.");
        }
        return profiles.get(name);
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
