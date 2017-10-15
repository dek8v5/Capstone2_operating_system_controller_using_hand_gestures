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
public class DistanceRange extends JSON{
    private Float lowerBound;
    private Float upperBound;
    private Float center;
    private Float range;
    
    public DistanceRange(Float center, Float range) throws Exception{
        setBounds(center, range);
    }
    
    public Boolean contains(Float toCheck){
//        Debugger.print("Checking that " + toCheck + " is within the range " + lowerBound + " - " + upperBound);
        return lowerBound <= toCheck && toCheck <= upperBound;
    }
    
    private void setBounds(Float center, Float range) throws Exception{
        if(center >= 0 && range >= 0){
            this.center = center;
            this.range = range;
            lowerBound = Math.max(0, center - range);
            upperBound = center + range;
        } else {
            throw new Exception("Invalid center <" + center + "> or range <" + range + ">");
        }
    }
    
    public void setCenter(Float newCenter) throws Exception{
        setBounds(newCenter, range);
    }
    
    public void setRange(Float newRange) throws Exception{
        setBounds(center, newRange);
    }
    
    @Override
    public String toString(){
        return "{" + lowerBound + " - " + upperBound + "}";
    }
}
