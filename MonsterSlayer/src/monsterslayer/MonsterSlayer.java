/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package monsterslayer;

import javax.swing.JFrame;

/**
 *
 * @author kent
 */
public class MonsterSlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game Tugas PBO");
        gamePanel gp = new gamePanel();
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gp.startGameThread();
            
    }
    
}
