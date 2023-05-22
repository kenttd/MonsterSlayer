/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

import java.awt.Graphics2D;

/**
 *
 * @author kent
 */
public class quest {
    gamePanel gp;
    private String questName1,questName2;
    private int questHave1=0,questRequired1=1,questHave2=0,questRequired2=1;
    private boolean quest1,quest2;
    public quest(gamePanel gp){
        this.gp=gp;
    }
    public void updateQuest(){
        if(questHave1==questRequired1){
            questHave1=0;
            questRequired1++;
        }else if(questHave2==questRequired2){
            questHave2=0;
            questRequired2++;
        }
        questName1="("+questHave1+"/"+questRequired1+") kill the cat enemy "+questRequired1+" times";
        questName2="("+questHave2+"/"+questRequired2+") pick up red potion "+questRequired2+" times";
    }
    public void setQuestName1(String questName1) {
        this.questName1 = questName1;
    }

    public void setQuestHave1(int questHave1) {
        this.questHave1 = questHave1;
    }

    public void setQuestRequired1(int questRequired1) {
        this.questRequired1 = questRequired1;
    }

    public String getQuestName1() {
        return questName1;
    }

    public int getQuestHave1() {
        return questHave1;
    }

    public int getQuestRequired1() {
        return questRequired1;
    }

    public String getQuestName2() {
        return questName2;
    }

    public void setQuestName2(String questName2) {
        this.questName2 = questName2;
    }

    public int getQuestHave2() {
        return questHave2;
    }

    public void setQuestHave2(int questHave2) {
        this.questHave2 = questHave2;
    }

    public int getQuestRequired2() {
        return questRequired2;
    }

    public void setQuestRequired2(int questRequired2) {
        this.questRequired2 = questRequired2;
    }

    public boolean isQuest1() {
        return quest1;
    }

    public void setQuest1(boolean quest1) {
        this.quest1 = quest1;
    }

    public boolean isQuest2() {
        return quest2;
    }

    public void setQuest2(boolean quest2) {
        this.quest2 = quest2;
    }
    
    
    
    
    
}
