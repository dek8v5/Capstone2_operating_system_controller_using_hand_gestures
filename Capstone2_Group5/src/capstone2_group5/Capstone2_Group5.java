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
    public static void main(String choice, String name){
        // TODO code application logic here
        

//        GestureRecognizer basicRecognizer = new BasicRecognizer();
        GestureRecognizer decisionTree = new AdvancedRecognizer();
        DecisionTree.init();

        Thread mainThread = new Thread(new Runnable(){
            Boolean running = true;
            @Override
            public void run() {
                while(running){

                    String next = choice;
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
                                Gesture newGesture;
                                try {
                                    newGesture = capturer.capture();
                                    if(newGesture == null){
                                        System.out.println("Invalid hand");
                                        continue;
                                    }
                                    String gestureName = name;
                                    newGesture.name = gestureName;
                                    break;
                                } catch (Exception ex) {
                                    Logger.getLogger(Capstone2_Group5.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        case "2":
//                            LeapService.start(basicRecognizer);
                            LeapService.start(decisionTree);
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
