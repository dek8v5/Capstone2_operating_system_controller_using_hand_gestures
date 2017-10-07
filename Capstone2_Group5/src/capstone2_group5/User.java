/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.util.HashMap;

/**
 *
 * @author Cameron
 */
public class User implements java.io.Serializable {
    private HashMap<Gesture, Command> gestureToCommand;
    private final String name;
    private Boolean modifiedSinceLoad = false;
    
    private User(){
        gestureToCommand = new HashMap();
        this.name = null;
    }
    
    public User(String name){
        super();
        this.name = name;
    }
    
    public Command getCommand(Gesture gesture){
        return gestureToCommand.get(gesture);
    }
    
    public String getName(){
        return this.name;
    }
    
}
