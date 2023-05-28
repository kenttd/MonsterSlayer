/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kent
 */
public class mouseHandler implements MouseListener,MouseMotionListener{
    gamePanel gp;
    int x,y;
    private boolean gameHover=false,highHover=false,loadHover=false,quitHover=false,backHighHover=false,pauseHover=false,saveHover=false,shopHover=false;
    private boolean shop1=false,shop2=false,shop3=false,shop4=false;
    public mouseHandler(gamePanel gp){
        this.gp=gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        if(gp.gameState==gp.titleState){
            if(!gp.ui.isConfLoad()){
                if(y>=300&&y<=345&&x>=285&&x<=485){
                    gp.gameState=gp.playState;
                }else if(y>=345&&y<=415&&x>=245&&x<=520){
                    gp.gameState=gp.highScoreState;
                }else if(y>=495&&y<=553&&x>=325&&x<=445){
                    System.exit(0);
                }else if(y>=430&&y<=485&&x>=205&&x<=565){
                    FileOutputStream fos;
                    try {
                        FileInputStream fis = new FileInputStream("inventory.txt");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        HashMap<String,Integer> inventory = (HashMap<String,Integer>)ois.readObject();
                        gp.player.setInventory(inventory);
                        ois.close();
                        fis.close();
                        BufferedReader reader = new BufferedReader(new FileReader("lastSave.txt"));
                        String[] line = reader.readLine().split(" ");
                        // life -> score -> gold
                        gp.player.life=Integer.parseInt(line[0]);
                        gp.player.score=Integer.parseInt(line[1]);
                        gp.player.setGold(Integer.parseInt(line[2]));
                    }catch (IOException ex) {
                        Logger.getLogger(mouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(mouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gp.ui.setConfLoad(true);
                }
            }else{
                if(y>=115&&y<=162&&x>=195&&x<=238){
                    gp.ui.setConfLoad(false);
                }
            }
            
        }
        if(gp.gameState==gp.highScoreState||gp.gameState==gp.pauseState||gp.gameState==gp.shopState||gp.gameState==gp.playState){
            if(y>=10&&y<=50&&x>=5&&x<=53&&gp.gameState==gp.highScoreState){
                gp.gameState=gp.titleState;
            }if(y>=10&&y<=50&&x>=5&&x<=53&&gp.gameState==gp.pauseState){
                gp.gameState=gp.playState;
            }
            if(y>=10&&y<=50&&x>=5&&x<=53&&gp.gameState==gp.shopState){
                gp.gameState=gp.playState;
            }
            if(y>=10&&y<=50&&x>=5&&x<=53&&gp.gameState==gp.playState){
                gp.gameState=gp.questState;
            }
        }
        if(gp.gameState==gp.playState){
            if(y>=0&&y<=50&&x>=720&&x<=765){
                gp.gameState=gp.pauseState;
            }else if(y>=0&&y<=50&&x>=665&&x<=715){
                gp.gameState=gp.shopState;
            }
        }
        if(gp.gameState==gp.pauseState){
            if(y>=0&&y<=50&&x>=720&&x<=765){
                try
                {
                    String filename= "lastSave.txt";
                    FileWriter fw = new FileWriter(filename,false); // tidak append tapi overwrite
                    fw.write(gp.player.life+" "+gp.player.score+" "+gp.player.getGold());//overwrite the string to the file
                    fw.close();
                    FileOutputStream fos = new FileOutputStream("inventory.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(gp.player.getInventory());
                    oos.close();
                    fos.close();
                }
                catch(IOException ioe)
                {
                    System.err.println("IOException: " + ioe.getMessage());
                }
                gp.ui.setConfSave(true);
            }
        }
        else if(gp.gameState==gp.shopState){
            if(y>=390&&y<=443&&x>=50&&x<=130){
                if(gp.player.getInventory().get("Apple")>0){
                    gp.player.getInventory().replace("Apple", gp.player.getInventory().get("Apple")-1);
                    gp.player.setGold(gp.player.getGold()+15);
                }
            }
            if(y>=390&&y<=443&&x>=240&&x<=322){
                if(gp.player.getInventory().get("Honey")>0){
                    gp.player.getInventory().replace("Honey", gp.player.getInventory().get("Honey")-1);
                    gp.player.setGold(gp.player.getGold()+15);
                }
            }
            if(y>=390&&y<=443&&x>=435&&x<=515){
                if(gp.player.getInventory().get("Peach")>0){
                    gp.player.getInventory().replace("Peach", gp.player.getInventory().get("Peach")-1);
                    gp.player.setGold(gp.player.getGold()+15);
                }
            }
            if(y>=390&&y<=443&&x>=628&&x<=710){
                if(gp.player.getInventory().get("Wine")>0){
                    gp.player.getInventory().replace("Wine", gp.player.getInventory().get("Wine")-1);
                    gp.player.setGold(gp.player.getGold()+15);
                }
            }
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        x=e.getX();
        y=e.getY();
        if(!gp.ui.isConfLoad()&&gp.gameState==gp.titleState){
            if(y>=300&&y<=345&&x>=285&&x<=485){
                gameHover=true;
            }else gameHover=false;
            if(y>=495&&y<=553&&x>=325&&x<=445){
                quitHover=true;
            }else quitHover=false;
            if(y>=345&&y<=415&&x>=245&&x<=520){
                highHover=true;
            }else highHover=false;
            if(y>=430&&y<=485&&x>=205&&x<=565){
                loadHover=true;
            }else loadHover=false;
        }
        if(gp.ui.isConfLoad()){
            if(y>=115&&y<=162&&x>=195&&x<=238){
                backHighHover=true;
            }else backHighHover=false;
        }
        
        
        if(!gp.ui.isConfLoad()){
            if(y>=10&&y<=50&&x>=5&&x<=53&&(gp.gameState==gp.highScoreState||gp.gameState==gp.pauseState||gp.gameState==gp.shopState||gp.gameState==gp.playState)){
                backHighHover=true;
            }else backHighHover=false;
        }
        
        if(y>=0&&y<=50&&x>=720&&x<=765&&gp.gameState==gp.playState){
            pauseHover=true;
        }else pauseHover=false;
        if(y>=0&&y<=50&&x>=720&&x<=765&&gp.gameState==gp.pauseState){
            saveHover=true;
        }else saveHover=false;
        if(y>=0&&y<=50&&x>=665&&x<=715&&gp.gameState==gp.playState){
            shopHover=true;
        }else shopHover=false;
        if(gp.gameState==gp.shopState){
            if(y>=390&&y<=443&&x>=50&&x<=130){
                shop1=true;
            }else shop1=false;
            if(y>=390&&y<=443&&x>=240&&x<=322){
                shop2=true;
            }else shop2=false;
            if(y>=390&&y<=443&&x>=435&&x<=515){
                shop3=true;
            }else shop3=false;
            if(y>=390&&y<=443&&x>=628&&x<=710){
                shop4=true;
            }else shop4=false;
            
        }
        System.out.println("x: "+x+", y"+y);
    }

    public boolean isGameHover() {
        return gameHover;
    }

    public void setGameHover(boolean gameHover) {
        this.gameHover = gameHover;
    }

    public boolean isHighHover() {
        return highHover;
    }

    public void setHighHover(boolean highHover) {
        this.highHover = highHover;
    }

    public boolean isQuitHover() {
        return quitHover;
    }

    public void setQuitHover(boolean quitHover) {
        this.quitHover = quitHover;
    }

    public boolean isBackHighHover() {
        return backHighHover;
    }

    public void setBackHighHover(boolean backHighHover) {
        this.backHighHover = backHighHover;
    }

    public boolean isPauseHover() {
        return pauseHover;
    }

    public void setPauseHover(boolean pauseHover) {
        this.pauseHover = pauseHover;
    }

    public boolean isSaveHover() {
        return saveHover;
    }

    public void setSaveHover(boolean saveHover) {
        this.saveHover = saveHover;
    }

    public boolean isShopHover() {
        return shopHover;
    }

    public void setShopHover(boolean shopHover) {
        this.shopHover = shopHover;
    }

    public boolean isShop1() {
        return shop1;
    }

    public void setShop1(boolean shop1) {
        this.shop1 = shop1;
    }

    public boolean isShop2() {
        return shop2;
    }

    public void setShop2(boolean shop2) {
        this.shop2 = shop2;
    }

    public boolean isShop3() {
        return shop3;
    }

    public void setShop3(boolean shop3) {
        this.shop3 = shop3;
    }

    public boolean isShop4() {
        return shop4;
    }

    public void setShop4(boolean shop4) {
        this.shop4 = shop4;
    }

    public boolean isLoadHover() {
        return loadHover;
    }

    public void setLoadHover(boolean loadHover) {
        this.loadHover = loadHover;
    }
    
    
    
    
    
}
