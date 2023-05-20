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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import object.obj_heart;
import object.superObject;

/**
 *
 * @author kent
 */
public class ui {
    gamePanel gp;
    Font courier;
    Graphics2D g2;
    BufferedImage heart_full,heart_half,heart_blank;
    public boolean messageOn=false;
    public String message="";
    public int messageCounter=0;
    public int commandNum=0;
    
    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");
    
    public ui(gamePanel gp){
        this.gp=gp;
        courier=new Font("Courier", Font.BOLD, 100);
        
        superObject heart=new obj_heart(gp);//polymorph
        heart_full=heart.image;
        heart_half=heart.image1;
        heart_blank=heart.image2;
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
            drawPlayerLife();
            drawEnemyLife();
        }else if(gp.gameState==gp.pauseState){
            drawWhenScreenIsPaused();
        }else if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }else if(gp.gameState==gp.gameOverState){
            drawGameOver();
            gp.gameState=gp.afterGameOverState;
        }else if(gp.gameState==gp.highScoreState){
            drawHighScore();
        }
        
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
                text=score.getName() + " - " + score.getScore();
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
                text=score.getName() + " - " + score.getScore();
                length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x=gp.screenWidth/3;
                y+=gp.tileSize;
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+5);
                g2.setColor(white);
                g2.drawString(text, x, y);
            }
            for(int i=0; i<5-scores.size(); i++){
                text="-";
                length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x=gp.screenWidth/3;
                y+=gp.tileSize;
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+5);
                g2.setColor(white);
                g2.drawString(text, x, y);
            }
        }
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        text="Press [b] to get back to the main menu";
        x=gp.screenWidth/10;
        y+=gp.tileSize*2;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(white);
        g2.drawString(text, x, y);
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

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 40, 80, 25);
        frame.add(ageLabel);

        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 120, 1));
        ageSpinner.setBounds(100, 40, 160, 25);
        frame.add(ageSpinner);

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
        // menu
        g2.setFont(new Font("Courier", Font.BOLD, 40));
        text="New Game";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y+=gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum==0){
            g2.drawString(">", x-gp.tileSize, y);
        }
        text="High Scores";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y+=gp.tileSize+20;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-gp.tileSize, y);
        }
        text="Quit";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2-(length/2);
        y+=gp.tileSize+20;
        g2.drawString(text, x, y);
        if(commandNum==2){
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        
    }
    
    public void drawWhenScreenIsPaused(){
        String text="PAUSED";
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-(length/2);
        int y=gp.screenHeight/2;
        g2.drawString(text, x, y);
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
}
