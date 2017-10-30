/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

/**
 *
 * @author Cameron
 */
public class MovementRecognizer implements GestureRecognizer{

    @Override
    public void scan(Frame frame) {
        if(frame == null){
            return;
        }
        Hand hand = frame.hands().frontmost();
        if(hand == null){
            return;
        }
        Vector position = hand.palmPosition();
        if(position == null){
            return;
        }
        Capstone2_Group5.getOSController().updateHandPosition((int)position.getX(), (int)position.getY(), (int)position.getZ());
    }
    
}
