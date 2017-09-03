/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

/**
 *
 * @author Cameron
 */
public class Capstone2_Group5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int startupHandlerId = Event.registerHandler("startup", (Event event) -> {
            System.out.println("Startup occurred!!!!!!!!");
        });
        Event startup = new Event();
        startup.type = "startup";
        Event.trigger(startup);
        Event.trigger(startup);
        //Cameron's test
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        Event.trigger(startup);
        System.out.println("this is dedwi");

        
        
        
        //HEEEELLLLOOOO
        
        
    }
    
}
