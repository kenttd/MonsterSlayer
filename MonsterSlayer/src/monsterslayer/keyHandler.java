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
    boolean up,down,left,right,space,flagcat,flagslime,flagboss;
    int upcounterrr,downcounterr,leftcounterr,upcounterr;
    int soundCounter=0;
    private int pauseCounter=0;
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
        if(gp.gameState==gp.highScoreState){
            if(code==KeyEvent.VK_B){
                gp.gameState=gp.titleState;
            }
        }
        else if(gp.gameState==gp.titleState){
            if(code==KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum>2)gp.ui.commandNum=0;
                gp.playSFX(5);
            }else if(code==KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0)gp.ui.commandNum=2;
                gp.playSFX(5);
            }else if(code==KeyEvent.VK_ENTER){
                switch(gp.ui.commandNum){
                    case 0:
                        gp.gameState=gp.playState;
                        break;
                    case 1:
                        gp.gameState=gp.highScoreState;
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }
            }
        }
        
        if(gp.gameState==gp.playState){
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
            }else if(code==KeyEvent.VK_SHIFT){
                gp.player.speed+=3;
            }else if(code==KeyEvent.VK_Q){
                gp.gameState=gp.questState;
            }
        }
        if(gp.gameState==gp.questState){
            if(code==KeyEvent.VK_B){
                gp.gameState=gp.playState;
            }
        }
        if(code==KeyEvent.VK_ESCAPE){
            pauseCounter++;
            if(pauseCounter==1){
                gp.gameState=gp.pauseState;
            }else if(pauseCounter==2){
                gp.gameState=gp.playState;
                pauseCounter=0;
            }
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(gp.gameState==gp.playState){
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
                flagcat=false;
                flagslime=false;
                flagboss=false;
            }else if(code==KeyEvent.VK_SHIFT){
                gp.player.speed-=3;
            }
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
