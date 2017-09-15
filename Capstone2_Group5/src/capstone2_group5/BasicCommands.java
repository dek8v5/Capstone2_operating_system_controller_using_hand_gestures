/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author alec
 */
public class BasicCommands {
    
    private int AutoDelay;
    
    private Event Click = new Event("commandPerformed");
    private int ClickDelay;
    private Event ClickHeld = new Event("commandPerformed");
    private Event ClickReleased = new Event("commandPerformed");
    
    private Robot Command;
        
    private int CurrentX;
    private int CurrentY;
    
    private int KeyDelay;
    private Event KeyHeld = new Event("commandPerformed");
    private Event KeyPressed = new Event("commandPerformed");
    private Event KeyReleased = new Event("commandPerformed");
    
    private int MoveDelay;
    private Event MouseMoved = new Event("commandPerformed");

    private int pMulti;
    private boolean rightHand;
    
    private int TrackY;
    private int TrackX;
    
    private int yCenter;
    private boolean zAxis;
    
    private double JoySens;
    
    public BasicCommands() {
        
        Click.addDetail("command", "click");
        ClickHeld.addDetail("command", "click held");
        ClickReleased.addDetail("command", "click released");
        
        KeyPressed.addDetail("command", "key pressed");
        KeyHeld.addDetail("command", "key held");
        KeyReleased.addDetail("command", "key released");
    
        MouseMoved.addDetail("command", "mouse moved");
        
        AutoDelay = 50;     //Default Values can change later
        ClickDelay = 250;
        KeyDelay = 250;
        MoveDelay = 50;
        
        yCenter = 100;
        pMulti = 2;
        
        rightHand = true;
        zAxis = true;
        
        JoySens = 2;
        
        try{
            
            Command = new Robot();
            
            Command.setAutoDelay(AutoDelay);    
            Command.setAutoWaitForIdle(false);  //no auto delay for now
            
        }catch(AWTException e){
            
            e.printStackTrace(System.out); //probably need to handle better eventually
            
        }
        
        Point b = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Point a = MouseInfo.getPointerInfo().getLocation();
        
        CurrentX = (int)b.x;
        CurrentY = (int)b.y; 
        TrackX = (int)a.getX();
        TrackY = (int)a.getY();

        
    }
    
    public BasicCommands(int autoDelay, int clickDelay, int keyDelay, int moveDelay, int ycenter, int pmulti, boolean hand, boolean axis, double joysens){
        
        Click.addDetail("command", "click");
        ClickHeld.addDetail("command", "click held");
        ClickReleased.addDetail("command", "click released");
        
        KeyPressed.addDetail("command", "key pressed");
        KeyHeld.addDetail("command", "key held");
        KeyReleased.addDetail("command", "key released");
    
        MouseMoved.addDetail("command", "mouse moved");
        
        AutoDelay = autoDelay;      //for user config
        ClickDelay = clickDelay;
        KeyDelay = keyDelay;
        MoveDelay = moveDelay;
        
        yCenter = ycenter;
        pMulti = pmulti;
        
        rightHand = hand;
        zAxis = axis;
        
        JoySens = joysens;
        
        try{
            
            Command = new Robot();
            
            Command.setAutoDelay(AutoDelay);    
            Command.setAutoWaitForIdle(false);  //no auto delay for now
            
        }catch(AWTException e){
            
            e.printStackTrace(System.out); //probably need to handle better eventually
            
        }    
        
        Point b = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Point a = MouseInfo.getPointerInfo().getLocation();
        
        CurrentX = (int)b.x;
        CurrentY = (int)b.y;
        TrackX = (int)a.getX();
        TrackY = (int)a.getY();

    
    }
    
    public void Click(int button){
        
        Command.mousePress(button);
        Command.delay(ClickDelay);
        Command.mouseRelease(button);
        Command.delay(ClickDelay);
        
        Click.trigger();
    }
    
    public void HoldClick(int button){
    
        Command.mousePress(button);
        Command.delay(ClickDelay);
    
        ClickHeld.trigger();
        
    }
    
