public class Warrior extends EnemyDecorator{
    
    /**
     * Decorates enemy as a warrior
     * @param e
     */
    public Warrior(Enemy e) {
        super(e, e.getName(), e.getHp() + 2);
    }

    /**
     * Attacks w/ 1 - 3 damage
     */
    @Override
    public String attack(Entity e) {
        int physicaldamage = (int) Math.floor( Math.random() * (4 - 1) + 1); 
        e.takeDamage( physicaldamage );
        return "attacks " + e.getName() + " for " + physicaldamage + " damage!" + "\n" + super.attack(e);
    }

    /**
     * Returns name with arrior decorated
     */
    public String getName() {
        return super.getName() + " Warrior";
    }
}
