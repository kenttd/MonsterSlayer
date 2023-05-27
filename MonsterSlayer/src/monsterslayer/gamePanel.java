/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import object.superObject;
import tiles.tileManager;

/**
 *
 * @author kent
 */
public class gamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    private int counterGameOver=0;
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenCol= 16;
    public final int maxScreenRow= 12;
    public final int screenWidth= tileSize*maxScreenCol;
    public final int screenHeight= tileSize*maxScreenRow;
    keyHandler keyH= new keyHandler(this);
    mouseHandler mouseH= new mouseHandler(this);
    public ui ui=new ui(this);
    Thread gameThread;
    quest quest= new quest(this);
    int playerX=100,playerY=100,playerSpeed=4;
    public final int maxWorldCol=38;
    public final int maxWorldRow=37;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    public entity[] npc=new entity[10];
    public assetSetter aSetter= new assetSetter(this);
    int fps=60;
    tileManager tileM= new tileManager(this);
    public superObject[] obj=new superObject[20];//2 object secara bersamaan
    sound sound = new sound();
    collisionChecker colCheck=new collisionChecker(this);
    public player player = new player(this,keyH);
    public int gameState;
    public final int playState=1;
    public final int pauseState=2;
    public final int titleState=0;
    public final int gameOverState=3;
    public final int afterGameOverState=4;
    public final int highScoreState=5;
    public final int questState=6;
    public final int shopState=7;
    public final int confSaveState=8;
    public final int loadFileState=9;
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);
        this.requestFocusInWindow();
        setupGame();
//        gameState=titleState;
        gameState=titleState;
    }
    
    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
        playMusic(0);
        
    }
    @Override
    public void run() {
        double drawInterval=1000000000/fps;
        double nextDrawTime= System.nanoTime()+drawInterval;
        while(gameThread !=null){
            
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime-System.nanoTime();
                remainingTime/=10000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException ex) {
                Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void update(){
        if(gameState==playState){
            quest.updateQuest();
            player.update();
        
            for(int i=0; i<npc.length; i++){
                if(npc[i]!=null){
                    npc[i].update();
                    npc[i].checkIfEnemyIsDead(i);
                }
            }
            for(int i=0; i<obj.length; i++){
                
            }
            aSetter.updatePotion();
        }else if(gameState==pauseState){
            
        }
        
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        
        if(gameState==titleState){
            ui.draw(g2);
        }else if(gameState==playState){
            tileM.draw(g2);
            //item
            for(int i=0; i<obj.length; i++){
                if(obj[i]!=null){
                    obj[i].draw(g2, this);
                }
            }
            for(int i=0; i<npc.length; i++){
                if(npc[i]!=null){
                    npc[i].draw(g2);
                }
            }
            player.draw(g2);

            ui.draw(g2);
            g2.setFont(new Font("Courier", Font.BOLD, 30));
            g2.setColor(Color.WHITE);
            g2.drawString("Score: "+player.score,tileSize/3 , tileSize*maxScreenRow-(tileSize/2));
            if(player.pickedRed&&ui.getRedPotionC()<=180){
                ui.ifPlayerPickedUpRedPotion();
                ui.setRedPotionC(ui.getRedPotionC()+1);
            }
            else if(ui.getRedPotionC()>180){
                ui.setRedPotionC(0);
                player.pickedRed=false;
            }
            
            
        }else if(gameState==gameOverState){
            counterGameOver++;
            if(counterGameOver==1){
                ui.draw(g2);
            }
            
        }
        ui.draw(g2);
        g2.dispose();
    }
    
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    
    public void stopMusic(){
        sound.stop();
    }
    
    public void playSFX(int i){
        sound.setFile(i);
        sound.play();
        
    }
    
    public void setupGame(){
        aSetter.setObject();
        
        aSetter.setEnemyCat();
        aSetter.setEnemySlime();
        aSetter.setEnemyBoss();
        
    }
}
