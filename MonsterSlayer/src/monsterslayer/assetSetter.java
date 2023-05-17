/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.util.Random;
import object.obj_redPotion;

/**
 *
 * @author kent
 */
public class assetSetter {
    gamePanel gp;
    
    public assetSetter(gamePanel gp){
        this.gp=gp;
    }
    
    public void setObject(){
        gp.obj[0]=new obj_redPotion();// menggunakan polymorphysm
        Random rand = new Random();
        int pilihanAntara4=(rand.nextInt(4) + 1);
        if(pilihanAntara4==1){
            gp.obj[0].worldX=(rand.nextInt((22-2)+1)+2)*gp.tileSize;//atas
            gp.obj[0].worldY=(rand.nextInt((8-5)+1)+5)*gp.tileSize;//atas
        }else if(pilihanAntara4==2){
            gp.obj[0].worldX=(rand.nextInt((22-2)+1)+2)*gp.tileSize;//bawah
            gp.obj[0].worldY=(rand.nextInt((19-13)+1)+13)*gp.tileSize;//bawah
        }else if(pilihanAntara4==3){
            gp.obj[0].worldX=(rand.nextInt((22-15)+1)+15)*gp.tileSize;//kanan
            gp.obj[0].worldY=(rand.nextInt((20-4)+1)+4)*gp.tileSize;//kanan
        }else{
            gp.obj[0].worldX=(rand.nextInt((9-2)+1)+2)*gp.tileSize;//kiri
            gp.obj[0].worldY=(rand.nextInt((19-4)+1)+4)*gp.tileSize;//kiri
        }
        System.out.println("pilihan 1:"+pilihanAntara4);
        gp.obj[1]=new obj_redPotion();// menggunakan polymorphysm
        pilihanAntara4=(rand.nextInt(4) + 1);
        if(pilihanAntara4==1){
            gp.obj[1].worldX=(rand.nextInt((22-2)+1)+2)*gp.tileSize;//atas
            gp.obj[1].worldY=(rand.nextInt((8-5)+1)+5)*gp.tileSize;//atas
        }else if(pilihanAntara4==2){
            gp.obj[1].worldX=(rand.nextInt((22-2)+1)+2)*gp.tileSize;//bawah
            gp.obj[1].worldY=(rand.nextInt((19-13)+1)+13)*gp.tileSize;//bawah
        }else if(pilihanAntara4==3){
            gp.obj[1].worldX=(rand.nextInt((22-15)+1)+15)*gp.tileSize;//kanan
            gp.obj[1].worldY=(rand.nextInt((20-4)+1)+4)*gp.tileSize;//kanan
        }else{
            gp.obj[1].worldX=(rand.nextInt((9-2)+1)+2)*gp.tileSize;//kiri
            gp.obj[1].worldY=(rand.nextInt((19-4)+1)+4)*gp.tileSize;//kiri
        }
         System.out.println("pilihan 2:"+pilihanAntara4);
        System.out.println("0 worldx:"+gp.obj[0].worldX);
        System.out.println("0 worldy:"+gp.obj[0].worldY);
        System.out.println("1 worldx:"+gp.obj[1].worldX);
        System.out.println("1 worldy:"+gp.obj[1].worldY);
    }
    
    public void setEnemyCat(){
        gp.npc[0]=new catEnemy(gp);
        gp.npc[0].worldX=gp.tileSize*13;
        gp.npc[0].worldY=gp.tileSize*3;
    }
}
