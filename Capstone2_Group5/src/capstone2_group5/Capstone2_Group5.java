/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cameron
 */
public class Capstone2_Group5{
    public static Boolean debug = false;
    private static GestureCapturer capturer;
    private static Boolean capturing = false;
    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        GestureRecognizer basicRecognizer = new BasicRecognizer();
        Thread mainThread = new Thread(new Runnable(){
            Boolean running = true;
            @Override
            public void run() {
                while(running){
                    System.out.println("Select an option");
                    System.out.println("0: Leave");
                    System.out.println("1: Capture a gesture");
                    System.out.println("2: Use gestures");
                    System.out.println("3: Run tests");
                    String next = INPUT.nextLine();
                    switch(next){
                        case "0":
                            LeapService.stop();
                            running = false;
                            break;
                        case "1":
                            capturing = true;
                            GestureRecognizer recognizer = new GestureCapturer();
                            capturer = (GestureCapturer)recognizer;
                            LeapService.start(recognizer);
                            while(capturing){
                                System.out.println("Place your hand in the desired gesture position and enter 1 to capture the gesture");
                                System.out.println("Enter 0 to exit capture mode");
                                String captureInput = INPUT.nextLine();
                                switch(captureInput){
                                    case "0":
                                        capturing = false;
                                        LeapService.stop();
                                        break;
                                    case "1":
                                        Gesture newGesture;
                                        try {
                                            newGesture = capturer.capture();
                                            System.out.println("Give your gesture a name!");
                                            String gestureName = INPUT.nextLine();
                                            if(newGesture != null){
                                                newGesture.name = gestureName;
                                                Event gestureCreated = new Event("gestureCreated");
                                                gestureCreated.details.put("gesture", newGesture);
                                                gestureCreated.trigger();
                                            } else {
                                                System.out.println("Gesture is null");
                                            }
                                        } catch (Exception ex) {
                                            Logger.getLogger(Capstone2_Group5.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        break;
                                }
                            }
                            break;
                        case "2":
                            int gesturePerformedId = Event.registerHandler("gesturePerformed", (Event event) -> {
                                System.out.println("Gesture performed: " + event.get("gesture")); 
                            });
                            LeapService.start(basicRecognizer);
                            break;
                        case "3":
                            TestSuite.run(); //MUST ADD -ea TO VM OPTIONS.  GO TO PROJECT PROPERTIES, RUN, VM OPTIONS
                            Debugger.viewLog();
                            break;
                        default:
                            System.out.println("Not a valid option");
                    }
                }
                Debugger.viewLog();
            }
        });
        mainThread.start();
    }
}
