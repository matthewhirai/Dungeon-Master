public class Troll extends Enemy{

    /**
     * Creates a Troll w/ 5 hp
     */
    public Troll() {
        super("Troll", 5);
    }

    /**
     * Attacks w/ 0 - 5 damage
     */
    @Override
    public String attack(Entity e) {
        int physicaldamage = (int) Math.floor( Math.random() * 6 ); 
        e.takeDamage( physicaldamage );
        return " attacks " + e.getName() + " for " + physicaldamage + " damage!";
    }
}
