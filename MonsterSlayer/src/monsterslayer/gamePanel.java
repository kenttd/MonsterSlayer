/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author kent
 */
public class gamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;
    final int maxScreenCol= 16;
    final int maxScreenRow= 12;
    final int screenWidth= tileSize*maxScreenCol;
    final int screenHeight= tileSize*maxScreenRow;
    keyHandler keyH= new keyHandler();
    Thread gameThread;
    int playerX=100,playerY=100,playerSpeed=4;
    
    int fps=60;
    player player = new player(this,keyH);
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
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
        player.update();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
