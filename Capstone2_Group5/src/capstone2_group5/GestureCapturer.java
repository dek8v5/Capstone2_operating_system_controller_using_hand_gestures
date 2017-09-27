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
public class GestureCapturer implements GestureRecognizer{
    public static Boolean debug = Capstone2_Group5.debug;
    private Frame capturedFrame;
    private Frame lastFrame;
    public static Float defaultAllowedFingerRadianRange = new Float(0.35); //about 20 degrees
    public static Float defaultAllowedBoneRadianRange = new Float(0.7); // about 45 degrees
    public static Float defaultAllowedPalmRadianRange = new Float(0.35); // about 20 degrees
    public static Float defaultAllowedGrabAngleRadianRange = new Float(0.35); // about 20 degrees
    private float defaultAllowedDistance = 20; // in mm
    
    public GestureCapturer(){
    }
    
    public Gesture capture() throws Exception{
        capturedFrame = lastFrame;
        Hand hand = capturedFrame.hands().frontmost();
        if(hand == null || !hand.isValid()){
            return null;
//            LeapService.stop();
//            throw new Exception("Invalid hand");
        }

        Finger index = hand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0);
        Finger middle = hand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0);
        Finger ring = hand.fingers().fingerType(Finger.Type.TYPE_RING).get(0);
        Finger pinky = hand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0);
        Finger thumb = hand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0);
        
        Image frameImage = capturedFrame.images().get(0);
        
        Gesture captured = new Gesture();
        captured.rawImage = frameImage;
        setFingerProperties(index, captured.index);
        setFingerProperties(middle, captured.middle);
        setFingerProperties(ring, captured.ring);
        setFingerProperties(pinky, captured.pinky);
        setFingerProperties(thumb, captured.thumb);
        
        captured.palm.allowedVector = getVectorRange(hand.palmNormal(), defaultAllowedPalmRadianRange);
        Event gestureCaptured = new Event(Event.TYPE.GESTURE_CAPTURED);
        gestureCaptured.addDetail("gesture", captured);
        gestureCaptured.trigger();
        System.out.println("Captured gesture");
        System.out.println(captured);
        return captured;
    }
    
    private VectorRange getVectorRange(Vector vector, float range) throws Exception{
        return new VectorRange(vector, range);
    }
    
    private void setFingerProperties(Finger finger, GestureFinger gestureFinger) throws Exception{
        gestureFinger.isExtended = finger.isExtended();
        gestureFinger.allowedDirection = getVectorRange(finger.direction(), defaultAllowedFingerRadianRange);
        if(finger.type() != Finger.Type.TYPE_THUMB){
            gestureFinger.metacarpal.allowedDirection = getVectorRange(finger.bone(Bone.Type.TYPE_METACARPAL).direction(), defaultAllowedBoneRadianRange);
        }
        gestureFinger.intermediate.allowedDirection = getVectorRange(finger.bone(Bone.Type.TYPE_INTERMEDIATE).direction(), defaultAllowedBoneRadianRange);
        gestureFinger.proximal.allowedDirection = getVectorRange(finger.bone(Bone.Type.TYPE_PROXIMAL).direction(), defaultAllowedBoneRadianRange);
        gestureFinger.distal.allowedDirection = getVectorRange(finger.bone(Bone.Type.TYPE_DISTAL).direction(), defaultAllowedBoneRadianRange);
    }
    
    @Override
    public void scan(Frame frame) {
        lastFrame = frame;
    }
}
