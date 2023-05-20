/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author kent
 */
public class catEnemy extends entity {
    
    public catEnemy(gamePanel gp,player player) {
        super(gp,player);
        direction="down";
        speed=2;//kalau mau ganti kecepatan disini
        getImage();
//        solidArea.x=0;
//        solidArea.y=0;
//        solidArea.width=12;
//        solidArea.height=16;
        maxLife=6;
        life=maxLife;
    }
    
    public void getImage(){
        up1=setup("catUp1");
        up2=setup("catUp2");
        up3=setup("catUp3");
        up4=setup("catUp4");
        down1=setup("catDown1");
        down2=setup("catDown2");
        down3=setup("catDown3");
        down4=setup("catDown4");
        left1=setup("catLeft1");
        left2=setup("catLeft2");
        left3=setup("catLeft3");
        left4=setup("catLeft4");
        right1=setup("catRight1");
        right2=setup("catRight2");
        right3=setup("catRight3");
        right4=setup("catRight4");
        attup1=setup("attCatUp1");
        attup2=setup("attCatUp2");
        attdown1=setup("attCatDown1");
        attdown1=setup("attCatDown2");
        attright1=setup("attCatRight1");
        attright2=setup("attCatRight2");
        attleft1=setup("attCatleft1");
        attleft2=setup("attCatleft2");
    }
    
    public BufferedImage setup(String imageName){
        utilityTool uTool=new utilityTool();
        BufferedImage image=null;
        
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/enemyCat/"+imageName+".png"));
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
                attack=true;
            }
            
        }
    }
    
    public boolean checkIfThereIsPlayer(int distance){
        int dx =worldX - gp.player.worldX;
        int dy =worldY - gp.player.worldY;
        return Math.sqrt(dx * dx + dy * dy) <= distance;
    }
    
}
