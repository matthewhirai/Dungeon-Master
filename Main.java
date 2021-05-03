import java.util.*;
import java.io.*;
import java.awt.Point;
import javax.sound.sampled.*;
public class Main{

    /**
     * player fights monster or runs away in a random direction from monster 
     * @param h
     * @param e
     * @return true if player is alive, false otherwise
     */
    public static boolean monsterRoom(Hero h, Enemy e) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        MusicPlayer music = new MusicPlayer("ema.wav");
        music.play(0);
        System.out.println("You've encountered a " +  e.getName() + "\n"); 
        int option = 0;
        boolean alive = true;
        char coord = 'x';
        Map map = Map.getInstance();

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
                if (coord == 'i') {
                    int item = (int) Math.floor(Math.random() * 2) + 1;
                    map.removeCharAtLoc(h.getLoc());
                    if (item == 1) {
                        System.out.println("You found a Health Potion! You drink it to restore your health.\n");
                        h.heal(15);
                    }
                    else if (item == 2) {
                        System.out.println("You found a Key! You put it in your pocket.\n");
                        h.pickUpKey();
                    }
                }
                else if (coord == 'f') {
                    if (h.hasKey()) {
                        System.out.println("You found the exit. Proceeding to the next level.\n");
                        h.useKey();
                        music.stop();
                        MusicPlayer music2 = new MusicPlayer("levelup.wav");
                        music2.play(0);
                        h.levelUp();
                    }
                    else {
                        System.out.println("You don't have a key to open the door.\n");
                    }
                }
                else if (coord == 's') {
                    store(h);
                }
            }
        }
        music.stop();
        if (alive) {
            return true;
        }
        return false;
    }

    /**
     * player and enemy fight until one of them dies
     * @param h
     * @param e
     * @return true if player is alive, false otherwise
     */
    public static boolean fight(Hero h, Enemy e) {
        Map map = Map.getInstance();
        int option = 0;
        String fightingString = "";
        int gold = (int) Math.floor(Math.random() * 8) + 3;

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
                System.out.println(e.getName() + " " + fightingString + "\n");
                if (h.getHp() != 0) {
                    return true;
                }    
                return false;
            }
            else {
                System.out.println("You defeated the " + e.getName() + "!\n");
                System.out.println("You got " + gold + "G!");
                h.collectGold(gold); 
                map.removeCharAtLoc(h.getLoc());
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * store has potions and keys to sell to player
     * potion immediately consumed
     * key is placed in inventory until used
     * @param h
     */
    public static void store(Hero h){
        int option = 0;
        int item = 0;
        System.out.print("Welcome to my store traveler. ");
        while (option != 2) {
            System.out.println("What would you like to do?\n1. Enter\n2. Exit");
            option = CheckInput.getIntRange(1, 2);
            if (option == 1) {
                System.out.println("What would you like to buy?\nYou have "+h.getGold()+"."+"\n1. Health Potion   25g\n2. Key             50g");
                item = CheckInput.getIntRange(1, 2);
                if (item == 1 && h.getGold() >= 25) {
                    System.out.println("You buy Health Potion! You drink it to restore your health.\n");
                    h.heal(15);
                    h.spendGold(25);
                }
                else if (item == 2 && h.getGold() >= 50) {
                    System.out.println("You buy a key! You puy it in your pocket.\n");
                    h.pickUpKey();
                    h.spendGold(50);
                }
                else {
                    System.out.println("You cannot afford the item.");
                    option = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        MusicPlayer music;
        Map map = Map.getInstance();
        EnemyGenerator generator = new EnemyGenerator();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello fellow traveler. What is your name? ");
        String name = in.nextLine();
        System.out.println("Loading...\n");

        Hero player = new Hero(name);
        System.out.println(player);

        int input = 0;
        int item;
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
            //m -> monsterRoom, x -> out of bounds, i -> heal, n -> nothing, f -> next map, s -> store
            if (coord == 'm') {
                map.reveal(player.getLoc());
                Enemy e = generator.generateEnemy(player.getLevel());
                alive = monsterRoom(player, e);
            }
            else if (coord == 'x') {
                System.out.println("You cannot go out of bounds\n");
            }
            else if (coord == 'i') {
                item = (int) Math.floor(Math.random() * 2) + 1;
                map.removeCharAtLoc(player.getLoc());
                if (item == 1)
                {
                  System.out.println("You found a Health Potion! You drink it to restore your health.\n");
                  player.heal(15);
                }
                else if (item == 2)
                {
                  System.out.println("You found a Key! You put it in your pocket.\n");
                  player.pickUpKey();
                }
            }
            else if (coord == 'n') {
                System.out.println("There is nothing here.\n");
            }
            else if (coord == 'f') {
                if (player.hasKey()) {
                    System.out.println("You found the exit. Proceeding to the next level.\n");
                    player.useKey();
                    music = new MusicPlayer("levelup.wav");
                    music.play(0);
                    player.levelUp();
                }
                else {
                    System.out.println("You don't have a key to open the door.\n");
                }
            }
            else if (coord == 's') {
                store(player);
            }
            System.out.println(player);    
        }
        music = new MusicPlayer("died.wav");
        music.play(0);
        System.out.println("Game Over\n");
    }
}