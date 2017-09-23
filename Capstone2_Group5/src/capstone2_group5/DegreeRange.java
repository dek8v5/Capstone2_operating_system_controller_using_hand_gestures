/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

/**
 *
 * @author Cameron
 */
public class DegreeRange {
    private final Integer lowerBound;
    private final Integer upperBound;
    
    public DegreeRange(Integer center, Integer range){
        center = center%360; //center can be anywhere on full circle
        range = range%180;   //range can extend 180 degrees in either direction.
           // range of 0 means must be exactly on the center
           // range of 180 means can be anywhere in the circle
        lowerBound = (center - range)%360;
        upperBound = (center + range)%360;
    }
    
    public Boolean contains(Integer toCheck){
        toCheck = toCheck%360;
        if(lowerBound <= upperBound){
            //normal expected behavior
            return lowerBound <= toCheck && toCheck <= upperBound;
        } else {
            //when the bounds wrap around the 0/360 mark
            return toCheck >= lowerBound || toCheck <= upperBound;
        }
    }
}
