/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import object.*;
/**
 *
 * @author kent
 */
public class ui {
    gamePanel gp;
    Font courier;
    Graphics2D g2;
    private int spriteNumBoss=1,spriteBossCounter=0,spriteNumCoin=1,spriteCoinCounter=0;
    BufferedImage heart_full,heart_half,heart_blank,pause,pauseHover,save,saveHover,shop,shopHover,quest,questHover;
    BufferedImage image1,image2,image3,image4,image5,image6,image7,image8,wine,peach,apple,honey,cross,crossHover;
    BufferedImage coinimage1,coinimage2,coinimage3,coinimage4,coinimage5,coinimage6,coinimage7,coinimage8;
    public boolean messageOn=false;
    private boolean confLoad=false,confSave=false;
    public String message="";
    public int messageCounter=0;
    public int commandNum=0;
    private int redPotionC=0;
    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");
    
    public ui(gamePanel gp){
        this.gp=gp;
        courier=new Font("Courier", Font.BOLD, 100);
        
        superObject heart=new obj_heart(gp);//polymorph
        heart_full=heart.image;
        heart_half=heart.image1;
        heart_blank=heart.image2;
        getImage();
        superObject wine= new obj_wine(gp);
        superObject peach= new obj_peach(gp);
        superObject apple= new obj_apple(gp);
        superObject honey= new obj_honey(gp);
        this.wine=wine.image;
        this.peach=peach.image;
        this.apple=apple.image;
        this.honey=honey.image;
    }
    
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(courier);
        g2.setColor(white);
        if(gp.gameState==gp.playState){
//            drawPlayerLife();
//            drawEnemyLife();
            drawQuestButton();
            drawPauseButton();
        }else if(gp.gameState==gp.pauseState){
            drawWhenScreenIsPaused();
            drawSaveButton();
        }else if(gp.gameState==gp.titleState){
            drawTitleScreen();
            if(confLoad){
                drawConfLoad();
            }
        }else if(gp.gameState==gp.gameOverState){
            drawGameOver();
            gp.gameState=gp.afterGameOverState;
        }else if(gp.gameState==gp.highScoreState){
            drawHighScore();
        }else if(gp.gameState==gp.questState){
            drawQuest();
        }else if(gp.gameState==gp.shopState){
            drawShop();
        }
        
    }
    
    public void drawConfLoad(){
        int x=gp.screenWidth/4;
        int y=gp.screenHeight/5;
        BufferedImage temp;
        if(gp.mouseH.isBackHighHover())temp=crossHover;
        else temp=cross;
        g2.setColor(new Color(133, 187, 101,190));
        g2.fillRect(x, y, 400, 300);
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        g2.setColor(Color.black);
        g2.drawString("You have successfully loaded ", x+10, y+150);
        g2.drawString("the most recent save.", x+10, y+170);
        g2.drawImage(temp, x, y,gp.tileSize,gp.tileSize,null);
    }
    public void drawShop(){
        g2.setColor(new Color(205,115,99));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(new Font("Courier", Font.BOLD, 80));
        String text="Trade";
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/6;
        g2.setColor(Color.black);
        g2.drawString(text, x+10, y+10);
        g2.setColor(white);
        g2.drawString(text, x, y);
        g2.setFont(new Font("Courier", Font.PLAIN, 20));
        text="Trade the dropped item for coins";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/10;
        y+=gp.tileSize+10;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        x+=length+10;
        y-=25;
        g2.drawImage(getImageCoin(), x, y,gp.tileSize,gp.tileSize,null);
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        //kotak 1
        x=gp.screenWidth/10-60;
        y+=gp.tileSize*2-10;
        int oriY=y;
        g2.setColor(new Color(255,255,255,170));
        g2.fillRect(x, y, gp.screenWidth/4-30, 220);
        x+=(gp.screenWidth/4-30)/2-25;
        y+=10;
        g2.drawImage(apple, x, y, gp.tileSize, gp.tileSize, null);
        y+=gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString("Apple", x, y);
        g2.setFont(new Font("Courier", Font.PLAIN, 15));
        y+=30;
        x-=((gp.screenWidth/4-30)/2-25)-5;
        g2.drawString("1 -> 15", x, y);
        y+=30;
        g2.drawString("You have: "+gp.player.getInventory().get("Apple"), x, y);
        x+=30;
        y+=30;
        if(gp.mouseH.isShop1()){
            g2.setColor(new Color(133, 187, 101));
        }else g2.setColor(Color.black);
        g2.fillRect(x, y, 80, 40);
        g2.setColor(Color.black);
        x+=23;
        y+=25;
        if(gp.mouseH.isShop1()){
            g2.setColor(Color.black);
        }else g2.setColor(white);
        g2.drawString("Sell", x, y);
        //kotak 2
        x=(gp.screenWidth/10-60)+(gp.screenWidth/4);
        y=oriY;
        g2.setColor(new Color(255,255,255,170));
        g2.fillRect(x, y, gp.screenWidth/4-30, 220);
        x+=(gp.screenWidth/4-30)/2-25;
        y+=10;
        g2.drawImage(honey, x, y, gp.tileSize, gp.tileSize, null);
        y+=gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString("Honey", x, y);
        g2.setFont(new Font("Courier", Font.PLAIN, 15));
        y+=30;
        x-=((gp.screenWidth/4-30)/2-25)-5;
        g2.drawString("1 -> 20", x, y);
        y+=30;
        g2.drawString("You have: "+gp.player.getInventory().get("Honey"), x, y);
        x+=30;
        y+=30;
        if(gp.mouseH.isShop2()){
            g2.setColor(new Color(133, 187, 101));
        }else g2.setColor(Color.black);
        g2.fillRect(x, y, 80, 40);
        g2.setColor(Color.black);
        x+=23;
        y+=25;
        if(gp.mouseH.isShop2()){
            g2.setColor(Color.black);
        }else g2.setColor(white);
        g2.drawString("Sell", x, y);
        //kotak 3
        x=(gp.screenWidth/10-60)+((gp.screenWidth/4)*2);
        y=oriY;
        g2.setColor(new Color(255,255,255,170));
        g2.fillRect(x, y, gp.screenWidth/4-30, 220);
        x+=(gp.screenWidth/4-30)/2-25;
        y+=10;
        g2.drawImage(peach, x, y, gp.tileSize, gp.tileSize, null);
        y+=gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString("Peach", x, y);
        g2.setFont(new Font("Courier", Font.PLAIN, 15));
        y+=30;
        x-=((gp.screenWidth/4-30)/2-25)-5;
        g2.drawString("1 -> 15", x, y);
        y+=30;
        g2.drawString("You have: "+gp.player.getInventory().get("Peach"), x, y);
        x+=30;
        y+=30;
        if(gp.mouseH.isShop3()){
            g2.setColor(new Color(133, 187, 101));
        }else g2.setColor(Color.black);
        g2.fillRect(x, y, 80, 40);
        g2.setColor(Color.black);
        x+=23;
        y+=25;
        if(gp.mouseH.isShop3()){
            g2.setColor(Color.black);
        }else g2.setColor(white);
        g2.drawString("Sell", x, y);
        //kotak 4
        x=(gp.screenWidth/10-60)+((gp.screenWidth/4)*3);
        y=oriY;
        g2.setColor(new Color(255,255,255,170));
        g2.fillRect(x, y, gp.screenWidth/4-30, 220);
        x+=(gp.screenWidth/4-30)/2-25;
        y+=10;
        g2.drawImage(wine, x, y, gp.tileSize, gp.tileSize, null);
        y+=gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString("Wine", x, y);
        g2.setFont(new Font("Courier", Font.PLAIN, 15));
        y+=30;
        x-=((gp.screenWidth/4-30)/2-25)-5;
        g2.drawString("1 -> 15", x, y);
        y+=30;
        g2.drawString("You have: "+gp.player.getInventory().get("Wine"), x, y);
        x+=30;
        y+=30;
        if(gp.mouseH.isShop4()){
            g2.setColor(new Color(133, 187, 101));
        }else g2.setColor(Color.black);
        g2.fillRect(x, y, 80, 40);
        g2.setColor(Color.black);
        x+=23;
        y+=25;
        if(gp.mouseH.isShop4()){
            g2.setColor(Color.black);
        }else g2.setColor(white);
        g2.drawString("Sell", x, y);
        // coin player
        x=(gp.screenWidth/10-60);
        y=gp.screenHeight-80;
        g2.drawImage(getImageCoin(), x, y,gp.tileSize,gp.tileSize,null);
        x+=gp.tileSize+10;
        y+=gp.tileSize-10;
        g2.setFont(new Font("Courier", Font.BOLD, 30));
        g2.drawString(Integer.toString(gp.player.getGold()), x, y);
        BufferedImage temp;
        if(gp.mouseH.isBackHighHover())temp=crossHover;
        else temp=cross;
        g2.drawImage(temp, 5, 5,gp.tileSize,gp.tileSize,null);
    }
    
    public void drawSaveButton(){
        BufferedImage temp;
        if(gp.mouseH.isSaveHover()){
            temp=saveHover;
        }else temp=save;
        int x=gp.screenWidth-50;
        int y=0;
        g2.drawImage(temp, x, y,gp.tileSize,gp.tileSize,null);
        
    }
    public void drawPauseButton(){
        BufferedImage temp;
        if(gp.mouseH.isPauseHover()){
            temp=pauseHover;
        }else temp=pause;
        int x=gp.screenWidth-50;
        int y=0;
        g2.drawImage(temp, x, y,gp.tileSize,gp.tileSize,null);
        if(gp.mouseH.isShopHover()){
            temp=shopHover;
        }else temp=shop;
        x-=gp.tileSize+5;
        y=0;
        g2.drawImage(temp, x, y,gp.tileSize,gp.tileSize,null);
    }
    public void drawQuestButton(){
        BufferedImage temp;
        if(gp.mouseH.isBackHighHover()){
            temp=questHover;
        }else temp=quest;
        int x=5;
        int y=5;
        g2.drawImage(temp, x, y,gp.tileSize,gp.tileSize,null);
    }
    public void drawQuest(){
        g2.setColor(new Color(205,115,99));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(new Font("Courier", Font.BOLD, 80));
        String text="Quest";
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/6;
        g2.setColor(Color.black);
        g2.drawString(text, x+10, y+10);
        g2.setColor(white);
        g2.drawString(text, x, y);
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        text=gp.quest.getQuestName1();
        x=gp.screenWidth/10-30;
        y+=gp.tileSize*2;
        g2.drawImage(gp.npc[0].down1, x, y-55,gp.tileSize*2,gp.tileSize*2,null); // gambar icon
        x=gp.screenWidth/10+40;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        text=gp.quest.getQuestName2();
        x=gp.screenWidth/10;
        y+=gp.tileSize*2;
        g2.drawImage(gp.obj[0].image, x, y-30,gp.tileSize/2+10,gp.tileSize/2+10,null); // gambar icon
        x=gp.screenWidth/10+40;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        text=gp.quest.getQuestName3();
        x=gp.screenWidth/10-15;
        y+=gp.tileSize*2;
        g2.drawImage(gp.npc[1].image5, x, y-40,gp.tileSize+12,gp.tileSize+12,null); // gambar icon
        x=gp.screenWidth/10+40;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        text="Press [b] to get back to resume the game";
        x=gp.screenWidth/10;
        y+=gp.tileSize*2;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
    }
    public void drawHighScore(){
        
        List<scoreHandler> scores = new ArrayList<>();

        // Read the scores from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("highScores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                scores.add(new scoreHandler(name, score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort the scores in descending order
        Collections.sort(scores, Collections.reverseOrder());
        g2.setColor(new Color(205,115,99));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(new Font("Courier", Font.BOLD, 80));
        String text="High Score";
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/6;
        g2.setColor(Color.black);
        g2.drawString(text, x+10, y+10);
        g2.setColor(white);
        g2.drawString(text, x, y);
        // Print the top 5 scores
        g2.setFont(new Font("Courier", Font.PLAIN, 30));
        y+=gp.screenHeight/10;
        if(scores.size()>5){
            for (int i = 0; i < 5; i++) {
                scoreHandler score = scores.get(i);
                text=(i+1)+". "+score.getName() + " - " + score.getScore();
                length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x=gp.screenWidth/3;
                y+=gp.tileSize;
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+5);
                g2.setColor(white);
                g2.drawString(text, x, y);
            }
        }else{
            for (int i = 0; i < scores.size(); i++) {
                scoreHandler score = scores.get(i);
                text=(i+1)+". "+score.getName() + " - " + score.getScore();
                length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x=gp.screenWidth/3;
                y+=gp.tileSize;
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+5);
                g2.setColor(white);
                g2.drawString(text, x, y);
            }
            for(int i=0; i<5-scores.size(); i++){
                text=(scores.size()+i)+". "+"-";
                length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x=gp.screenWidth/3;
                y+=gp.tileSize;
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+5);
                g2.setColor(white);
                g2.drawString(text, x, y);
            }
        }
        BufferedImage temp;
        if(gp.mouseH.isBackHighHover())temp=crossHover;
        else temp=cross;
        g2.drawImage(temp, 5, 5,gp.tileSize,gp.tileSize,null);
    }
    public void drawAfterGameOver(){
        g2.setColor(new Color(205,115,99));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(new Font("Courier", Font.BOLD, 80));
        String text="Fill the name box";
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/6;
        g2.setColor(Color.black);
        g2.drawString(text, x+10, y+10);
        g2.setColor(white);
        g2.drawString(text, x, y);
    }
    public void drawGameOver(){
        if(gp.gameState==gp.afterGameOverState){
            drawAfterGameOver();
        }
        JFrame frame = new JFrame("Input Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 10, 80, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 10, 160, 25);
        frame.add(nameField);

//        JLabel ageLabel = new JLabel("Age:");
//        ageLabel.setBounds(10, 40, 80, 25);
//        frame.add(ageLabel);
//
//        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 120, 1));
//        ageSpinner.setBounds(100, 40, 160, 25);
//        frame.add(ageSpinner);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 80, 80, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                try
                {
                    String filename= "highScores.txt";
                    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                    fw.write("\n"+name+" "+gp.player.score);//appends the string to the file
                    fw.close();
                    gp.player.life=gp.player.maxLife;
                }
                catch(IOException ioe)
                {
                    System.err.println("IOException: " + ioe.getMessage());
                }
                gp.gameState=gp.titleState;
                frame.dispose();
            }
        });
        frame.add(submitButton);

        frame.setVisible(true);
    }
    
    public void drawTitleScreen(){
        g2.setColor(new Color(205,115,99));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(new Font("Courier", Font.BOLD, 80));
        String text="Monster";
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/6;
        // shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+10, y+10);
        g2.setColor(white);
        g2.drawString(text, x, y);
        text="Slayer";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y=gp.screenHeight/4+50;
        g2.setColor(Color.black);
        g2.drawString(text, x+10, y+10);
        g2.setColor(white);
        g2.drawString(text, x, y);
        // gambar karakter
        
        x=20;
        y=gp.screenHeight-(gp.screenHeight/2)-50;
        g2.drawImage(gp.player.attdown1, x, y,gp.tileSize*4,gp.tileSize*4,null);
        x=gp.screenWidth-(gp.screenWidth/2)+20;
        y=gp.screenHeight-(gp.screenHeight/2)-140;
        g2.drawImage(getImageBoss(), x, y,gp.tileSize*10,gp.tileSize*10,null);
        // menu
        g2.setFont(new Font("Courier", Font.BOLD, 40));
        text="New Game";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y=(gp.screenHeight-(gp.screenHeight/2)-50)+gp.tileSize*2;
        if(gp.mouseH.isGameHover()){
            drawHover(x, y,length);
        }
        g2.drawString(text, x, y);
        text="High Scores";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y+=gp.tileSize+20;
        if(gp.mouseH.isHighHover()){
            drawHover(x, y,length);
        }
        g2.drawString(text, x, y);
        text="Load Last Save";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y+=gp.tileSize+20;
        if(gp.mouseH.isLoadHover()){
            drawHover(x, y,length);
        }
        
        g2.drawString(text, x, y);
        text="Quit";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y+=gp.tileSize+20;
        if(gp.mouseH.isQuitHover()){
            drawHover(x, y,length);
        }
        
        g2.drawString(text, x, y);
        
        
    }
    
    public void drawHover(int x,int y,int length){
        g2.setColor(new Color(0,0,0)); 
        g2.fillRect(x-15, y-50, length+30 , gp.tileSize+20 );
        g2.setColor(white);
    }
    public void drawWhenScreenIsPaused(){
        
        g2.setColor(new Color(205,115,99));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        String text="PAUSED";
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/4;
        g2.setColor(Color.black);
        g2.drawString(text, x+10, y+10);
        g2.setColor(white);
        g2.drawString(text, x, y);
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        text="Control: ";
        x=gp.screenWidth/10;
        y+=gp.tileSize*2;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        text="[W][A][S][D] to move";
        x=gp.screenWidth/10;
        y+=gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        text="[Q] to see available quest";
        x=gp.screenWidth/10;
        y+=gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        BufferedImage temp;
        if(gp.mouseH.isBackHighHover())temp=crossHover;
        else temp=cross;
        g2.drawImage(temp, 5, 5,gp.tileSize,gp.tileSize,null);
    }
    public void drawPlayerLife(){
        // letak heart
        int x=gp.tileSize/3;
        int y=gp.tileSize/2;
        int i=0;
        while(i<gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x+=gp.tileSize;
        }
        x=gp.tileSize/3;
        y=gp.tileSize/2;
        i=0;
        while(i<gp.player.life){
            g2.drawImage(heart_half, x, y,null);
            i++;
            if(i<gp.player.life){
                //kalau masuk ganti half heart ke full heart
                g2.drawImage(heart_full, x, y,null);
            }
            i++;
            x+=gp.tileSize;
        
        }
    }
    
    public void drawEnemyLife(){
        // letak heart
        if(gp.npc[0]!=null){
            int x=gp.tileSize*gp.maxScreenRow;
            int y=gp.tileSize/2;
            int i=0;
            while(i<gp.npc[0].maxLife/2){
                g2.drawImage(heart_blank, x, y, null);
                i++;
                x+=gp.tileSize;
            }
            x=gp.tileSize*gp.maxScreenRow;
            y=gp.tileSize/2;
            i=0;
            while(i<gp.npc[0].life){
                g2.drawImage(heart_half, x, y,null);
                i++;
                if(i<gp.npc[0].life){
                    //kalau masuk ganti half heart ke full heart
                    g2.drawImage(heart_full, x, y,null);
                }
                i++;
                x+=gp.tileSize;

            }
        }
        
    }
    
    public void ifPlayerPickedUpRedPotion(){
        System.out.println("etst");
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        String text="You picked up red potion.";
        int x=gp.screenWidth/10-50;
        int y=gp.screenHeight/2+40;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
        redPotionC++;
        
    }

    public int getRedPotionC() {
        return redPotionC;
    }

    public void setRedPotionC(int redPotionC) {
        this.redPotionC = redPotionC;
    }
    
    public BufferedImage getImageBoss(){
        BufferedImage image=null;
        spriteBossCounter++;
        if (spriteBossCounter > 10) {
            
            if (spriteNumBoss == 1) {
                spriteNumBoss = 2;
            } else if (spriteNumBoss == 2) {
                spriteNumBoss = 3;
            } else if (spriteNumBoss == 3) {
                spriteNumBoss = 4;
            } else if (spriteNumBoss == 4) {
                spriteNumBoss = 5;
            }else if (spriteNumBoss == 5) {
                spriteNumBoss = 6;
            } else if (spriteNumBoss == 6) {
                spriteNumBoss = 7;
            } else if (spriteNumBoss == 7) {
                spriteNumBoss = 8;
            } else if (spriteNumBoss == 8) {
                spriteNumBoss = 1;
            }
            spriteBossCounter=0;
            if(spriteNumBoss>8)spriteNumBoss=0;
        }
        if(spriteNumBoss==1){
            image=image1;
        }else if(spriteNumBoss==2){
            image=image2;
        }else if(spriteNumBoss==3){
            image=image3;
        }else if(spriteNumBoss==4){
            image=image4;
        }else if(spriteNumBoss==5){
            image=image5;
        }else if(spriteNumBoss==6){
            image=image6;
        }else if(spriteNumBoss==7){
            image=image7;
        }else if(spriteNumBoss==8){
            image=image8;
        }
        
        return image;
    }
    
    public void getImage(){
        image1=setup("/enemyBoss/idle1");
        image2=setup("/enemyBoss/idle2");
        image3=setup("/enemyBoss/idle3");
        image4=setup("/enemyBoss/idle4");
        image5=setup("/enemyBoss/idle5");
        image6=setup("/enemyBoss/idle6");
        image7=setup("/enemyBoss/idle7");
        image8=setup("/enemyBoss/idle8");
        coinimage1=setup("/objects/1");
        coinimage2=setup("/objects/2");
        coinimage3=setup("/objects/3");
        coinimage4=setup("/objects/4");
        coinimage5=setup("/objects/5");
        coinimage6=setup("/objects/6");
        coinimage7=setup("/objects/7");
        coinimage8=setup("/objects/8");
        pause=setup("/objects/pauseButton");
        pauseHover=setup("/objects/pauseButtonHover");
        save=setup("/objects/saveButton");
        saveHover=setup("/objects/saveButtonHover");
        shop=setup("/objects/shopButton");
        shopHover=setup("/objects/shopButtonHover");
        cross=setup("/objects/crossButton");
        crossHover=setup("/objects/crossButtonHover");
        quest=setup("/objects/questButton");
        questHover=setup("/objects/questButtonHover");
    }
    
    public BufferedImage setup(String imageName){
        utilityTool uTool=new utilityTool();
        BufferedImage image=null;
        
        try{
            image=ImageIO.read(getClass().getResourceAsStream(imageName+".png"));
            image=uTool.scaleImage(image, gp.tileSize*8, gp.tileSize*8);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return image;
    }
    
    public BufferedImage getImageCoin(){
        BufferedImage image=null;
        spriteCoinCounter++;
        if (spriteCoinCounter > 10) { // ganti gambar setiap 10 frame
            
            if (spriteNumCoin == 1) {
                spriteNumCoin = 2;
            } else if (spriteNumCoin == 2) {
                spriteNumCoin = 3;
            } else if (spriteNumCoin == 3) {
                spriteNumCoin = 4;
            } else if (spriteNumCoin == 4) {
                spriteNumCoin = 5;
            }else if (spriteNumCoin == 5) {
                spriteNumCoin = 6;
            } else if (spriteNumCoin == 6) {
                spriteNumCoin = 7;
            } else if (spriteNumCoin == 7) {
                spriteNumCoin = 8;
            } else if (spriteNumCoin == 8) {
                spriteNumCoin = 1;
            }
            spriteCoinCounter=0;
            if(spriteNumCoin>8)spriteNumCoin=0;
        }
        if(spriteNumCoin==1){
            image=coinimage1;
        }else if(spriteNumCoin==2){
            image=coinimage2;
        }else if(spriteNumCoin==3){
            image=coinimage3;
        }else if(spriteNumCoin==4){
            image=coinimage4;
        }else if(spriteNumCoin==5){
            image=coinimage5;
        }else if(spriteNumCoin==6){
            image=coinimage6;
        }else if(spriteNumCoin==7){
            image=coinimage7;
        }else if(spriteNumCoin==8){
            image=coinimage8;
        }
        return image;
    }

    public boolean isConfLoad() {
        return confLoad;
    }

    public void setConfLoad(boolean confLoad) {
        this.confLoad = confLoad;
    }

    public boolean isConfSave() {
        return confSave;
    }

    public void setConfSave(boolean confSave) {
        this.confSave = confSave;
    }
    
    
}
