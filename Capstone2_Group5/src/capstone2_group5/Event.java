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
public class Event {
    private static Integer eventID = 1;
    private static HashMap<String, HashMap> eventHandlers = new HashMap<>();
    private static HashMap<Integer, EventHandler> _eventHandlers = new HashMap<>();
    
    public static Integer registerHandler(String event, EventHandler handler){
        Integer id = Event.getNextID();
        if(!eventHandlers.containsKey(event)){
            HashMap<Integer, EventHandler> handlerHash = new HashMap<>();
            eventHandlers.put(event, handlerHash);
        }
        eventHandlers.get(event).put(id, handler);
        return id;
    }
    
    public static void trigger(Event event){
        eventHandlers.get(event.type).forEach((id, handler) -> {
            EventHandler _handler = (EventHandler)handler;
            _handler.handle(event);
        });
    }
    
    private static Integer getNextID(){
        return eventID++;
    }
    
    public String type;
}
