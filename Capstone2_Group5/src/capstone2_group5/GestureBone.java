/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import com.leapmotion.leap.Bone;

/**
 *
 * @author Cameron
 */
public class GestureBone implements java.io.Serializable{
    public static Boolean debug = Capstone2_Group5.debug;
    private Bone.Type type;
    public VectorRange allowedDirection;
    
    private GestureBone(){
        //prevent calling constructor with no type
    }
            
    public GestureBone(Bone.Type type){
        this.type = type;
    }
    
    public Bone.Type getType(){
        return type;
    }
    
}
