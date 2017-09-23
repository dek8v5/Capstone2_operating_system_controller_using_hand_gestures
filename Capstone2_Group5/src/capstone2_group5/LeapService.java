/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;
import com.leapmotion.leap.*;
/**
 *
 * @author Cameron
 */
public class LeapService implements Runnable{
    private static Controller controller = new Controller();
    private static Boolean running = false;
    private static Listener listener;

    
    public static void start(GestureRecognizer recognizer){
        listener = new LeapServiceListener(recognizer);
        controller.addListener(listener);
        running = true;
        Thread thread = new Thread(new LeapService());
        thread.start();
    }
    
    public static void stop(){
        running = false;
    }

    @Override
    public void run() {
        while(running){
            //System.out.println("Leap motion controller connected: " + controller.isServiceConnected());
            while(controller.isServiceConnected() && running){
            }
            while(!controller.isServiceConnected() && running){
            }
        }
    }
}