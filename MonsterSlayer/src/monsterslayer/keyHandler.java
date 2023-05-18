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
    boolean up,down,left,right,space,flag;
    int upcounterrr,downcounterr,leftcounterr,upcounterr;
    int soundCounter=0;
    sound sound = new sound();
    gamePanel gp;
    public keyHandler(gamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            soundCounter++;
            if(soundCounter==1){
                playMusic(1);
                
            }
            up=true;
        }else if(code==KeyEvent.VK_A){
            soundCounter++;
            if(soundCounter==1){
                playMusic(1);
                
            }
//            playMusic(1);
            left=true;
        }else if(code==KeyEvent.VK_S){
            soundCounter++;
            if(soundCounter==1){
                playMusic(1);
                
            }
//            playMusic(1);
            down=true;
        }else if(code==KeyEvent.VK_D){
            soundCounter++;
            if(soundCounter==1){
                playMusic(1);
                
            }
//            playMusic(1);
            right=true;
        }else if(code==KeyEvent.VK_SPACE){
            space=true;
            playSFX(2);
        }else if(code==KeyEvent.VK_ESCAPE){
            if(gp.gameState==gp.playState){
                gp.gameState=gp.pauseState;
            }else if(gp.gameState==gp.pauseState){
                gp.gameState=gp.playState;
            }
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            sound.stop();
            soundCounter=0;
            up=false;
        }else if(code==KeyEvent.VK_A){
            sound.stop();
            soundCounter=0;
            left=false;
        }else if(code==KeyEvent.VK_S){
            sound.stop();
            soundCounter=0;
            down=false;
        }else if(code==KeyEvent.VK_D){
            sound.stop();
            soundCounter=0;
            right=false;
        }else if(code==KeyEvent.VK_SPACE){
            space=false;
            flag=false;
        }
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    
    public void playSFX(int i){
        sound.setFile(i);
        sound.play();
    }
    
}
