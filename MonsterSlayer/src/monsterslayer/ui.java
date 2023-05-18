/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.obj_heart;
import object.superObject;

/**
 *
 * @author kent
 */
public class ui {
    gamePanel gp;
    Font courier;
    Graphics2D g2;
    BufferedImage heart_full,heart_half,heart_blank;
    public boolean messageOn=false;
    public String message="";
    public int messageCounter=0;
    
    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");
    
    public ui(gamePanel gp){
        this.gp=gp;
        courier=new Font("Courier", Font.BOLD, 100);
        
        superObject heart=new obj_heart(gp);//polymorph
        heart_full=heart.image;
        heart_half=heart.image1;
        heart_blank=heart.image2;
    }
    
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(courier);
        g2.setColor(white);
        if(gp.gameState==gp.playState){
            drawPlayerLife();
            drawEnemyLife();
        }else if(gp.gameState==gp.pauseState){
            drawWhenScreenIsPaused();
        }
    }
    
    public void drawWhenScreenIsPaused(){
        String text="PAUSED";
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public void drawPlayerLife(){
        // letak heart
        int x=gp.tileSize/3;
        int y=gp.tileSize/2;
        int i=0;
        while(i<gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x+=gp.tileSize;
        }
        x=gp.tileSize/3;
        y=gp.tileSize/2;
        i=0;
        while(i<gp.player.life){
            g2.drawImage(heart_half, x, y,null);
            i++;
            if(i<gp.player.life){
                //kalau masuk ganti half heart ke full heart
                g2.drawImage(heart_full, x, y,null);
            }
            i++;
            x+=gp.tileSize;
        
        }
    }
    
    public void drawEnemyLife(){
        // letak heart
        int x=gp.tileSize*gp.maxScreenRow;
        int y=gp.tileSize/2;
        int i=0;
        while(i<gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x+=gp.tileSize;
        }
        x=gp.tileSize*gp.maxScreenRow;
        y=gp.tileSize/2;
        i=0;
        while(i<gp.player.life){
            g2.drawImage(heart_half, x, y,null);
            i++;
            if(i<gp.player.life){
                //kalau masuk ganti half heart ke full heart
                g2.drawImage(heart_full, x, y,null);
            }
            i++;
            x+=gp.tileSize;
        
        }
    }
}
