public class Froglok extends Enemy{
    /**
     * Creates a Froglock w/ 3 hp
     */
    public Froglok() {
        super("Froglock", 3);
    }

    /**
     * Attacks w/ 1 - 3 damage
     */
    @Override
    public String attack(Entity e) {
        int physicaldamage = (int) Math.floor( Math.random() * (4 - 1) + 1); 
        e.takeDamage( physicaldamage );
        return " attacks " + e.getName() + " for " + physicaldamage + " damage!";
    }
}
