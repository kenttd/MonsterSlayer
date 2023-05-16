/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kent
 */
public class keyHandler implements KeyListener{
    boolean up,down,left,right,space;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            up=true;
        }else if(code==KeyEvent.VK_A){
            left=true;
        }else if(code==KeyEvent.VK_S){
            down=true;
        }else if(code==KeyEvent.VK_D){
            right=true;
        }else if(code==KeyEvent.VK_SPACE){
            space=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            up=false;
        }else if(code==KeyEvent.VK_A){
            left=false;
        }else if(code==KeyEvent.VK_S){
            down=false;
        }else if(code==KeyEvent.VK_D){
            right=false;
        }else if(code==KeyEvent.VK_SPACE){
            space=false;
        }
    }
    
}
