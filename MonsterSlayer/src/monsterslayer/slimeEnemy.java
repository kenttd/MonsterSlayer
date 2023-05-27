/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author kent
 */
public class slimeEnemy extends entity{
    public slimeEnemy(gamePanel gp,player player) {
        super(gp,player);
        direction="down";
        speed=2;//kalau mau ganti kecepatan disini
        getImage();
//        solidArea.x=0;
//        solidArea.y=0;
//        solidArea.width=12;
//        solidArea.height=16;
        maxLife=3;
        life=maxLife;
    }
    public void getImage(){
        image1=setup("slime1");
        image2=setup("slime2");
        image3=setup("slime3");
        image4=setup("slime4");
        image5=setup("slime5");
        image6=setup("slime6");
        image7=setup("slime7");
    }
    
    public BufferedImage setup(String imageName){
        utilityTool uTool=new utilityTool();
        BufferedImage image=null;
        
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/enemySlime/"+imageName+".png"));
            image=uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return image;
    }
    
    public void setAction(){
        actionLockCounter++;
        Random random=new Random();
        int i=random.nextInt(100)+1;
        if(actionLockCounter==120){//ambil random per 120 frame(2 detik)
            if(i<=25){
            direction="up";
            }else if(i<=50){
                direction="down";
            }else if(i<=75){
                direction="left";
            }else{
                direction="right";
            }
            actionLockCounter=0;
        }
        if(checkIfThereIsPlayer(70)){
            enemyAttackCounter++;
            if(enemyAttackCounter==60){// menyerang setiap 1 detik
                System.out.println("enemy detect player");
                enemyAttackCounter=0;
                gp.player.life-=1;
                gp.playSFX(4);
            }
            
        }
    }
    
    public boolean checkIfThereIsPlayer(int distance){
        int dx =worldX - gp.player.worldX;
        int dy =worldY - gp.player.worldY;
        return Math.sqrt(dx * dx + dy * dy) <= distance;
    }
    
    @Override
    public void update(){
        actionLockCounter++;
        Random random=new Random();
        int i=random.nextInt(100)+1;
        if(actionLockCounter==120){//ambil random per 120 frame(2 detik)
            if(i<=25){
            direction="up";
            }else if(i<=50){
                direction="down";
            }else if(i<=75){
                direction="left";
            }else{
                direction="right";
            }
            actionLockCounter=0;
        }
        if(checkIfThereIsPlayer(70)){
            enemyAttackCounter++;
            if(enemyAttackCounter==60){// menyerang setiap 1 detik
                System.out.println("enemy detect player");
                enemyAttackCounter=0;
                gp.player.life-=1;
                gp.playSFX(4);
            }
            
        }
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
                    spriteNum = 5;
                }else if (spriteNum == 5) {
                    spriteNum = 6;
                } else if (spriteNum == 6) {
                    spriteNum = 7;
                } else if (spriteNum == 7) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
    }
    
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX=worldX-gp.player.worldX+gp.player.screenX;
        int screenY=worldY-gp.player.worldY+gp.player.screenY;
        if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&worldY-gp.tileSize<gp.player.worldY+gp.player.screenY&&!attack){
            switch(direction){
                case "up":
                    if(spriteNum==1){
                        image=image1;
                    }else if(spriteNum==2){
                        image=image2;
                    }else if(spriteNum==3){
                        image=image3;
                    }else if(spriteNum==4){
                        image=image4;
                    }else if(spriteNum==5){
                        image=image5;
                    }else if(spriteNum==6){
                        image=image6;
                    }else if(spriteNum==7){
                        image=image7;
                    }
                    break;
                case "down":
                    if(spriteNum==1){
                        image=image1;
                    }else if(spriteNum==2){
                        image=image2;
                    }else if(spriteNum==3){
                        image=image3;
                    }else if(spriteNum==4){
                        image=image4;
                    }else if(spriteNum==5){
                        image=image5;
                    }else if(spriteNum==6){
                        image=image6;
                    }else if(spriteNum==7){
                        image=image7;
                    }
                    break;
                case "left":
                    if(spriteNum==1){
                        image=image1;
                    }else if(spriteNum==2){
                        image=image2;
                    }else if(spriteNum==3){
                        image=image3;
                    }else if(spriteNum==4){
                        image=image4;
                    }else if(spriteNum==5){
                        image=image5;
                    }else if(spriteNum==6){
                        image=image6;
                    }else if(spriteNum==7){
                        image=image7;
                    }
                    break;
                case "right":
                    if(spriteNum==1){
                        image=image1;
                    }else if(spriteNum==2){
                        image=image2;
                    }else if(spriteNum==3){
                        image=image3;
                    }else if(spriteNum==4){
                        image=image4;
                    }else if(spriteNum==5){
                        image=image5;
                    }else if(spriteNum==6){
                        image=image6;
                    }else if(spriteNum==7){
                        image=image7;
                    }
                    break;
            }
            
        }
        g2.drawImage(image, screenX, screenY,gp.tileSize*3,gp.tileSize*3,null);
        double oneScale=(double)gp.tileSize/maxLife;
            double hpBarValue=oneScale*life;
            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX+51, screenY+31,gp.tileSize, gp.tileSize/4+1);
            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX+50, screenY+30, (int)hpBarValue, gp.tileSize/4);
    }
}
