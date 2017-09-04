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
public class BasicRecognizer implements GestureRecognizer{
    private Event thumbsUp = new Event("gesturePerformed");
    private Event pointingWithIndex = new Event("gesturePerformed");
    
    public BasicRecognizer(){
        thumbsUp.addDetail("gesture", "thumbsUp");
        pointingWithIndex.addDetail("gesture", "pointingWithIndexFinger");
    }
    
    public void checkForPointingWithIndex(Frame frame){
        Finger index = frame.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
        Finger middle = frame.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
        Finger ring = frame.fingers().fingerType(Finger.Type.TYPE_RING).get(0);
        Finger pinky = frame.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0);
        Finger thumb = frame.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0);
        
        if(index.isExtended() && !middle.isExtended() && !ring.isExtended() && !pinky.isExtended() && !thumb.isExtended()){
            pointingWithIndex.trigger();
        }
    }
    
    public void checkForThumbsUp(Frame frame){
        Finger index = frame.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
        Finger middle = frame.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
        Finger ring = frame.fingers().fingerType(Finger.Type.TYPE_RING).get(0);
        Finger pinky = frame.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0);
        Finger thumb = frame.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0);
        
        if(!index.isExtended() && !middle.isExtended() && !ring.isExtended() && !pinky.isExtended() && thumb.isExtended()){
            thumbsUp.trigger();
        }
    }
    
    @Override
    public void scan(Frame frame) {
        checkForThumbsUp(frame);
        checkForPointingWithIndex(frame);
    }
    
}
