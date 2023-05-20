/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsterslayer;

/**
 *
 * @author kent
 */
public class scoreHandler implements Comparable<scoreHandler>{
    private String name;
        private int score;

        public scoreHandler(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(scoreHandler other) {
            return Integer.compare(this.score, other.score);
        }

}
