import java.util.*;
import java.io.*;
import java.awt.Point;
public class Map {
    private char[][] map = new char[5][5];;
    private boolean[][] revealed = new boolean[5][5];
    private static Map instance;

    /**
     * Singleton
     */
    private Map() {}

    /**
     * Makes that so there's only one map
     * @return instance
     */
    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    /**
     * loads the map file
     * @param mapNum takes the map number and changes it accordingly
    */
    public void loadMap(int mapNum) {
        try {
            String input = "";
            if (mapNum == 1) {
                input = "C:\\Users\\silve\\Desktop\\Java\\CECS 277\\Project 1\\Map1.txt";
            }
            else if (mapNum == 2) {
                input = "Map2.txt";
            }
            else if (mapNum == 3) {
                input = "Map3.txt";
            }
            Scanner read = new Scanner(new File(input));
            int i = 0;
            if (i != 6){
                while (read.hasNextLine()) {
                    String line = read.nextLine();  
                    String[] c = line.split(" ");
                    for (int k = 0; k < c.length; k++) {
                        map[i][k] = c[k].charAt(0);
                    }  
                    i++;
                }
            }
            read.close();
            for (int e = 0; e < revealed.length; e++) {
                for (int j = 0; j < revealed[0].length; j++) {
                    revealed[e][j] = Boolean.FALSE;
                }
            }

        }catch (FileNotFoundException fnf){
            System.out.println("File was not found");
        }
    }
    
    /**
     * returns the char at the point
     * @param p take the x and y value from Point class
     * @return char at (p.x, p.y)
     */
    public char getCharAtLoc(Point p) {
        return map[p.x][p.y];
    }

    /**
     * Gives map w/ revealed and unrevealed chars, and player location
     * if statement: checks if the player coord is the same as the current map coord and then makes it revealed
     * else if statement: checks if the coord was revealed previously by the player or has not been
     * @param p take the x and y value from Point class
     * @return returns string 
    */
    public String mapToString(Point p) {
        String s = "";
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (p.x == i && p.y == j) {
                    reveal(new Point(i, j));
                    s += '*' + " ";
                }
                else if (p.x != i || p.y != j){
                    if (revealed[i][j] == Boolean.FALSE) {
                        s += 'x' + " ";
                    }
                    else {
                        s += map[i][j] + " "; 
                    }  
                }   
            }
            s += "\n";
        }
        return s;
    }
    
    /**
     * Finds the start of the map
     * @return returns (x, y) coordinate
    */
    public Point findStart() {
        for (int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[0].length; k++) {
                if (map[i][k] == 's') {
                    revealed[i][k] = Boolean.TRUE;
                    return new Point(i, k);
                }
            }
        }
        return new Point();
    }

    /**
     * reveals the coord on the map where the player already has been to
     * @param p take the x and y value from Point class
     */
    public void reveal(Point p) {
        revealed[p.x][p.y] = Boolean.TRUE;
    }

    /**
     * Removes char at (x, y) and replaces with n
     * @param p take the x and y value from Point class
    */
    public void removeCharAtLoc(Point p) {
        map[p.x][p.y] = 'n';
    }
}