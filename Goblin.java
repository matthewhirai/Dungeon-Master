public class Goblin extends Enemy{
    /**
     * Creates a Goblin w/ 2 hp
     */
    public Goblin() {
        super("Goblin", 2);
    }

    /**
     * Attacks w/ 1 - 2 damage
     */
    @Override
    public String attack(Entity e) {
        int physicaldamage = (int) Math.floor( Math.random() * (2 - 1) +1); 
        e.takeDamage( physicaldamage );
        return " attacks " + e.getName() + " for " + physicaldamage + " damage!";
    }
}
