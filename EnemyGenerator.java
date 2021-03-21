import java.util.*;
import java.io.*;
public class EnemyGenerator {
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();;

    /**
     * loads the enemies
     */
    public EnemyGenerator() {
        try {
            Scanner read = new Scanner(new File("C:\\Users\\silve\\Desktop\\Java\\CECS 277\\Project 1\\Enemies.txt"));
            while (read.hasNextLine()) {
                String line = read.nextLine();  
                String[] e = line.split(",");
                String enemyName = e[0];
                int enemyHp = Integer.parseInt(e[1]);
                enemyList.add(new Enemy(enemyName, enemyHp));
            }
            read.close();
        }catch (FileNotFoundException fnf){
            System.out.println("File was not found");
        }
    }

    /**
     * Generates a random enemy from the list
     * Determines if it's an enemy or magical enemy
     * @return enemy
     */
   public Enemy generateEnemy() {
       Random random = new Random();
       int i = random.nextInt(enemyList.size());
       int num = random.nextInt(2) + 1;
       String enemy = "";
       if (num == 1) {
           enemy = "Magical " + enemyList.get(i).getName();
       }
       else {
           enemy = enemyList.get(i).getName();
       }
       
       Enemy e = new Enemy(enemy, enemyList.get(i).getMaxHp());
       if (enemy.contains("Magical")) {
           e = new MagicalEnemy(enemy, enemyList.get(i).getMaxHp());
       }
       return e;
   }
}