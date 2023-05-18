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
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenCol= 16;
    public final int maxScreenRow= 12;
    public final int screenWidth= tileSize*maxScreenCol;
    public final int screenHeight= tileSize*maxScreenRow;
    keyHandler keyH= new keyHandler(this);
    public ui ui=new ui(this);
    Thread gameThread;
    int playerX=100,playerY=100,playerSpeed=4;
    public final int maxWorldCol=23;
    public final int maxWorldRow=23;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    public entity[] npc=new entity[10];
    public assetSetter aSetter= new assetSetter(this);
    int fps=60;
    tileManager tileM= new tileManager(this);
    public superObject[] obj=new superObject[10];//10 object secara bersamaan
    sound sound = new sound();
    collisionChecker colCheck=new collisionChecker(this);
    public player player = new player(this,keyH);
    public int gameState;
    public final int playState=1;
    public final int pauseState=2;
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
        setupGame();
        gameState=playState;
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
            player.update();
        
            for(int i=0; i<npc.length; i++){
                if(npc[i]!=null){
                    npc[i].update();
                }
            }
        }else if(gameState==pauseState){
            
        }
        
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
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
        
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        g2.drawString("Player Life", 10, 20);
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        g2.drawString("Enemy Life", tileSize*maxScreenRow, 20);
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
    }
}
