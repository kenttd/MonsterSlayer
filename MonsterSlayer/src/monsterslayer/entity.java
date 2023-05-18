/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author kent
 */
public class entity {
    public int worldX,worldY;
    int speed;
    gamePanel gp;
    player player;
    public BufferedImage up1,up2,up3,up4,up5,up6;
    public BufferedImage down1,down2,down3,down4,down5,down6;
    public BufferedImage left1,left2,left3,left4,left5,left6;
    public BufferedImage right1,right2,right3,right4,right5,right6;
    public BufferedImage attup1,attup2,attup3,attup4;
    public BufferedImage attdown1,attdown2,attdown3,attdown4;
    public BufferedImage attleft1,attleft2,attleft3,attleft4;
    public BufferedImage attright1,attright2,attright3,attright4;
    public String direction;
    public boolean attack=false;
    public int spriteCounter=0;
    public int spriteNum=1;
    public int maxLife,life;
    
    public int actionLockCounter=0;
    public int enemyAttackCounter=0;
    public Rectangle solidArea=new Rectangle(60,64,32,20);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=false;
    
    public entity(gamePanel gp,player player){
        this.gp=gp;
        this.player=player;
    }

    public entity(gamePanel gp) {
        this.gp = gp;
    }
    
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX=worldX-gp.player.worldX+gp.player.screenX;
        int screenY=worldY-gp.player.worldY+gp.player.screenY;
        if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
            switch(direction){
                case "up":
                    if(spriteNum==1){
                        image=up1;
                    }else if(spriteNum==2){
                        image=up2;
                    }else if(spriteNum==3){
                        image=up3;
                    }else if(spriteNum==4){
                        image=up4;
                    }
                    break;
                case "down":
                    if(spriteNum==1){
                        image=down1;
                    }else if(spriteNum==2){
                        image=down2;
                    }else if(spriteNum==3){
                        image=down3;
                    }else if(spriteNum==4){
                        image=down4;
                    }
                    break;
                case "left":
                    if(spriteNum==1){
                        image=left1;
                    }else if(spriteNum==2){
                        image=left2;
                    }else if(spriteNum==3){
                        image=left3;
                    }else if(spriteNum==4){
                        image=left4;
                    }
                    break;
                case "right":
                    if(spriteNum==1){
                        image=right1;
                    }else if(spriteNum==2){
                        image=right2;
                    }else if(spriteNum==3){
                        image=right3;
                    }else if(spriteNum==4){
                        image=right4;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY,gp.tileSize*3,gp.tileSize*3,null);
        }
    }
    
    public void setAction(){
        
    }
    
    public void update(){
        setAction(); //panggil untuk gerakkan enemy
        collisionOn=false;
        gp.colCheck.checkTile(this);
        gp.colCheck.checkObject(this, false);
//        gp.colCheck.checkPlayer(this);
        if(!collisionOn){
            switch(direction){
                case"up":
                    worldY -= speed;
                    break;
                case"down":
                    worldY += speed;
                    break;
                case"left":
                    worldX -= speed;
                    break;
                case"right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}
