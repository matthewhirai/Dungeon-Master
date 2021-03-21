import java.awt.Point;
import java.util.Random;

public class Hero extends Entity implements Magical {
    private Map map = new Map();
    private Point loc;
    private int level;

    /**
     * creates an entity with 25 maxHp and starts at lvl 1
     * @param n
     */
    public Hero(String n) {
        super(n, 25);
        level = 1;
        map.loadMap(level);
        loc = map.findStart();
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
     * @return string that displays name, hp and maxHp, lvl num, and map
     */
    public String toString() {
        String s = super.toString() + "\nLevel: " +
            level + "\n" + map.mapToString(loc);
        return s;
    }

    /**
     * loads the map to the associated lvl
     */
    public void levelUp() {
        int maplevel = 1;
        level++;
        maplevel = level % 3;
        if (maplevel == 0) {
            maplevel = 3;
        }
        map.loadMap(maplevel);
    }

    /**
     * moves the player north or up 1
     * @return char of where the player is
     */
    public char goNorth() {
        int x = (int) loc.getX() - 1;
        if (x < 0 || x > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point(x, (int) loc.getY()));
            map.reveal(loc);
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            else {
                map.removeCharAtLoc(loc);
            }
            return place;
        }
    }

    /**
     * moves the player south or down 1
     * @return char of where the player is
     */
    public char goSouth() {
        int x = (int) loc.getX() + 1;
        if (x < 0 || x > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point(x, (int) loc.getY()));
            map.reveal(loc);
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            else {
                map.removeCharAtLoc(loc);
            }
            return place;
        }
    }

    /**
     * moves the player east or right 1
     * @return char of where the player is
     */
    public char goEast() {
        int y = (int) loc.getY() + 1;
        if (y < 0 || y > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point((int) loc.getX(), y));
            map.reveal(loc);
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            else {
                map.removeCharAtLoc(loc);
            }
            return place;
        }
    }

    /**
     * moves the player west or left 1
     * @return char of where the player is
     */
    public char goWest() {
        int y = (int) loc.getY() - 1;
        if (y < 0 || y > 4) {
            return 'x';
        }
        else {
            loc.setLocation(new Point((int) loc.getX(), y));
            map.reveal(loc);
            char place = map.getCharAtLoc(loc);
            if (place == 's') {
                System.out.println("You are back at the start.\n");
            }
            else {
                map.removeCharAtLoc(loc);
            }
            return place;
        }
    }
 }
