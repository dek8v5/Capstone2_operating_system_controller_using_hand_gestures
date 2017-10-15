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
public class User extends JSON {
    private HashMap<Gesture, Command> gestureToCommand = new HashMap();
    private HashMap<Command, Gesture> commandToGesture = new HashMap();
    private ArrayList<Gesture> gestures = new ArrayList();
    private final String name;
    private static Event userAddedGesture = new Event(Event.TYPE.USER_ADDED_GESTURE);
    private static Event userRemovedGesture = new Event(Event.TYPE.USER_REMOVED_GESTURE);
    private static Event userMappedGestureToCommand = new Event(Event.TYPE.USER_MAPPED_GESTURE_TO_COMMAND);
    private static Event userRemovedGestureFromCommand = new Event(Event.TYPE.USER_REMOVED_GESTURE_FROM_COMMAND);
    
    private User(){
        for(Command command : Command.values()){
            commandToGesture.put(command, null);
        }
        this.name = "";
        commandToGesture.forEach((command, gesture) -> {
            System.out.println("Command in user " + name + "'s commandToGesture: " + command);
        });
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
    
    public void addGesture(Gesture gesture) throws Exception{
        if(gesture == null){
            throw new Exception("Gesture was null.  Cannot add it to user's gesture list.");
        }
        if(gestures.contains(gesture)){
            throw new Exception("Gesture is already in this user's gesture list."); 
        }
        gestures.add(gesture);
        removeGestureFromCommand(gestureToCommand.get(gesture));
        userAddedGesture.addDetail("user", this);
        userAddedGesture.addDetail("gesture", gesture);
        userAddedGesture.trigger();
    }
    
    public void removeGesture(Gesture gesture) throws Exception{
        if(gesture == null){
            throw new Exception("Gesture was null.  Cannot remove it from user's gesture list.");
        }
        if(!gestures.contains(gesture)){
            throw new Exception("Gesture is not in this user's gesture list.");
        }
        gestures.remove(gesture);
        Command command = gestureToCommand.remove(gesture);
        if(command != null){
            commandToGesture.put(command, null);
        }
        userRemovedGesture.addDetail("user", this);
        userRemovedGesture.addDetail("gesture", gesture);
        userRemovedGesture.trigger();
    }
    
    public void mapCommandToGesture(Command command, Gesture gesture) throws Exception{
        if(command == null){
            throw new Exception("Command was null.  Cannot map gesture to null command");
        }
        if(gesture == null){
            //umm.... remove command from the mapping?
            removeGestureFromCommand(command);
        } else {
            gestureToCommand.put(gesture, command);
            commandToGesture.put(command, gesture);
            userMappedGestureToCommand.addDetail("user", this);
            userMappedGestureToCommand.addDetail("gesture", gesture);
            userMappedGestureToCommand.addDetail("command", command);
            userMappedGestureToCommand.trigger();
        }
    }
    
    public void removeGestureFromCommand(Command command){
        Gesture gesture = commandToGesture.get(command);
        commandToGesture.put(command, null);
        if(gesture != null){
            gestureToCommand.remove(gesture);
            userRemovedGestureFromCommand.addDetail("user", this);
            userRemovedGestureFromCommand.addDetail("gesture", gesture);
            userRemovedGestureFromCommand.addDetail("command", command);
            userRemovedGestureFromCommand.trigger();
            
        }
    }
    
    public ArrayList<Gesture> getGestures(){
        if(gestures == null){
            return new ArrayList<>();
        }
        return new ArrayList<>(gestures);
    }
    
    public HashMap<Command, Gesture> getCommandsAndGestures(){
        return commandToGesture;
    }
}
