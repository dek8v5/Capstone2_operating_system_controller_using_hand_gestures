/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;
import com.leapmotion.leap.*;
import java.util.function.Consumer;

/**
 *
 * @author Cameron
 */
public class LeapServiceListener extends Listener{
    public Controller controller;
    public GestureRecognizer gestureRecognizer;
    
    public LeapServiceListener(GestureRecognizer gestureRecognizer){
        this.gestureRecognizer = gestureRecognizer;
    }
    
    public void onConnect(Controller controller){
        this.controller = controller;
        Event connected = new Event("leapControllerConnected");
        connected.trigger();
    }
    
    public void onFrame(Controller controller){
        Frame frame = controller.frame();
        gestureRecognizer.scan(frame);
    }
}
