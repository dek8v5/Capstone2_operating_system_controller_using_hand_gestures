/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import com.leapmotion.leap.Vector;

/**
 *
 * @author Cameron
 */
public class VectorRange implements ToJson{
    private final RadianRange xRange;
    private final RadianRange yRange;
    private final RadianRange zRange;
    
    public VectorRange(Float xCenter, Float xRange, Float yCenter, Float yRange, Float zCenter, Float zRange) throws Exception{
        this.xRange = new RadianRange(xCenter, xRange);
        this.yRange = new RadianRange(yCenter, yRange);
        this.zRange = new RadianRange(zCenter, zRange);
    }
    
    public VectorRange(Vector vector, Float range) throws Exception{
        this.xRange = new RadianRange(vector.getX(), range);
        this.yRange = new RadianRange(vector.getY(), range);
        this.zRange = new RadianRange(vector.getZ(), range);
    }
    
    private Boolean contains(Float x, Float y, Float z){
        return xRange.contains(x) && yRange.contains(y) && zRange.contains(z);
    }
    
    public Boolean contains(Vector vector){
        return this.contains(vector.getX(), vector.getY(), vector.getZ());
    }
    
    private void setAllCenters(Float x, Float y, Float z) throws Exception{
        setXCenter(x);
        setYCenter(y);
        setZCenter(z);
    }

    public void setCenter(Vector vector) throws Exception{
        setAllCenters(vector.getX(), vector.getY(), vector.getZ());
    }
    
    public void setAllRanges(Float range) throws Exception{
        setXRange(range);
        setYRange(range);
        setZRange(range);
    }
    
    public void setAllRanges(Float x, Float y, Float z) throws Exception{
        setXRange(x);
        setYRange(y);
        setZRange(z);
    }
    
    private void setXCenter(Float x) throws Exception{
        xRange.setCenter(x);
    }
    
    public void setXRange(Float x) throws Exception{
        xRange.setRange(x);
    }
    
    private void setYCenter(Float y) throws Exception{
        yRange.setCenter(y);
    }
    
    public void setYRange(Float y) throws Exception{
        yRange.setRange(y);
    }
    
    private void setZCenter(Float z) throws Exception{
        zRange.setCenter(z);
    }
    
    public void setZRange(Float z) throws Exception{
        zRange.setRange(z);
    }
    
    @Override
    public String toString(){
        return "{x: " + xRange + ", y: " + yRange + ", z: " + zRange + "}";
    }

    @Override
    public String toJsonString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
