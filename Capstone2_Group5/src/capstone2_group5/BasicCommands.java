/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author alec
 */
public class BasicCommands implements OSControl{
    
    private int autoDelay;
    
    private Event primaryMouseDown = new Event(Event.TYPE.COMMAND_PERFORMED);
    private int primaryMouseClickDelay;
    private Event primaryMouseHeldDown = new Event(Event.TYPE.COMMAND_PERFORMED);
    private Event primaryMouseUp = new Event(Event.TYPE.COMMAND_PERFORMED);
    
    private Robot robot;
        
    private int currentX;
    private int currentY;
    
    private int keyPressDelay;
    private Event keyHeldDown = new Event(Event.TYPE.COMMAND_PERFORMED);
    private Event keyDown = new Event(Event.TYPE.COMMAND_PERFORMED);
    private Event keyUp = new Event(Event.TYPE.COMMAND_PERFORMED);
    
    private int moveDelay;
    private Event mouseMoved = new Event(Event.TYPE.COMMAND_PERFORMED);

    private int pMulti;
    
    private int trackY;
    private int trackX;
    
    private int yCenter;
    private boolean zAxis;
    
    private double joyStickSensitivity;
    
    public BasicCommands() {
        
        primaryMouseDown.addDetail("command", Command.MOUSE_PRIMARY_DOWN);
        primaryMouseHeldDown.addDetail("command", Command.MOUSE_PRIMARY_HELD_DOWN);
        primaryMouseUp.addDetail("command", Command.MOUSE_PRIMARY_UP);
        
        keyDown.addDetail("command", Command.KEY_DOWN);
        keyHeldDown.addDetail("command", Command.KEY_HELD_DOWN);
        keyUp.addDetail("command", Command.KEY_UP);
    
        mouseMoved.addDetail("command", Command.MOUSE_MOVE);
        
        autoDelay = 50;     //Default Values can change later
        primaryMouseClickDelay = 250;
        keyPressDelay = 250;
        moveDelay = 1;
        
        yCenter = 200;
        pMulti = 2;
        
//        rightHand = true;
        zAxis = true;
        
        joyStickSensitivity = 2;
        
        try{
            
            robot = new Robot();
            
            robot.setAutoDelay(autoDelay);    
            robot.setAutoWaitForIdle(false);  //no auto delay for now
            
        }catch(AWTException e){
            
            e.printStackTrace(System.out); //probably need to handle better eventually
            
        }
        
        Point b = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Point a = MouseInfo.getPointerInfo().getLocation();
        
        currentX = (int)b.x;
        currentY = (int)b.y; 
        trackX = (int)a.getX();
        trackY = (int)a.getY();

        
    }
    
    public BasicCommands(int autoDelay, int clickDelay, int keyDelay, int moveDelay, int ycenter, int pmulti, boolean hand, boolean axis, double joysens){
        
        primaryMouseDown.addDetail("command", Command.MOUSE_PRIMARY_DOWN);
        primaryMouseHeldDown.addDetail("command", Command.MOUSE_PRIMARY_HELD_DOWN);
        primaryMouseUp.addDetail("command", Command.MOUSE_PRIMARY_UP);
        
        keyDown.addDetail("command", Command.KEY_DOWN);
        keyHeldDown.addDetail("command", Command.KEY_HELD_DOWN);
        keyUp.addDetail("command", Command.KEY_UP);
    
        mouseMoved.addDetail("command", Command.MOUSE_MOVE);
        
        this.autoDelay = autoDelay;      //for user config
        primaryMouseClickDelay = clickDelay;
        keyPressDelay = keyDelay;
        this.moveDelay = moveDelay;
        
        yCenter = ycenter;
        pMulti = pmulti;
        
//        rightHand = hand;
        zAxis = axis;
        
        joyStickSensitivity = joysens;
        
        try{
            
            robot = new Robot();
            
            robot.setAutoDelay(this.autoDelay);    
            robot.setAutoWaitForIdle(false);  //no auto delay for now
            
        }catch(AWTException e){
            
            e.printStackTrace(System.out); //probably need to handle better eventually
            
        }    
        
        Point b = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Point a = MouseInfo.getPointerInfo().getLocation();
        
        currentX = (int)b.x;
        currentY = (int)b.y;
        trackX = (int)a.getX();
        trackY = (int)a.getY();

    
    }
    
    private void click(int button){
        
        robot.mousePress(button);
        primaryMouseDown.trigger();
        robot.delay(primaryMouseClickDelay);
        robot.mouseRelease(button);
        robot.delay(primaryMouseClickDelay);
        
        primaryMouseDown.trigger();
    }
    
    private void holdClick(int button){
    
        robot.mousePress(button);
        robot.delay(primaryMouseClickDelay);
    
        primaryMouseHeldDown.trigger();
        
    }
    
    private void releaseClick(int button){
    
        robot.mouseRelease(button);
        robot.delay(primaryMouseClickDelay);
    
        primaryMouseUp.trigger();
    }
    
    private void primaryClick(){
    
        this.click(InputEvent.BUTTON1_MASK);
        
    }
    
    private void secondaryClick(){
    
        this.click(InputEvent.BUTTON2_MASK);
        
    }
    
    private void primaryClickHold(){
    
        this.holdClick(InputEvent.BUTTON1_MASK);
        
    }
    
    private void secondaryClickHold(){
        this.holdClick(InputEvent.BUTTON2_MASK);
    }
    
