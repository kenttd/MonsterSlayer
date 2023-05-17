/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author kent
 */
public class obj_redPotion extends superObject{
    
    public obj_redPotion(){
        name="Red Potion";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/objects/redPotion.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
    
}
