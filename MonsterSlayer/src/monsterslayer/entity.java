/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.image.BufferedImage;

/**
 *
 * @author kent
 */
public class entity {
    int x,y;
    int speed;
    
    public BufferedImage up1,up2,up3,up4,up5,up6;
    public BufferedImage down1,down2,down3,down4,down5,down6;
    public BufferedImage left1,left2,left3,left4,left5,left6;
    public BufferedImage right1,right2,right3,right4,right5,right6;
    public BufferedImage attup1,attup2,attup3,attup4;
    public BufferedImage attdown1,attdown2,attdown3,attdown4;
    public BufferedImage attleft1,attleft2,attleft3,attleft4;
    public BufferedImage attright1,attright2,attright3,attright4;
    public String direction;
    public boolean attack=false;
    public int spriteCounter=0;
    public int spriteNum=1;
}
