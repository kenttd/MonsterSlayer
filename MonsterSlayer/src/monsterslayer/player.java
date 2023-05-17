/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author kent
 */
public class player extends entity{

    keyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int totalRedPotion=0;
    
    public player(gamePanel gp, keyHandler keyH){
        super(gp);//memanggil contructor dari superclass

        this.keyH=keyH;
        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
        solidArea=new Rectangle(60,64,32,20);
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
    
    public void setDefaultValues(){
        worldX=gp.tileSize*11;
        worldY=gp.tileSize*11;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){
        try{
            up1=ImageIO.read(getClass().getResourceAsStream("/playerUp/up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/playerUp/up2.png"));
            up3=ImageIO.read(getClass().getResourceAsStream("/playerUp/up3.png"));
            up4=ImageIO.read(getClass().getResourceAsStream("/playerUp/up4.png"));
            up5=ImageIO.read(getClass().getResourceAsStream("/playerUp/up5.png"));
            up6=ImageIO.read(getClass().getResourceAsStream("/playerUp/up6.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/playerDown/down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/playerDown/down2.png"));
            down3=ImageIO.read(getClass().getResourceAsStream("/playerDown/down3.png"));
            down4=ImageIO.read(getClass().getResourceAsStream("/playerDown/down4.png"));
            down5=ImageIO.read(getClass().getResourceAsStream("/playerDown/down5.png"));
            down6=ImageIO.read(getClass().getResourceAsStream("/playerDown/down6.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/playerLeft/left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/playerLeft/left2.png"));
            left3=ImageIO.read(getClass().getResourceAsStream("/playerLeft/left3.png"));
            left4=ImageIO.read(getClass().getResourceAsStream("/playerLeft/left4.png"));
            left5=ImageIO.read(getClass().getResourceAsStream("/playerLeft/left5.png"));
            left6=ImageIO.read(getClass().getResourceAsStream("/playerLeft/left6.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/playerRight/right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/playerRight/right2.png"));
            right3=ImageIO.read(getClass().getResourceAsStream("/playerRight/right3.png"));
            right4=ImageIO.read(getClass().getResourceAsStream("/playerRight/right4.png"));
            right5=ImageIO.read(getClass().getResourceAsStream("/playerRight/right5.png"));
            right6=ImageIO.read(getClass().getResourceAsStream("/playerRight/right6.png"));
            attup1=ImageIO.read(getClass().getResourceAsStream("/playerAttackUp/attup1.png"));
            attup2=ImageIO.read(getClass().getResourceAsStream("/playerAttackUp/attup2.png"));
            attup3=ImageIO.read(getClass().getResourceAsStream("/playerAttackUp/attup3.png"));
            attup4=ImageIO.read(getClass().getResourceAsStream("/playerAttackUp/attup4.png"));
            attdown1=ImageIO.read(getClass().getResourceAsStream("/playerAttackDown/attdown1.png"));
            attdown2=ImageIO.read(getClass().getResourceAsStream("/playerAttackDown/attdown2.png"));
            attdown3=ImageIO.read(getClass().getResourceAsStream("/playerAttackDown/attdown3.png"));
            attdown4=ImageIO.read(getClass().getResourceAsStream("/playerAttackDown/attdown4.png"));
            attleft1=ImageIO.read(getClass().getResourceAsStream("/playerAttackLeft/attleft1.png"));
            attleft2=ImageIO.read(getClass().getResourceAsStream("/playerAttackLeft/attleft2.png"));
            attleft3=ImageIO.read(getClass().getResourceAsStream("/playerAttackLeft/attleft3.png"));
            attleft4=ImageIO.read(getClass().getResourceAsStream("/playerAttackLeft/attleft4.png"));
            attright1=ImageIO.read(getClass().getResourceAsStream("/playerAttackRight/attright1.png"));
            attright2=ImageIO.read(getClass().getResourceAsStream("/playerAttackRight/attright2.png"));
            attright3=ImageIO.read(getClass().getResourceAsStream("/playerAttackRight/attright3.png"));
            attright4=ImageIO.read(getClass().getResourceAsStream("/playerAttackRight/attright4.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update() {
    if (keyH.up || keyH.down || keyH.right || keyH.left&&!attack) {
        if (keyH.up == true) {
            direction = "up";
            
        } else if (keyH.down) {
            direction = "down";
            
        } else if (keyH.right) {
            direction = "right";
            
        } else if (keyH.left) {
            direction = "left";
            
        }
        // cek apakah tembok
        collisionOn=false;
        gp.colCheck.checkTile(this);
        int objIndex=gp.colCheck.checkObject(this, true);
        pickUpPotion(objIndex);
        //kalau ga ada tembok baru gerak
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
            } else if (spriteNum == 5) {
                spriteNum = 6;
            } else if (spriteNum == 6) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    } else if (keyH.space && !attack) { // only start attack animation if it is not already playing
        attack = true;
        spriteNum = 1;
    }

    // always increment sprite counter and update sprite number
    if (attack) {
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
                attack = false; // reset attack animation
            }
            spriteCounter = 0;
        }
    }
//    spriteCounter++;
//    if (spriteCounter > 10) {
//        if (!attack) { // walking animation
//            if (spriteNum == 1) {
//                spriteNum = 2;
//            } else if (spriteNum == 2) {
//                spriteNum = 3;
//            } else if (spriteNum == 3) {
//                spriteNum = 4;
//            } else if (spriteNum == 4) {
//                spriteNum = 5;
//            } else if (spriteNum == 5) {
//                spriteNum = 6;
//            } else if (spriteNum == 6) {
//                spriteNum = 1;
//            }
//        } else { // attack animation
//            if (spriteNum == 1) {
//                spriteNum = 2;
//            } else if (spriteNum == 2) {
//                spriteNum = 3;
//            } else if (spriteNum == 3) {
//                spriteNum = 4;
//            } else if (spriteNum == 4) {
//                spriteNum = 1;
//                attack = false; // reset attack animation
//            }
//        }
//        spriteCounter = 0;
//    }
}

//    public void update(){
//        if(keyH.up||keyH.down||keyH.right||keyH.left){
//            if(keyH.up==true){
//            direction="up";
//            y-=speed;
//            }else if(keyH.down){
//                direction="down";
//                y+=speed;
//            }else if(keyH.right){
//                direction="right";
//                x+=speed;
//            }else if(keyH.left){
//                direction="left";
//                x-=speed;
//            }
//            spriteCounter++;
//            if(spriteCounter>10){
//                if(spriteNum==1){
//                    spriteNum=2;
//                }else if(spriteNum==2){
//                    spriteNum=3;
//                }else if(spriteNum==3){
//                    spriteNum=4;
//                }else if(spriteNum==4){
//                    spriteNum=5;
//                }else if(spriteNum==5){
//                    spriteNum=6;
//                }else if(spriteNum==6){
//                    spriteNum=1;
//                }
//                spriteCounter=0;
//            }
//        }else if(keyH.space&&!attack){
//            attack=true;
//            spriteCounter++;
//            if(spriteCounter>10){
//                if(spriteNum==1){
//                    spriteNum=2;
//                }else if(spriteNum==2){
//                    spriteNum=3;
//                }else if(spriteNum==3){
//                    spriteNum=4;
//                }else if(spriteNum==4){
//                    spriteNum=1;
//                    attack=false;
//                }
//                spriteCounter=0;
//                
//            }
//            
//        }
//        
//    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        if(!attack){
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
                    }else if(spriteNum==5){
                        image=up5;
                    }else if(spriteNum==6){
                        image=up6;
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
                    }else if(spriteNum==5){
                        image=down5;
                    }else if(spriteNum==6){
                        image=down6;
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
                    }else if(spriteNum==5){
                        image=left5;
                    }else if(spriteNum==6){
                        image=left6;
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
                    }else if(spriteNum==5){
                        image=right5;
                    }else if(spriteNum==6){
                        image=right6;
                    }
                    break;
            }
        }else{
            switch(direction){
                case "up":
                    if(spriteNum==1){
                        image=attup1;
                    }else if(spriteNum==2){
                        image=attup2;
                    }else if(spriteNum==3){
                        image=attup3;
                    }else if(spriteNum==4){
                        image=attup4;
                    }
                    break;
                case "down":
                    if(spriteNum==1){
                        image=attdown1;
                    }else if(spriteNum==2){
                        image=attdown2;
                    }else if(spriteNum==3){
                        image=attdown3;
                    }else if(spriteNum==4){
                        image=attdown4;
                    }
                    break;
                case "left":
                    if(spriteNum==1){
                        image=attleft1;
                    }else if(spriteNum==2){
                        image=attleft2;
                    }else if(spriteNum==3){
                        image=attleft3;
                    }else if(spriteNum==4){
                        image=attleft4;
                    }
                    break;
                case "right":
                    if(spriteNum==1){
                        image=attright1;
                    }else if(spriteNum==2){
                        image=attright2;
                    }else if(spriteNum==3){
                        image=attright3;
                    }else if(spriteNum==4){
                        image=attright4;
                    }
                    break;
            }
        }
        
        g2.drawImage(image, screenX,screenY,gp.tileSize*3,gp.tileSize*3,null);
//        g2.setColor(Color.white);
//        g2.fillRect(x, x, gp.tileSize, gp.tileSize);
    }
    
    public void pickUpPotion(int index){
        if(index!=-1){
            String objName=gp.obj[index].name;
            gp.playSFX(3);
            switch(objName){
                case"Red Potion":
                    totalRedPotion++;
                    gp.obj[index]=null;
                    System.out.println(totalRedPotion);
                    break;
                //bisa ditambah item lain disini
            }
        }
    }
}
