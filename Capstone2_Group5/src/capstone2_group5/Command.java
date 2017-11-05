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
public enum Command {
    MOUSE_PRIMARY_DOWN,
    MOUSE_PRIMARY_UP,
    MOUSE_PRIMARY_CLICK,
    MOUSE_SECONDARY_DOWN,
    MOUSE_SECONDARY_UP,
    MOUSE_SECONDARY_CLICK,
    MOUSE_MOVE,
    MOUSE_SCROLL,
    KEY_DOWN,
    KEY_HELD_DOWN,
    KEY_UP;

    
    public String toTableString(){
        return this.name().replace("_", " ");

    }

}
