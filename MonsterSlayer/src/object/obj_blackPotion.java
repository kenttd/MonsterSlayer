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
public class obj_blackPotion extends superObject{
    public obj_blackPotion(gamePanel gp){
        super(gp);
        name="Black Potion";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/objects/blackPotion.png"));
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
