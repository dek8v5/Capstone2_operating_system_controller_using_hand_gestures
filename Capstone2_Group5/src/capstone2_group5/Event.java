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
    private static Integer handlerID = 1;
    private static HashMap<String, HashMap> eventHandlers = new HashMap<>();
    private static HashMap<Integer, String> handlerIdToType = new HashMap<>();
    
    private static Integer getNextID(){
        return handlerID++;
    }
   
    public static Integer registerHandler(String event, EventHandler handler){
        Integer id = Event.getNextID();
        if(!eventHandlers.containsKey(event)){
            HashMap<Integer, EventHandler> handlerHash = new HashMap<>();
            eventHandlers.put(event, handlerHash);
        }
        eventHandlers.get(event).put(id, handler);
        handlerIdToType.put(id, event);
        return id;
    }
    
    public static void removeHandler(Integer id){
        eventHandlers.get(handlerIdToType.get(id)).remove(id);
    }
    
    public static void trigger(Event event){
        eventHandlers.get(event.type).forEach((id, handler) -> {
            EventHandler _handler = (EventHandler)handler;
            _handler.handle(event);
        });
    }
    
    public String type;
    public HashMap details = new HashMap();
    
    public Event(String type){
        this.type = type;
    }
    
    public void addDetail(String name, Object detail){
        details.put(name, detail);
    }
    
    public Object get(String name){
        return details.get(name);
    }
    
    public void trigger(){
        Event.trigger(this);
    }
}
