 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Cameron
 */
public class User extends JSON {
    private HashMap<Gesture, Command> gestureToCommand;
    private ArrayList<Gesture> gestures;
    private final String name;
    private Boolean modifiedSinceLoad = false;
    private static Event userAddedGesture = new Event(Event.TYPE.USER_ADDED_GESTURE);
    private static Event userRemovedGesture = new Event(Event.TYPE.USER_REMOVED_GESTURE);
    private static Event userMappedGestureToCommand = new Event(Event.TYPE.USER_MAPPED_GESTURE_TO_COMMAND);
    private static Event userRemovedGestureFromCommand = new Event(Event.TYPE.USER_REMOVED_GESTURE_FROM_COMMAND);
    
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

//    @Override
//    public String toJsonString() {
//        JSONObject thisJson = new JSONObject();
//        JSONArray gesturesJson = new JSONArray(); 
//        thisJson.put("name", name);
//        for(Gesture gesture : gestures){
//            gesturesJson.add(gesture.toJsonString());
//        }
//        thisJson.put("gestures", gesturesJson);
//        JSONObject mapping = new JSONObject();
//        for(Entry<Gesture, Command> entry : gestureToCommand.entrySet()){
//            mapping.put(entry.getKey().name, entry.getValue());
//        }
//        thisJson.put("gestureToCommand", mapping);
//        return thisJson.toJSONString();
//    }
    
    public void addGesture(Gesture gesture) throws Exception{
        if(gesture == null){
            throw new Exception("Gesture was null.  Cannot add it to user's gesture list.");
        }
        if(gestures.contains(gesture)){
            throw new Exception("Gesture is already in this user's gesture list."); 
        }
        gestures.add(gesture);
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
            userMappedGestureToCommand.addDetail("user", this);
            userMappedGestureToCommand.addDetail("gesture", gesture);
            userMappedGestureToCommand.addDetail("command", command);
            userMappedGestureToCommand.trigger();
        }
    }
    
    public void removeGestureFromCommand(Command command){
        if(gestureToCommand.containsValue(command)){
            for(Entry<Gesture, Command> entry : gestureToCommand.entrySet()){
                if(entry.getValue().equals(command)){
                    gestureToCommand.remove(entry.getKey());
                    userRemovedGestureFromCommand.addDetail("user", this);
                    userRemovedGestureFromCommand.addDetail("gesture", entry.getKey());
                    userRemovedGestureFromCommand.addDetail("command", entry.getValue());
                    userRemovedGestureFromCommand.trigger();
                    break;
                }
            }
        }
    }
    
    public ArrayList<Gesture> getGestures(){
        return new ArrayList(gestures);
    }
}
