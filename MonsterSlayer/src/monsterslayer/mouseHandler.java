/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kent
 */
public class mouseHandler implements MouseListener,MouseMotionListener{
    gamePanel gp;
    int x,y;
    private boolean gameHover=false,highHover=false,quitHover=false,backHighHover=false,pauseHover=false,saveHover=false;
    public mouseHandler(gamePanel gp){
        this.gp=gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        if(gp.gameState==gp.titleState){
            if(y>=300&&y<=345&&x>=285&&x<=485){
                gp.gameState=gp.playState;
            }else if(y>=345&&y<=415&&x>=245&&x<=520){
                gp.gameState=gp.highScoreState;
            }else if(y>=435&&y<=480&&x>=325&&x<=445){
                System.exit(0);
            }
        }
        else if(gp.gameState==gp.highScoreState){
            if(y>=470&&y<=500&&x>=65&&x<=160){
                gp.gameState=gp.titleState;
            }
        }else if(gp.gameState==gp.playState){
            if(y>=530&&y<=570&&x>=720&&x<=765){
                gp.gameState=gp.pauseState;
            }
        }else if(gp.gameState==gp.pauseState){
            if(y>=530&&y<=570&&x>=720&&x<=765){
                try
                {
                    String filename= "highScores.txt";
                    FileWriter fw = new FileWriter(filename,false); // tidak append tapi overwrite
                    fw.write(gp.player.life+" "+gp.player.score);//overwrite the string to the file
                    fw.close();
                }
                catch(IOException ioe)
                {
                    System.err.println("IOException: " + ioe.getMessage());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        x=e.getX();
        y=e.getY();
        if(y>=300&&y<=345&&x>=285&&x<=485){
            gameHover=true;
        }else gameHover=false;
        if(y>=435&&y<=480&&x>=325&&x<=445){
            quitHover=true;
        }else quitHover=false;
        if(y>=345&&y<=415&&x>=245&&x<=520){
            highHover=true;
        }else highHover=false;
        if(y>=470&&y<=500&&x>=65&&x<=160){
            backHighHover=true;
        }else backHighHover=false;
        if(y>=530&&y<=570&&x>=720&&x<=765&&gp.gameState==gp.playState){
            pauseHover=true;
        }else pauseHover=false;
        if(y>=530&&y<=570&&x>=720&&x<=765&&gp.gameState==gp.pauseState){
            saveHover=true;
        }else saveHover=false;
        System.out.println("x: "+x+", y"+y);
    }

    public boolean isGameHover() {
        return gameHover;
    }

    public void setGameHover(boolean gameHover) {
        this.gameHover = gameHover;
    }

    public boolean isHighHover() {
        return highHover;
    }

    public void setHighHover(boolean highHover) {
        this.highHover = highHover;
    }

    public boolean isQuitHover() {
        return quitHover;
    }

    public void setQuitHover(boolean quitHover) {
        this.quitHover = quitHover;
    }

    public boolean isBackHighHover() {
        return backHighHover;
    }

    public void setBackHighHover(boolean backHighHover) {
        this.backHighHover = backHighHover;
    }

    public boolean isPauseHover() {
        return pauseHover;
    }

    public void setPauseHover(boolean pauseHover) {
        this.pauseHover = pauseHover;
    }

    public boolean isSaveHover() {
        return saveHover;
    }

    public void setSaveHover(boolean saveHover) {
        this.saveHover = saveHover;
    }
    
    
    
}
