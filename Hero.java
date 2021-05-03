import java.awt.Point;
import java.util.Random;

public class Hero extends Entity implements Magical {
    private Point loc;
    private int level;
    private int gold;
    private int key;

    /**
     * creates an entity with 25 maxHp and starts at lvl 1
     * @param n
     */
    public Hero(String n) {
        super(n, 25);
        level = 1;
        gold = 20;
        Map map = Map.getInstance();
        map.loadMap(level);
        loc = map.findStart();
    }

    /**
     * @return string that displays name, hp and maxHp, lvl num, and map
     */
    public String toString() {
        Map map = Map.getInstance();
        String s = super.toString() + "\nLevel: " +
            level + "\n" + getGold() + " G\n" + map.mapToString(loc);
        return s;
    }

    /**
     * loads the map to the associated lvl
     */
    public void levelUp() {
        Map map = Map.getInstance();
        int maplevel = 1;
        level++;
        maplevel = level % 3;
        if (maplevel == 0) {
            maplevel = 3;
        }
        map.loadMap(maplevel);
    }

    public int getLevel() {
        return level;
    }

    /**
     * Player's location
     * @return loctaion of player
     */
    public Point getLoc() {
        return loc;
    }

    /**
     * moves the player north or up 1
     * @return char of where the player is
     */
    public char goNorth() {
        Map map = Map.getInstance();
        int x = (int) loc.getX() - 1;
        if (x < 0 || x > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point(x, (int) loc.getY()));
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            return place;
        }
    }

    /**
     * moves the player south or down 1
     * @return char of where the player is
     */
    public char goSouth() {
        Map map = Map.getInstance();
        int x = (int) loc.getX() + 1;
        if (x < 0 || x > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point(x, (int) loc.getY()));
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            return place;
        }
    }

    /**
     * moves the player east or right 1
     * @return char of where the player is
     */
    public char goEast() {
        Map map = Map.getInstance();
        int y = (int) loc.getY() + 1;
        if (y < 0 || y > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point((int) loc.getX(), y));
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            return place;
        }
    }

    /**
     * moves the player west or left 1
     * @return char of where the player is
     */
    public char goWest() {
        Map map = Map.getInstance();
        int y = (int) loc.getY() - 1;
        if (y < 0 || y > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point((int) loc.getX(), y));
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            return place;
        }
    }

    @Override
    public String attack(Entity e) {
        int damage = (int) ((Math.floor(Math.random() * ((3 - 1) + 1))+ 1));
        String str = getName() + " attacks " + e.getName() + " for " + 
            damage + " damage";
        e.takeDamage(damage);
        return str;
    }

    @Override
    public String magicMissile(Entity e) {
        int damage = (int) ((Math.floor(Math.random() * ((5 - 3) + 1))+ 3));
        String str = getName() + " hits " + e.getName() +  " with a MagicMissle for " + 
            damage + " damage";
        e.takeDamage(damage);
        return str;
    }

    @Override
    public String fireball(Entity e) {
        int damage = (int) ((Math.floor(Math.random() * ((5 - 4) + 1))+ 4));
        String str = getName() + " hits " + e.getName() +  " with a Fireball for " + 
            damage + " damage";
        e.takeDamage(damage);
        return str;
    }

    @Override
    public String thunderclap(Entity e) {
        int damage = (int) ((Math.floor(Math.random() * ((6 - 5) + 1))+ 5));
        String str = getName() + " zaps " + e.getName() +  " with Thunderclap for " + 
            damage + " damage";
        e.takeDamage(damage);
        return str;
    }

    /**
     * Amount of gold player had
     * @return gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Gold is added to player's amount
     * @param g
     */
    public void collectGold(int g) {
        gold += g;
    }

    /**
     * Gold is subtracted from player's amount
     * @param g
     */
    public void spendGold(int g) {
        if (gold > g) {
            gold -= g;
        }
    }

    /**
     * Checks if player has a key
     * @return boolean
     */
    public boolean hasKey() {
        if (key != 0) {
            return true;
        }
        return false;
    }

    /**
     * Adds one key to player's inventory if found or bought
     */
    public void pickUpKey() {
        key += 1;
    }

    /**
     * Player uses key for next lvl and subtracts it from player's inventory
     * @return
     */
    public boolean useKey() {
        if (key >= 1) {
            key -= 1;
            return true;
        }
        return false;
    }
 }
