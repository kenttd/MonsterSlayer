/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.util.Random;
import object.obj_blackPotion;
import object.obj_redPotion;
import object.obj_yellowPotion;

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
        gp.obj[0]=new obj_redPotion(gp);// menggunakan polymorphysm
        Random rand = new Random();
        int pilihanAntara4=(rand.nextInt(4) + 1);
        if(pilihanAntara4==1){
            gp.obj[0].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
            gp.obj[0].worldY=(rand.nextInt((14-11)+1)+11)*gp.tileSize;//atas
        }else if(pilihanAntara4==2){
            gp.obj[0].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
            gp.obj[0].worldY=(rand.nextInt((25-19)+1)+19)*gp.tileSize;//bawah
        }else if(pilihanAntara4==3){
            gp.obj[0].worldX=(rand.nextInt((30-23)+1)+23)*gp.tileSize;//kanan
            gp.obj[0].worldY=(rand.nextInt((26-10)+1)+10)*gp.tileSize;//kanan
        }else{
            gp.obj[0].worldX=(rand.nextInt((17-10)+1)+10)*gp.tileSize;//kiri
            gp.obj[0].worldY=(rand.nextInt((25-11)+1)+11)*gp.tileSize;//kiri
        }
        System.out.println("pilihan 1:"+pilihanAntara4);
        gp.obj[1]=new obj_yellowPotion(gp);// menggunakan polymorphysm
        pilihanAntara4=(rand.nextInt(4) + 1);
        if(pilihanAntara4==1){
            gp.obj[1].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
            gp.obj[1].worldY=(rand.nextInt((14-11)+1)+11)*gp.tileSize;//atas
        }else if(pilihanAntara4==2){
            gp.obj[1].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
            gp.obj[1].worldY=(rand.nextInt((25-19)+1)+19)*gp.tileSize;//bawah
        }else if(pilihanAntara4==3){
            gp.obj[1].worldX=(rand.nextInt((30-23)+1)+23)*gp.tileSize;//kanan
            gp.obj[1].worldY=(rand.nextInt((26-10)+1)+10)*gp.tileSize;//kanan
        }else{
            gp.obj[1].worldX=(rand.nextInt((17-10)+1)+10)*gp.tileSize;//kiri
            gp.obj[1].worldY=(rand.nextInt((25-11)+1)+11)*gp.tileSize;//kiri
        }
        gp.obj[2]=new obj_blackPotion(gp);// menggunakan polymorphysm
        pilihanAntara4=(rand.nextInt(4) + 1);
        if(pilihanAntara4==1){
            gp.obj[2].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
            gp.obj[2].worldY=(rand.nextInt((14-11)+1)+11)*gp.tileSize;//atas
        }else if(pilihanAntara4==2){
            gp.obj[2].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
            gp.obj[2].worldY=(rand.nextInt((25-19)+1)+19)*gp.tileSize;//bawah
        }else if(pilihanAntara4==3){
            gp.obj[2].worldX=(rand.nextInt((30-23)+1)+23)*gp.tileSize;//kanan
            gp.obj[2].worldY=(rand.nextInt((26-10)+1)+10)*gp.tileSize;//kanan
        }else{
            gp.obj[2].worldX=(rand.nextInt((17-10)+1)+10)*gp.tileSize;//kiri
            gp.obj[2].worldY=(rand.nextInt((25-11)+1)+11)*gp.tileSize;//kiri
        }
        System.out.println("pilihan 2:"+pilihanAntara4);
        System.out.println("0 worldx:"+gp.obj[0].worldX);
        System.out.println("0 worldy:"+gp.obj[0].worldY);
        System.out.println("1 worldx:"+gp.obj[1].worldX);
        System.out.println("1 worldy:"+gp.obj[1].worldY);
    }
    
    public void setEnemyCat(){
        gp.npc[0]=new catEnemy(gp,gp.player);
        gp.npc[0].worldX=gp.tileSize*24;
        gp.npc[0].worldY=gp.tileSize*11;
    }
    public void setEnemySlime(){
        gp.npc[1]=new slimeEnemy(gp,gp.player);
        gp.npc[1].worldX=gp.tileSize*25;
        gp.npc[1].worldY=gp.tileSize*22;
    }
    public void setEnemyBoss(){
        gp.npc[2]=new bossEnemy(gp,gp.player);
        gp.npc[2].worldX=gp.tileSize*20;
        gp.npc[2].worldY=gp.tileSize*14;
    }
    
    public void updatePotion(){
        if(gp.obj[0]==null){
            gp.obj[0]=new obj_redPotion(gp);// menggunakan polymorphysm
            Random rand = new Random();
            int pilihanAntara4=(rand.nextInt(4) + 1);
            if(pilihanAntara4==1){
                gp.obj[0].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
                gp.obj[0].worldY=(rand.nextInt((14-11)+1)+11)*gp.tileSize;//atas
            }else if(pilihanAntara4==2){
                gp.obj[0].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
                gp.obj[0].worldY=(rand.nextInt((25-19)+1)+19)*gp.tileSize;//bawah
            }else if(pilihanAntara4==3){
                gp.obj[0].worldX=(rand.nextInt((30-23)+1)+23)*gp.tileSize;//kanan
                gp.obj[0].worldY=(rand.nextInt((26-10)+1)+10)*gp.tileSize;//kanan
            }else{
                gp.obj[0].worldX=(rand.nextInt((17-10)+1)+10)*gp.tileSize;//kiri
                gp.obj[0].worldY=(rand.nextInt((25-11)+1)+11)*gp.tileSize;//kiri
            }
        }else if(gp.obj[1]==null){
            gp.obj[1]=new obj_redPotion(gp);// menggunakan polymorphysm
            Random rand = new Random();
            int pilihanAntara4=(rand.nextInt(4) + 1);
            pilihanAntara4=(rand.nextInt(4) + 1);
            if(pilihanAntara4==1){
                gp.obj[1].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
                gp.obj[1].worldY=(rand.nextInt((14-11)+1)+11)*gp.tileSize;//atas
            }else if(pilihanAntara4==2){
                gp.obj[1].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
                gp.obj[1].worldY=(rand.nextInt((25-19)+1)+19)*gp.tileSize;//bawah
            }else if(pilihanAntara4==3){
                gp.obj[1].worldX=(rand.nextInt((30-23)+1)+23)*gp.tileSize;//kanan
                gp.obj[1].worldY=(rand.nextInt((26-10)+1)+10)*gp.tileSize;//kanan
            }else{
                gp.obj[1].worldX=(rand.nextInt((17-10)+1)+10)*gp.tileSize;//kiri
                gp.obj[1].worldY=(rand.nextInt((25-11)+1)+11)*gp.tileSize;//kiri
            }
        }else if(gp.obj[2]==null){
            gp.obj[2]=new obj_blackPotion(gp);// menggunakan polymorphysm
            Random rand = new Random();
            int pilihanAntara4=(rand.nextInt(4) + 1);
            pilihanAntara4=(rand.nextInt(4) + 1);
            if(pilihanAntara4==1){
                gp.obj[2].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
                gp.obj[2].worldY=(rand.nextInt((14-11)+1)+11)*gp.tileSize;//atas
            }else if(pilihanAntara4==2){
                gp.obj[2].worldX=(rand.nextInt((30-10)+1)+10)*gp.tileSize;//atas
                gp.obj[2].worldY=(rand.nextInt((25-19)+1)+19)*gp.tileSize;//bawah
            }else if(pilihanAntara4==3){
                gp.obj[2].worldX=(rand.nextInt((30-23)+1)+23)*gp.tileSize;//kanan
                gp.obj[2].worldY=(rand.nextInt((26-10)+1)+10)*gp.tileSize;//kanan
            }else{
                gp.obj[2].worldX=(rand.nextInt((17-10)+1)+10)*gp.tileSize;//kiri
                gp.obj[2].worldY=(rand.nextInt((25-11)+1)+11)*gp.tileSize;//kiri
            }
        }
    }
}
