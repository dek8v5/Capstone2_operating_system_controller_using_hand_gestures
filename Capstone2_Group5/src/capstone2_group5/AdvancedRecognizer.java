/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import com.leapmotion.leap.Frame;

/**
 *
 * @author Cameron
 */
public class AdvancedRecognizer implements GestureRecognizer{
    Event gestureFound = new Event(Event.TYPE.GESTURE_PERFORMED);
    
    @Override
    public void scan(Frame frame){
        try{
            Gesture gesture = DecisionTree.findGesture(frame);
            if(gesture != null){
                gestureFound.addDetail("gesture", gesture);
                gestureFound.trigger();
            }
        } catch(Exception e){
            Debugger.print(e.getMessage());
        }
    }
}
