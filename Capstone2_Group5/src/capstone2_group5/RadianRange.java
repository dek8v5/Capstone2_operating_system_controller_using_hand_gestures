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
public class RadianRange implements java.io.Serializable{
    public static Boolean debug = Capstone2_Group5.debug;
    static{
//        debug = ;
    }

    private final String rads = "rads";
    private Float lowerBound;
    private Float upperBound;
    private Float center;
    private Float range;
    private static final Float RADIANSPERCIRCLE = (float)(2*Math.PI);
    private static final Float MINRANGE = (float)(0.0001f * (180/Math.PI));

    public RadianRange(Float center, Float range) throws Exception {
        setBounds(center, range);
    }

    public Boolean contains(Float toCheck) {
        if (toCheck == null) {
            return false;
        }
        toCheck = preventNegative(toCheck) % RADIANSPERCIRCLE;
        Debugger.print("Checking that " + toCheck + " is between " + lowerBound + " and " + upperBound);
        if (lowerBound < upperBound || (lowerBound == upperBound && range == MINRANGE)) {
            //normal expected behavior
            return lowerBound <= toCheck && toCheck <= upperBound;
        } else {
            //when the bounds wrap around the 0/360 mark
            return toCheck >= lowerBound || toCheck <= upperBound;
        }
    }

    private Float preventNegative(Float num) {
        while (num < 0) {
            num += RADIANSPERCIRCLE;
        }
        return num;
    }
    
    private void setBounds(Float center, Float range) throws Exception{
        if (range < 0) {
            throw new Exception("Range must be >= 0");
        }
        center = (center) % RADIANSPERCIRCLE; //center can be anywhere on full circle
        this.center = center;
        //never allow range to go below .0001 
        //this allows for the error in float calculations to still give good results
        range = Math.max(MINRANGE, Math.min(RADIANSPERCIRCLE/2, range));   //range can extend 180 degrees in either direction.
        this.range = range;

        // range of 0 means must be exactly on the center
        // range of PI means can it be anywhere in the circle
        lowerBound = preventNegative(center - range) % RADIANSPERCIRCLE;
        upperBound = preventNegative(center + range) % RADIANSPERCIRCLE;
    }
    
    public void setCenter(Float newCenter) throws Exception{
        setBounds(newCenter, range);
    }
    
    public void setRange(Float newRange) throws Exception{
        setBounds(center, newRange);
    }

    @Override
    public String toString() {
        return "{" + lowerBound + " " + rads + " - " + upperBound + " " + rads + "}";
    }
}
