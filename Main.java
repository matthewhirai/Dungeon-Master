import java.util.*;
import java.io.*;
import java.awt.Point;
import javax.sound.sampled.*;
public class Main {

    /**
     * player fights mosnter or runs away in a random direction from monster 
     * @param h
     * @param e
     * @return true if player is alive, false otherwise
     */
    public static boolean monsterRoom(Hero h, Enemy e) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        MusicPlayer music = new MusicPlayer("C:\\Users\\silve\\Desktop\\Java\\CECS 277\\Project 1\\cynthia.wav");
        music.play(0);
        Scanner input = new Scanner(System.in);
        System.out.println("You've encountered a " +  e.getName() + "\n"); 
        int option = 0;
        boolean alive = true;
        char coord = 'x';

        while (e.getHp() != 0) {
            System.out.println(e.toString());
            System.out.println(h.getName() + "'s Hp: " + h.getHp() + "/" + h.getMaxHp() + "\n");
            System.out.println("What do you want to do?\n1. Attack\n2. Run Away");
            option = CheckInput.getIntRange(1, 2);
            if (option == 1) {
                    alive = fight(h, e);
                if (alive){
                    continue;
                }
                return false;
            }
            if (option == 2) {
                e.takeDamage(e.getHp());
                while (coord == 'x') {
                    Random rand = new Random();                        
                    int direction = rand.nextInt(4);
                    if (direction == 0) {
                        coord = h.goNorth();
                    }
                    else if (direction == 1) {
                        coord = h.goSouth();
                    }                        
                    else if (direction == 2) {
                        coord = h.goEast();
                    }
                    else if (direction == 3) {
                        coord = h.goWest();
                    }                    
                }
                if (coord == 'f') {
                    music.stop();
                    System.out.println("You found the exit. Proceeding to the next level.\n");
                    MusicPlayer music2 = new MusicPlayer("C:\\Users\\silve\\Desktop\\Java\\CECS 277\\Project 1\\levelup.wav");
                    music2.play(0);
                    h.levelUp();
                }
            }
        }
        if (alive) {
            music.stop();
            return true;
        }
        music.stop();
        return false;
    }

    /**
     * player and enemy fight until one of them dies
     * @param h
     * @param e
     * @return true if player is alive, false otherwise
     */
    public static boolean fight(Hero h, Enemy e) {
        Scanner input = new Scanner(System.in);
        int option = 0;
        String fightingString = "";

        while (option == 0) {
            System.out.println("1. Physical Attack\n2. Magical Attack");
            option = CheckInput.getIntRange(1, 2);
            if (option == 1) {
                fightingString = h.attack(e);
                }
            else {
                System.out.println(Magical.MAGIC_MENU);
                int choice = CheckInput.getIntRange(1, 3);
                if (choice ==  1){
                    fightingString = h.magicMissile(e);
                }
                else if (choice ==  2){
                    fightingString = h.fireball(e);
                }
                else if (choice ==  3){
                    fightingString = h.thunderclap(e);
                }
            }             
        }
        System.out.println(fightingString); 
        if (h.getHp() != 0) {
            if (e.getHp() != 0) {
                fightingString = e.attack(h);
                System.out.println(fightingString + "\n");
                if (h.getHp() != 0) {
                    return true;
                }    
                return false;
            }
            else {
                System.out.println("You defeated the " + e.getName() + "!\n");
                return true;
            }
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        EnemyGenerator generator = new EnemyGenerator();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello fellow traveler. What is your name? ");
        String name = in.nextLine();
        System.out.println("Loading...\n");

        Hero player = new Hero(name);
        System.out.println(player);

        int input = 0;
        char coord = 'x';
        boolean alive = true;

        while (input == 0 || alive) {
            System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
            input = CheckInput.getIntRange(1, 5);
            if (input == 1) {
                coord = player.goNorth();
            }
            else if (input == 2) {
                coord = player.goSouth();
            }                 
            else if (input == 3) {
                coord = player.goEast();
            }
            else if (input == 4) {
                coord = player.goWest();
            }   
            else if (input == 5) {
                break;
            } 
            //m -> monsterRoom, x -> out of bounds, i -> heal, n -> nothing, f -> next map
            if (coord == 'm') {
                Enemy e = generator.generateEnemy();
                alive = monsterRoom(player, e);
            }
            else if (coord == 'x') {
                System.out.println("You cannot go out of bounds\n");
            }
            else if (coord == 'i') {
                System.out.println("You found a Health Potion! You drink it to restore your health\n");
                player.heal(25);
            }
            else if (coord == 'n') {
                System.out.println("There is nothing here.\n");
            }
            else if (coord == 'f') {
                System.out.println("You found the exit. Proceeding to the next level.\n");
                MusicPlayer music = new MusicPlayer("C:\\Users\\silve\\Desktop\\Java\\CECS 277\\Project 1\\levelup.wav");
                music.play(0);
                player.levelUp();
            }
            System.out.println(player);    
        }
        MusicPlayer music = new MusicPlayer("C:\\Users\\silve\\Desktop\\Java\\CECS 277\\Project 1\\died.wav");
        music.play(0);
        System.out.println("Game Over\n");
    }
}