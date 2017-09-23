/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;
import com.leapmotion.leap.Vector;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Cameron
 */
public class DecisionTreeNode {
    private HashMap<Object, DecisionTreeNode> attributeValueToOutcome;
    private DecisionTree.Attribute attribute;
//    private Object attributeValue;
    private ArrayList<DecisionTree.Attribute> usedAttributes;
    
    public DecisionTreeNode(){
        
    }
    
    public DecisionTreeNode(DecisionTree.Attribute attribute){
        this.attribute = attribute;
    }
    
    public Boolean isGesture(){
        return false;
    }
    
    public DecisionTree.Attribute getAttribute(){
        return attribute;
    }
    
    public void setAttribute(DecisionTree.Attribute attribute){
        this.attribute = attribute;
    }
    
    public void setConditionalNode(Object value, DecisionTreeNode node) throws Exception{
        if(attribute == null){
            throw new Exception("Set this node's attribute first");
        }
        switch(attribute){
            case INDEX_EXTENDED:
            case MIDDLE_EXTENDED:
            case RING_EXTENDED:
            case PINKY_EXTENDED:
            case THUMB_EXTENDED:
                //vaue should be boolean
                if(!value.getClass().getName().equals(Boolean.class.getName())){
                   throw new Exception("Attribute " + attribute + " expects a boolean value"); 
                }
//            case GRAB_ANGLE:
//                //value should be a RadianRange
//                if(!value.getClass().getName().equals(RadianRange.class.getName())){
//                    throw new Exception("Attribute " + attribute + " expects a RadianRange");
//                }
                break;
            case PALM_NORMAL:
            case INDEX_DIRECTION:
            case MIDDLE_DIRECTION:
            case RING_DIRECTION:
            case PINKY_DIRECTION:
            case THUMB_DIRECTION:
            case INDEX_METACARPAL_DIRECTION:
            case INDEX_INTERMEDIATE_DIRECTION:
            case INDEX_PROXIMAL_DIRECTION:
            case INDEX_DISTAL_DIRECTION:
            case MIDDLE_METACARPAL_DIRECTION:
            case MIDDLE_INTERMEDIATE_DIRECTION:
            case MIDDLE_PROXIMAL_DIRECTION:
            case MIDDLE_DISTAL_DIRECTION:
            case RING_METACARPAL_DIRECTION:
            case RING_INTERMEDIATE_DIRECTION:
            case RING_PROXIMAL_DIRECTION:
            case RING_DISTAL_DIRECTION:
            case PINKY_METACARPAL_DIRECTION:
            case PINKY_INTERMEDIATE_DIRECTION:
            case PINKY_PROXIMAL_DIRECTION:
            case PINKY_DISTAL_DIRECTION:
            case THUMB_INTERMEDIATE_DIRECTION:
            case THUMB_PROXIMAL_DIRECTION:
            case THUMB_DISTAL_DIRECTION:
                //value should be VectorRange
                if(!value.getClass().getName().equals(VectorRange.class.getName())){
                    throw new Exception("Attribute " + attribute + " expects a VectorRange");
                }
                break;
//            case PINCH_DISTANCE:
//                //value should be DistanceRange
//                if(!value.getClass().getName().equals(DistanceRange.class.getName())){
//                    throw new Exception("Attribute " + attribute + " expects a DistanceRange");
//                }
        }
        if(attributeValueToOutcome == null){
            attributeValueToOutcome = new HashMap();
        }
        attributeValueToOutcome.put(value, node);
    }
    
    public void setUsedAttributes(ArrayList<DecisionTree.Attribute> attributes){
        usedAttributes = new ArrayList();
        attributes.forEach((att) -> {
            usedAttributes.add(att);
        });
    }
    
    public ArrayList<DecisionTree.Attribute> getUsedAttributes(){
        ArrayList<DecisionTree.Attribute> attributes = new ArrayList();
        usedAttributes.forEach((att) -> {
            attributes.add(att);
        });
        return attributes;
    }
    
