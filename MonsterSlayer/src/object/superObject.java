/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import monsterslayer.gamePanel;
import monsterslayer.utilityTool;

/**
 *
 * @author kent
 */
public class superObject {
    private gamePanel gp;
    public BufferedImage image,image1,image2;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    utilityTool uTool = new utilityTool();
    public void draw(Graphics2D g2,gamePanel gp){
        int screenX=worldX-gp.player.worldX+gp.player.screenX;
        int screenY=worldY-gp.player.worldY+gp.player.screenY;
        if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
            g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
        }
    }
    public superObject(gamePanel gp){
        this.gp=gp;
    }
    
    public void update(){
        
    }
}