    private void primaryClickRelease(){
    
        this.releaseClick(InputEvent.BUTTON1_MASK);
    }
    
    private void secondaryClickRelease(){
        this.releaseClick(InputEvent.BUTTON2_MASK);
    }
    
    private void pressKeyDown(int keycode){
    
        robot.keyPress(keycode);
        robot.delay(keyPressDelay);
        robot.keyRelease(keycode);
        robot.delay(keyPressDelay);
        
        keyDown.trigger();
        
    }
    
    private void holdKeyDown(int keycode){
        
        robot.keyPress(keycode);
        robot.delay(keyPressDelay);
        
        keyHeldDown.trigger();
    
    }
    
    private void releaseKey(int keycode){
    
        robot.keyRelease(keycode);
        robot.delay(keyPressDelay);
        
        keyUp.trigger();
    }
    
    private void moveMouse(int x, int y){
    
        robot.mouseMove(x, y);
        robot.delay(moveDelay);
        
        mouseMoved.trigger();
    }
    
    private void moveJoyStick(double x, double y, double z){
        
//        HandList hands = frame.hands();
//        Hand myHand;
//        
//        if(rightHand == true){
//            myHand = hands.rightmost();
//        }else{
//            myHand = hands.leftmost();
//        }
//        
//        Vector pos = myHand.palmPosition();
//        
//        int x_pos = (int)(pos.getX()/joyStickSensitivity);
//        int y_pos = (int)(pos.getY()/joyStickSensitivity);
//        int z_pos = (int)(pos.getZ()/joyStickSensitivity);

        int x_pos = (int)(x/joyStickSensitivity);
        int y_pos = (int)(y/joyStickSensitivity);
        int z_pos = (int)(z/joyStickSensitivity);
        
        if(zAxis == true){
            moveMouse(currentX + x_pos, currentY + z_pos);
            currentX = currentX + x_pos;
            currentY = currentY + z_pos;
        }else{
            moveMouse(currentX + x_pos, currentY - (y_pos-yCenter));
            currentX = currentX + x_pos;
            currentY = currentY - (y_pos-yCenter);
        }
        
    }
    
    private void moveMousePad(double x, double y, double z){
//    
//        HandList hands = frame.hands();
//        Hand myHand;
//        
//        if(rightHand == true){
//            myHand = hands.rightmost();
//        }else{
//            myHand = hands.leftmost();
//        }
//        
//        Vector pos = myHand.palmPosition();
        
//        int x_pos = (int)pos.getX();
//        int y_pos = (int)pos.getY();
//        int z_pos = (int)pos.getZ();
        
        if(zAxis == true){
            moveMouse(currentX + pMulti*(int)x, currentY + pMulti*(int)z);
        }else{
            moveMouse(currentX + pMulti*(int)x, currentY - pMulti*(int)(y - yCenter));
        }
    }
    
    private void moveMouseTrack(double x, double y, double z){
    
//        HandList hands = frame.hands();
//        Hand myHand;
//        
//        if(rightHand == true){
//            myHand = hands.rightmost();
//        }else{
//            myHand = hands.leftmost();
//        }
//        
//        Vector pos = myHand.palmPosition();
//        
//        int x_pos = (int)pos.getX();
//        int y_pos = (int)pos.getY();
//        int z_pos = (int)pos.getZ();
        
        if(zAxis == true){
            moveMouse(trackX + pMulti*(int)x, trackY + pMulti*(int)(z));
        }else{
            moveMouse(trackX + pMulti*(int)x, trackY - pMulti*(int)(y - yCenter));
        }
           
    }
    
    public void mouseTrackUpdate(){
    
        Point a = MouseInfo.getPointerInfo().getLocation();

        trackX = (int)a.getX();
        trackY = (int)a.getY();
    }
    
    public int getKeyCode(KeyEvent a){
    
        int keycode = a.getKeyCode();
        return keycode;
    }

    @Override
    public void performCommand(Command command) {
//        MOUSE_PRIMARY_DOWN,
//        MOUSE_PRIMARY_HELD_DOWN,
//        MOUSE_PRIMARY_UP,
//        MOUSE_SECONDARY_DOWN,
//        MOUSE_SECONDARY_HELD_DOWN,
//        MOUSE_SECONDARY_UP,
//        MOUSE_MOVE,
//        MOUSE_SCROLL_UP,
//        MOUSE_SCROLL_DOWN,
//        KEY_DOWN,
//        KEY_HELD_DOWN,
//        KEY_UP,
        switch(command){
            case MOUSE_PRIMARY_DOWN:
                this.primaryClick();
                break;
            case MOUSE_PRIMARY_HELD_DOWN:
                this.primaryClickHold();
                break;
            case MOUSE_PRIMARY_UP:
                this.primaryClickRelease();
                break;
            case MOUSE_PRIMARY_CLICK:
                
                break;
            case MOUSE_SECONDARY_DOWN:
                this.secondaryClick();
                break;
            case MOUSE_SECONDARY_HELD_DOWN:
                this.secondaryClickHold();
                break;
            case MOUSE_SECONDARY_UP:
                this.secondaryClickRelease();
                break;
            case MOUSE_SECONDARY_CLICK:
                break;
            case MOUSE_SCROLL_DOWN:
                break;
            case MOUSE_SCROLL_UP:
                break;
            case MOUSE_MOVE:
                break;
            case KEY_DOWN:
//                this.pressKeyDown();
                break;
            case KEY_HELD_DOWN:
                break;
            case KEY_UP:
                break;
        }
    }
}