    public DecisionTreeNode getNextNodeByValue(Object value) throws Exception{
        if(attributeValueToOutcome == null){
            return null;
        }
        DecisionTreeNode toReturn = null;
        switch(attribute){
            case INDEX_EXTENDED:
            case MIDDLE_EXTENDED:
            case RING_EXTENDED:
            case PINKY_EXTENDED:
            case THUMB_EXTENDED:
                //value should be boolean. compare against Boolean
                if(!value.getClass().getName().equals(Boolean.class.getName())){
                    throw new Exception("Attribute " + attribute + " must be given a boolean to be compared against");
                }
                return attributeValueToOutcome.get(value);
//            case GRAB_ANGLE:
//                //value should be Float. compare against RadianRange
//                if(!value.getClass().getName().equals(float.class.getName()) && !value.getClass().getName().equals(Float.class.getName())){
//                    throw new Exception("Attribute " + attribute + " must be given a float to be compared against");
//                }
//                ArrayList<DecisionTreeNode> viableNodes = new ArrayList();
//                attributeValueToOutcome.forEach((compare, node)-> {
//                    RadianRange compareTo = (RadianRange)compare;
//                    if(compareTo.contains((float)value)){
//                        viableNodes.add(node);
//                    }
//                });
//                if(viableNodes.isEmpty()){
//                    toReturn = null;
//                } else if(viableNodes.size() == 1){
//                    toReturn = viableNodes.get(0);
//                } else {
//                    throw new Exception("Multiple viable nodes found in tree for attribute <" + attribute + "> with value of <" + value + ">");
//                }
            case PALM_NORMAL:
            case INDEX_DIRECTION:
            case MIDDLE_DIRECTION:
            case RING_DIRECTION:
            case PINKY_DIRECTION:
            case THUMB_DIRECTION:
            case INDEX_METACARPAL_DIRECTION:
            case INDEX_INTERMEDIATE_DIRECTION:
            case INDEX_PROXIMAL_DIRECTION:
            case INDEX_DISTAL_DIRECTION:
            case MIDDLE_METACARPAL_DIRECTION:
            case MIDDLE_INTERMEDIATE_DIRECTION:
            case MIDDLE_PROXIMAL_DIRECTION:
            case MIDDLE_DISTAL_DIRECTION:
            case RING_METACARPAL_DIRECTION:
            case RING_INTERMEDIATE_DIRECTION:
            case RING_PROXIMAL_DIRECTION:
            case RING_DISTAL_DIRECTION:
            case PINKY_METACARPAL_DIRECTION:
            case PINKY_INTERMEDIATE_DIRECTION:
            case PINKY_PROXIMAL_DIRECTION:
            case PINKY_DISTAL_DIRECTION:
            case THUMB_INTERMEDIATE_DIRECTION:
            case THUMB_PROXIMAL_DIRECTION:
            case THUMB_DISTAL_DIRECTION:
                //value should be Vector. compare against VectorRange
                if(!value.getClass().getName().equals(Vector.class.getName())){
                    throw new Exception("Attribute " + attribute + " must be given a Vector to be compared against");
                }
                ArrayList<DecisionTreeNode> possibleNodes = new ArrayList();
                attributeValueToOutcome.forEach((compare, node)-> {
                    VectorRange compareTo = (VectorRange)compare;
                    if(compareTo.contains((Vector)value)){
                        possibleNodes.add(node);
                    }
                });
                if(possibleNodes.isEmpty()){
                    toReturn = null;
                } else if(possibleNodes.size() == 1){
                    toReturn = possibleNodes.get(0);
                } else {
                    throw new Exception("Multiple viable nodes found in tree for attribute <" + attribute + "> with value of <" + value + ">");
                }
//            case PINCH_DISTANCE:
//                //value should be Float. compare against DistanceRange
//                if(!value.getClass().getName().equals(float.class.getName()) && !value.getClass().getName().equals(Float.class.getName())){
//                    throw new Exception("Attribute " + attribute + " must be given a float to be compared against");
//                }
//                ArrayList<DecisionTreeNode> nodes = new ArrayList();
//                attributeValueToOutcome.forEach((compare, node)-> {
//                    DistanceRange compareTo = (DistanceRange)compare;
//                    if(compareTo.contains((float)value)){
//                        nodes.add(node);
//                    }
//                });
//                if(nodes.isEmpty()){
//                    toReturn = null;
//                } else if(nodes.size() == 1){
//                    toReturn = nodes.get(0);
//                } else {
//                    throw new Exception("Multiple viable nodes found in tree for attribute <" + attribute + "> with value of <" + value + ">");
//                }
        }
        return toReturn;
    }
    
    public String toString(){
        return this.attribute + ": " + this.attributeValueToOutcome;
    }
}
