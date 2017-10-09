/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;

/**
 *
 * @author Cameron
 */
public class GestureFinger extends JSON{
    public static Boolean debug = Capstone2_Group5.debug;
    private Finger.Type type;
    public GestureBone metacarpal;
    public GestureBone proximal;
    public GestureBone intermediate;
    public GestureBone distal;
    
    public Boolean isExtended;
    public VectorRange allowedDirection;
    
    private GestureFinger(){
        //prevent calling constructor with no type
    }
    
    public GestureFinger(Finger.Type type){
        this.type = type;
        metacarpal = new GestureBone(Bone.Type.TYPE_METACARPAL);
        proximal = new GestureBone(Bone.Type.TYPE_PROXIMAL);
        intermediate = new GestureBone(Bone.Type.TYPE_INTERMEDIATE);
        distal = new GestureBone(Bone.Type.TYPE_DISTAL);
    }
    
    public Finger.Type getType(){
        return type;
    }
    
    public Boolean performedBy(Finger finger){
            Boolean fingerDirectionCorrect, metacarpalCorrect, proximalCorrect, intermediateCorrect, distalCorrect, extendedCorrect;
            Debugger.print(type + " Correctness:");
            fingerDirectionCorrect = allowedDirection.contains(finger.direction());
            Debugger.print("  finger direction: " + fingerDirectionCorrect);
            if(type == Finger.Type.TYPE_THUMB){
                metacarpalCorrect = true;
            } else {
                metacarpalCorrect = metacarpal.allowedDirection.contains(finger.bone(Bone.Type.TYPE_METACARPAL).direction());
            }
            Debugger.print("  metacarpal: " + metacarpalCorrect);
            proximalCorrect = proximal.allowedDirection.contains(finger.bone(Bone.Type.TYPE_PROXIMAL).direction());
            Debugger.print("  proximal: " + proximalCorrect);
            intermediateCorrect = intermediate.allowedDirection.contains(finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction());
            Debugger.print("  intermediate: " + intermediateCorrect);
            distalCorrect = distal.allowedDirection.contains(finger.bone(Bone.Type.TYPE_DISTAL).direction());
            Debugger.print("  distal: " + distalCorrect);
            extendedCorrect = finger.isExtended() == this.isExtended;
            Debugger.print("  extended? " + extendedCorrect);
            return fingerDirectionCorrect && 
                    metacarpalCorrect &&
                    proximalCorrect &&
                    intermediateCorrect &&
                    distalCorrect &&
                    extendedCorrect;
    }
}
