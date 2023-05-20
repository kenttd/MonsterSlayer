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
public class obj_yellowPotion extends superObject{
    public obj_yellowPotion(gamePanel gp){
        super(gp);
        name="Yellow Potion";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/objects/yellowPotion.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
    @Override
    public void update(){
        
    }
}
