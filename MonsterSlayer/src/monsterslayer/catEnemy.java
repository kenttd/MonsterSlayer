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
    
    public catEnemy(gamePanel gp) {
        super(gp);
        direction="down";
        speed=2;//kalau mau ganti kecepatan disini
        getImage();
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
        
    }
}