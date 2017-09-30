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
public class UserSettings {
    private HashMap<Gesture, Command> gestureToCommand;
    
    public UserSettings(){
        gestureToCommand = new HashMap();
    }
    
    public Command getCommand(Gesture gesture){
        return gestureToCommand.get(gesture);
    }
}
