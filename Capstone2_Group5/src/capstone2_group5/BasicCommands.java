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
    private int primaryMouseClickDelay;
    private Robot robot;
    private int screenX;
    private int screenY;
    private int keyPressDelay;
    private int mouseMovementDelay;
    private int pixelMultiplier;
    private int mousePositionY;
    private int mousePositionX;
    private int yPosCalibration;
    private boolean useZAxis;
    
    private int handCurrentX;
    private int handCurrentY;
    private int handCurrentZ;
    
    private double joyStickSensitivity;
    
    public BasicCommands() {
        
        autoDelay = 50;     //Default Values can change later
        primaryMouseClickDelay = 250;
        keyPressDelay = 250;
        mouseMovementDelay = 1;
        yPosCalibration = 200;
        pixelMultiplier = 2;
        
//        rightHand = true;
        useZAxis = true;
        
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
        
        screenX = (int)b.x;
        screenY = (int)b.y; 
        mousePositionX = (int)a.getX();
        mousePositionY = (int)a.getY();

        
    }
    
    public BasicCommands(int autoDelay, int clickDelay, int keyDelay, int moveDelay, int ycenter, int pmulti, boolean hand, boolean axis, double joysens){
        
        this.autoDelay = autoDelay;      //for user config
        primaryMouseClickDelay = clickDelay;
        keyPressDelay = keyDelay;
        this.mouseMovementDelay = moveDelay;
        
        yPosCalibration = ycenter;
        pixelMultiplier = pmulti;
        
//        rightHand = hand;
        useZAxis = axis;
        
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
        
        screenX = (int)b.x;
        screenY = (int)b.y;
        mousePositionX = (int)a.getX();
        mousePositionY = (int)a.getY();

    
    }
    
    private void click(int button){
        
        robot.mousePress(button);
        robot.delay(primaryMouseClickDelay);
        robot.mouseRelease(button);
        robot.delay(primaryMouseClickDelay);
        
    }
    
    private void holdClick(int button){
    
        robot.mousePress(button);
        robot.delay(primaryMouseClickDelay);

        
    }
    
    private void releaseClick(int button){
    
        robot.mouseRelease(button);
        robot.delay(primaryMouseClickDelay);
    
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
        
        
    }
    
    private void holdKeyDown(int keycode){
        
        robot.keyPress(keycode);
        robot.delay(keyPressDelay);
        
    
    }
    
    private void releaseKey(int keycode){
    
        robot.keyRelease(keycode);
        robot.delay(keyPressDelay);
        
    }
    
    private void moveMouse(int x, int y){
    
        robot.mouseMove(x, y);
        robot.delay(mouseMovementDelay);
        
    }
    
    private void moveJoyStick(){
        
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

        int x_pos = this.handCurrentX;
        int y_pos = this.handCurrentY;
        int z_pos = this.handCurrentZ;
        
        if(useZAxis == true){
            moveMouse(screenX + x_pos, screenY + z_pos);
            screenX = screenX + x_pos;
            screenY = screenY + z_pos;
        }else{
            moveMouse(screenX + x_pos, screenY - (y_pos-yPosCalibration));
            screenX = screenX + x_pos;
            screenY = screenY - (y_pos-yPosCalibration);
        }
        
    }
    
    private void moveMousePad(){
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
        
        int x_pos = this.handCurrentX;
        int y_pos = this.handCurrentY;
        int z_pos = this.handCurrentZ;
        
        if(useZAxis == true){
            moveMouse(screenX + pixelMultiplier*(int)x_pos, screenY + pixelMultiplier*(int)z_pos);
        }else{
            moveMouse(screenX + pixelMultiplier*(int)x_pos, screenY - pixelMultiplier*(int)(y_pos - yPosCalibration));
        }
    }
    
    private void moveMouseTrack(){
    
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
        int x_pos = this.handCurrentX;
        int y_pos = this.handCurrentY;
        int z_pos = this.handCurrentZ;
        
        if(useZAxis == true){
            moveMouse(mousePositionX + pixelMultiplier*(int)x_pos, mousePositionY + pixelMultiplier*(int)(z_pos));
        }else{
            moveMouse(mousePositionX + pixelMultiplier*(int)x_pos, mousePositionY - pixelMultiplier*(int)(y_pos - yPosCalibration));
        }
           
    }
    
    public void mouseScroll(){
    
    
    
    }
    
    public void mouseTrackUpdate(){
    
        Point a = MouseInfo.getPointerInfo().getLocation();

        mousePositionX = (int)a.getX();
        mousePositionY = (int)a.getY();
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
                this.primaryClick();
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
                this.secondaryClick();
                break;
            case MOUSE_SCROLL_DOWN:
                break;
            case MOUSE_SCROLL_UP:
                break;
            case MOUSE_MOVE:
                this.moveMousePad();
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

    @Override
    public void updateHandPosition(Integer x, Integer y, Integer z) {
        this.handCurrentX = x;
        this.handCurrentY = y;
        this.handCurrentZ = z;
    }
}