    public void ReleaseClick(int button){
    
        Command.mouseRelease(button);
        Command.delay(ClickDelay);
    
        ClickReleased.trigger();
    }
    
    public void LeftClick(){
    
        this.Click(InputEvent.BUTTON1_MASK);
        
    }
    
    public void RightClick(){
    
        this.Click(InputEvent.BUTTON2_MASK);
        
    }
    
    public void HoldLeftClick(){
    
        this.HoldClick(InputEvent.BUTTON1_MASK);
        
    }
    
    public void ReleaseLeftClick(){
    
        this.ReleaseClick(InputEvent.BUTTON1_MASK);
    }
    
    public void PressKey(int keycode){
    
        Command.keyPress(keycode);
        Command.delay(KeyDelay);
        Command.keyRelease(keycode);
        Command.delay(KeyDelay);
        
        KeyPressed.trigger();
        
    }
    
    public void HoldKey(int keycode){
        
        Command.keyPress(keycode);
        Command.delay(KeyDelay);
        
        KeyHeld.trigger();
    
    }
    
    public void ReleaseKey(int keycode){
    
        Command.keyRelease(keycode);
        Command.delay(KeyDelay);
        
        KeyReleased.trigger();
    }
    
    public void MoveMouse(int x, int y){
    
        Command.mouseMove(x, y);
        Command.delay(MoveDelay);
        
        MouseMoved.trigger();
    }
    
    public void MoveMouseJoystick(com.leapmotion.leap.Frame frame){
        
        HandList hands = frame.hands();
        Hand myHand;
        
        if(rightHand == true){
            myHand = hands.rightmost();
        }else{
            myHand = hands.leftmost();
        }
        
        Vector pos = myHand.palmPosition();
        
        int x_pos = (int)(pos.getX()/JoySens);
        int y_pos = (int)(pos.getY()/JoySens);
        int z_pos = (int)(pos.getZ()/JoySens);
        
        if(zAxis == true){
            MoveMouse(CurrentX + x_pos, CurrentY + z_pos);
            CurrentX = CurrentX + x_pos;
            CurrentY = CurrentY + z_pos;
        }else{
            MoveMouse(CurrentX + x_pos, CurrentY - (y_pos-yCenter));
            CurrentX = CurrentX + x_pos;
            CurrentY = CurrentY - (y_pos-yCenter);
        }
        
    }
    
    public void MoveMousePad(com.leapmotion.leap.Frame frame){
    
        HandList hands = frame.hands();
        Hand myHand;
        
        if(rightHand == true){
            myHand = hands.rightmost();
        }else{
            myHand = hands.leftmost();
        }
        
        Vector pos = myHand.palmPosition();
        
        int x_pos = (int)pos.getX();
        int y_pos = (int)pos.getY();
        int z_pos = (int)pos.getZ();
        
        if(zAxis == true){
            MoveMouse(CurrentX + pMulti*x_pos, CurrentY + pMulti*(z_pos));
        }else{
            MoveMouse(CurrentX + pMulti*x_pos, CurrentY - pMulti*(y_pos-yCenter));
        }
    }
    
    public void MoveMouseTrack(com.leapmotion.leap.Frame frame){
    
        HandList hands = frame.hands();
        Hand myHand;
        
        if(rightHand == true){
            myHand = hands.rightmost();
        }else{
            myHand = hands.leftmost();
        }
        
        Vector pos = myHand.palmPosition();
        
        int x_pos = (int)pos.getX();
        int y_pos = (int)pos.getY();
        int z_pos = (int)pos.getZ();
        
        if(zAxis == true){
            MoveMouse(TrackX + pMulti*x_pos, TrackY + pMulti*(z_pos));
        }else{
            MoveMouse(TrackX + pMulti*x_pos, TrackY - pMulti*(y_pos-yCenter));
        }
           
    }
    
    public void MouseTrackUpdate(){
    
        Point a = MouseInfo.getPointerInfo().getLocation();

        TrackX = (int)a.getX();
        TrackY = (int)a.getY();
    }
    
    public int GetKeycode(KeyEvent a){
    
        int keycode = a.getKeyCode();
        return keycode;
    }
}
