/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import monsterslayer.gamePanel;

/**
 *
 * @author kent
 */
public class tileManager {
    gamePanel gp;
    public tile[] tile;
    public int mapTileNum[][];
    public tileManager(gamePanel gp){
        this.gp=gp;
        tile=new tile[10];
        getTileImage();
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap();
    }
    
    public void getTileImage(){
      
        try{
            tile[0]=new tile();
            tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/brickTile.png"));
            tile[1]=new tile();
            tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision=true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is=getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line = br.readLine();
                while(col<gp.maxWorldCol){
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            
        }
    }
    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;
        
        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum=mapTileNum[worldCol][worldRow];
            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screenX=worldX-gp.player.worldX+gp.player.screenX;
            int screenY=worldY-gp.player.worldY+gp.player.screenY;
            if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY,gp.tileSize,gp.tileSize,null);
            }
            
            worldCol++;
            if(worldCol==gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
