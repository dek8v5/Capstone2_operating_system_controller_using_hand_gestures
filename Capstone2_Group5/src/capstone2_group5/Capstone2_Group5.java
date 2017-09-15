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
        Event startup = new Event("startup");
        Event.trigger(startup);

        int gesturePerformedId = Event.registerHandler("gesturePerformed", (Event event) -> {
           System.out.println("Gesture performed: " + event.get("gesture")); 
        });
        
        int commandPerformedId = Event.registerHandler("commandPerformed", (Event event) -> {
           System.out.println("Command performed: " + event.get("command")); 
        });
        
        
        GestureRecognizer recognizer = new BasicRecognizer();
        LeapService.start(recognizer);
    }
    
}
