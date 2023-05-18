/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author kent
 */
public class sound {
    Clip clip;
    URL soundURL[]=new URL[30];
    
    public sound(){
        
        soundURL[0]=getClass().getResource("/sound/foreverland.wav");
        soundURL[1]=getClass().getResource("/sound/footstep.wav");
        soundURL[2]=getClass().getResource("/sound/sword.wav");
        soundURL[3]=getClass().getResource("/sound/pickUpPotion.wav");
        soundURL[4]=getClass().getResource("/sound/enemyHitPlayer.wav");
    }
    
    public void setFile(int i){
        try{
            AudioInputStream ais=AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
            if(i==2){
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-13.0f);
            }else if(i==1){
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(5.0f);
            }else if(i==0){
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-4.0f);
            }else if(i==4){
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(50.0f);
                System.out.println("suara");
            }
            

        }catch(Exception e){
            
        }
    }
    
    public void play(){
        clip.start();
    }
    
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
}
