/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import monsterslayer.gamePanel;

/**
 *
 * @author kent
 */
public class obj_heart extends superObject{
    gamePanel gp;
    public obj_heart(gamePanel gp){
        super(gp);
        name="Red Potion";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image1=ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image2=ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image=uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image1=uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
            image2=uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
}
