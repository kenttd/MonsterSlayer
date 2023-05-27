/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import object.obj_apple;
import object.obj_honey;
import object.obj_peach;
import object.obj_wine;
import object.superObject;

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
    public BufferedImage image1,image2,image3,image4,image5,image6,image7,image8;
    public String direction;
    public boolean attack=false;
    public int spriteCounter=0;
    public int spriteNum=1;
    public int maxLife,life;
    private boolean alive=true,dying=false;
    private int dyingCounter=0;
    public int actionLockCounter=0;
    public int enemyAttackCounter=0;
    public Rectangle solidArea=new Rectangle(60,64,50,60);
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
            // hp bar
            double oneScale=(double)gp.tileSize/maxLife;
            double hpBarValue=oneScale*life;
            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX+51, screenY+31,gp.tileSize, gp.tileSize/4+1);
            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX+50, screenY+30, (int)hpBarValue, gp.tileSize/4);
            
            
        }
        if(dying){
            dyingAnimation(g2);
        }
        else g2.drawImage(image, screenX, screenY,gp.tileSize*3,gp.tileSize*3,null);
    }
    
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        if(dyingCounter<=5){
            
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
    
    public void checkIfEnemyIsDead(int i){
        if(this.life<=0){
            gp.npc[i].setDying(true);
            System.out.println("mati");
            Random rand= new Random();
            int result= rand.nextInt(4-1)+1+1;
            superObject temp;
            if(result==1){
                temp=new obj_apple(gp);
            }else if(result==2){
                temp=new obj_honey(gp);
            }else if(result==3){
                temp=new obj_peach(gp);
            }else{
                temp=new obj_wine(gp);
            }
            dropItem(temp, gp.npc[i].worldX, gp.npc[i].worldY);
            switch(i){
                case 0:
//                    gp.quest.setQuestName1("bisa");
                    System.out.println(gp.quest.getQuestName1());
                    gp.quest.setQuestHave1(gp.quest.getQuestHave1()+1);
                    System.out.println(gp.quest.getQuestHave1());
                    System.out.println(gp.quest.getQuestName1());
                    gp.aSetter.setEnemyCat();
                    gp.player.score+=50;
                    break;
                case 1:
                    gp.aSetter.setEnemySlime();
                    gp.player.score+=30;
                    break;
                case 2:
                    gp.aSetter.setEnemyBoss();
                    break;
            }
            
        }
    }
    public void dropItem(superObject item,int x,int y){
        int i=0;
        while(gp.obj[i]!=null){
            i++;
        }
        if(i<gp.obj.length){
            gp.obj[i]=item;
            gp.obj[i].worldX=x;
            gp.obj[i].worldY=y;
        }
    }
    
    public void updateQuest(int i){
        
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public int getDyingCounter() {
        return dyingCounter;
    }

    public void setDyingCounter(int dyingCounter) {
        this.dyingCounter = dyingCounter;
    }
    
    
}
