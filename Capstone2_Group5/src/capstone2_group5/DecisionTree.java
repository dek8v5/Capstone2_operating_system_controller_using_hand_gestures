/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import com.leapmotion.leap.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Cameron
 */
public class DecisionTree {
    private static Boolean isCreatingTree = false;
    private static Boolean runTreeCreationThread = false;
    private static DecisionTreeNode root;
    private static ArrayList<Attribute> usedAttributes;
    private static ArrayList<Gesture> gestureList;
    
    private DecisionTree(){
    }
    
    public enum Attribute{
        INDEX_EXTENDED,
        MIDDLE_EXTENDED,
        RING_EXTENDED,
        PINKY_EXTENDED,
        THUMB_EXTENDED,
        PALM_NORMAL,
        GRAB_ANGLE,
        PINCH_DISTANCE,
        INDEX_DIRECTION,
        MIDDLE_DIRECTION,
        RING_DIRECTION,
        PINKY_DIRECTION,
        THUMB_DIRECTION,
        INDEX_METACARPAL_DIRECTION,
        INDEX_INTERMEDIATE_DIRECTION,
        INDEX_PROXIMAL_DIRECTION,
        INDEX_DISTAL_DIRECTION,
        MIDDLE_METACARPAL_DIRECTION,
        MIDDLE_INTERMEDIATE_DIRECTION,
        MIDDLE_PROXIMAL_DIRECTION,
        MIDDLE_DISTAL_DIRECTION,
        RING_METACARPAL_DIRECTION,
        RING_INTERMEDIATE_DIRECTION,
        RING_PROXIMAL_DIRECTION,
        RING_DISTAL_DIRECTION,
        PINKY_METACARPAL_DIRECTION,
        PINKY_INTERMEDIATE_DIRECTION,
        PINKY_PROXIMAL_DIRECTION,
        PINKY_DISTAL_DIRECTION,
        THUMB_INTERMEDIATE_DIRECTION,
        THUMB_PROXIMAL_DIRECTION,
        THUMB_DISTAL_DIRECTION
    };
    
    public static void create(ArrayList<Gesture> gestureList){
        build(null, gestureList);
    }
    
    private static DecisionTree.Attribute determineBestAttribute(ArrayList<Gesture> gestureList){
        HashMap<Attribute, Point> distribution = new HashMap();
        Attribute bestAttribute = null;
        for(Attribute att : Attribute.values()){
            if(!usedAttributes.contains(att)){
                //logic to determine best attribute
            }
        }
        return bestAttribute;
    }
    
    private static void build(DecisionTreeNode parent, ArrayList<Gesture> gestureList){
        DecisionTreeNode child;
        if(parent == null){
            child = root;
        }
        child = new DecisionTreeNode();
    }
    
    public static Gesture findGesture(Frame frame)throws Exception{
        DecisionTreeNode nextNode = root;
        while(nextNode != null && nextNode.isGesture() == false){
            Hand hand = frame.hands().frontmost();
            Object value = null;
            switch(nextNode.getAttribute()){
                case INDEX_EXTENDED:
                    value = (Boolean)hand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0).isExtended();
                    break;
                case MIDDLE_EXTENDED:
                    value = (Boolean)hand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0).isExtended();
                    break;
                case RING_EXTENDED:
                    value = (Boolean)hand.fingers().fingerType(Finger.Type.TYPE_RING).get(0).isExtended();
                    break;
                case PINKY_EXTENDED:
                    value = (Boolean)hand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0).isExtended();
                    break;
                case THUMB_EXTENDED:
                    value = (Boolean)hand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0).isExtended();
                    break;
                case PALM_NORMAL:
                    value = (Vector)hand.palmNormal();
                    break;
                case INDEX_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0).direction();
                    break;
                case MIDDLE_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0).direction();
                    break;
                case RING_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_RING).get(0).direction();
                    break;
                case PINKY_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0).direction();
                    break;
                case THUMB_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0).direction();
                    break;
                case INDEX_METACARPAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0).bone(Bone.Type.TYPE_METACARPAL).direction();
                    break;
                case INDEX_INTERMEDIATE_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
                    break;
                case INDEX_PROXIMAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0).bone(Bone.Type.TYPE_PROXIMAL).direction();
                    break;
                case INDEX_DISTAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_INDEX).get(0).bone(Bone.Type.TYPE_DISTAL).direction();
                    break;
                case MIDDLE_METACARPAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0).bone(Bone.Type.TYPE_METACARPAL).direction();
                    break;
                case MIDDLE_INTERMEDIATE_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
                    break;
                case MIDDLE_PROXIMAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0).bone(Bone.Type.TYPE_PROXIMAL).direction();
                    break;
                case MIDDLE_DISTAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_MIDDLE).get(0).bone(Bone.Type.TYPE_DISTAL).direction();
                    break;
                case RING_METACARPAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_RING).get(0).bone(Bone.Type.TYPE_METACARPAL).direction();
                    break;
                case RING_INTERMEDIATE_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_RING).get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
                    break;
                case RING_PROXIMAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_RING).get(0).bone(Bone.Type.TYPE_PROXIMAL).direction();
                    break;
                case RING_DISTAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_RING).get(0).bone(Bone.Type.TYPE_DISTAL).direction();
                    break;
                case PINKY_METACARPAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0).bone(Bone.Type.TYPE_METACARPAL).direction();
                    break;
                case PINKY_INTERMEDIATE_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
                    break;
                case PINKY_PROXIMAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0).bone(Bone.Type.TYPE_PROXIMAL).direction();
                    break;
                case PINKY_DISTAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_PINKY).get(0).bone(Bone.Type.TYPE_DISTAL).direction();
                    break;
                case THUMB_INTERMEDIATE_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
                    break;
                case THUMB_PROXIMAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0).bone(Bone.Type.TYPE_PROXIMAL).direction();
                    break;
                case THUMB_DISTAL_DIRECTION:
                    value = (Vector)hand.fingers().fingerType(Finger.Type.TYPE_THUMB).get(0).bone(Bone.Type.TYPE_DISTAL).direction();
                    break;
            }
            nextNode = nextNode.getNode(value);
        }
        if(nextNode != null && nextNode.isGesture()){
            return (Gesture)nextNode;
        } else {
            return null;
        }
    }   
}
