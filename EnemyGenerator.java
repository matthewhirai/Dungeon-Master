import java.util.*;
import java.io.*;
public class EnemyGenerator {

    /**
     * Generates a random enemy 
     * Determines if it's an enemy or magical enemy depending on the level
     * @return enemy
     */
   public Enemy generateEnemy(int level) {
       int enemy = (int) Math.floor( Math.random() * (5 - 1) + 1);
       Enemy e = new Troll();
       if (enemy == 1) {
           e = new Goblin();
       }
       else if (enemy == 2) {
           e = new Froglok();
       }
       else if (enemy == 3) {
           e = new Orc();
       }
       else if (enemy == 4) {
           e = new Troll();
       }
       
       int lvlUp = (int) Math.floor( Math.random() * (3 - 1) + 1);
       for (int i = 0; i < level - 1; i++) {
           if (lvlUp == 1) {
               e = new Warrior(e);
           }
           else if (lvlUp == 2) {
               e = new Warlock(e);
           }
       }
       return e;
    }
}