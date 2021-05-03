public class Orc extends Enemy {
    /**
     * Creates a Orc w/ 4 hp
     */
    public Orc() {
        super("Orc", 4);
    }

    /**
     * Attacks w/ 0 - 4 damage
     */
    @Override
    public String attack(Entity e) {
        int physicaldamage = (int) Math.floor( Math.random() * 5 ); 
        e.takeDamage( physicaldamage );
        return " attacks " + e.getName() + " for " + physicaldamage + " damage!";
    }
}
