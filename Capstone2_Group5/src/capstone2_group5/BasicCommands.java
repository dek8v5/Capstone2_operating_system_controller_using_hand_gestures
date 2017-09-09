/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.awt.*;
import java.awt.event.InputEvent;

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
    
    private int KeyDelay;
    private Event KeyHeld = new Event("commandPerformed");
    private Event KeyPressed = new Event("commandPerformed");
    private Event KeyReleased = new Event("commandPerformed");
    
    private int MoveDelay;
    private Event MouseMoved = new Event("commandPerformed");
    
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
        
        try{
            
            Command = new Robot();
            
            Command.setAutoDelay(AutoDelay);    
            Command.setAutoWaitForIdle(false);  //no auto delay for now
            
        }catch(AWTException e){
            
            e.printStackTrace(System.out); //probably need to handle better eventually
            
        }
        
    }
    
    public BasicCommands(int autoDelay, int clickDelay, int keyDelay, int moveDelay){
        
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
        
        try{
            
            Command = new Robot();
            
            Command.setAutoDelay(AutoDelay);    
            Command.setAutoWaitForIdle(false);  //no auto delay for now
            
        }catch(AWTException e){
            
            e.printStackTrace(System.out); //probably need to handle better eventually
            
        }    
    
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
    
}
