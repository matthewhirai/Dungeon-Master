public class Warlock extends EnemyDecorator implements Magical{
    /**
     * Decorates enemy as a warrior
     * @param e
     */
    public Warlock(Enemy e) {
        super(e, e.getName(), e.getHp() + 1);
    }

    /**
     * Returns name with Warlock decorated
     */
    public String getName() {
        return super.getName() + " Warlock";
    }

    /**
     * Determines what magical attack the Warlock should use
     * Additional attack for every level greater than 1
     */
    @Override
    public String attack(Entity e) {
        int attackType = (int) Math.floor(Math.random() * (3 - 1) + 1);
        String str = "";
        switch(attackType) {
            case 1:
                str = magicMissile(e) + "\n" + super.attack(e); 
                break;
            case 2:
                str = fireball(e) + "\n" + super.attack(e);
                break;
            case 3:
                str = thunderclap(e) + "\n" + super.attack(e);
                break;
        }
        return str;
    }

    /**
     * Magic missile does 1 - 3 damage
     */
    @Override
    public String magicMissile(Entity e){
        int damage = (int) Math.floor( Math.random() * (4 - 1) + 1);
        e.takeDamage(damage);
        return "shoots " + e.getName() + " for " + damage + " damage!"; 
    }

    /**
     * Fireball does 2 - 3 damage
     */
    @Override
    public String fireball(Entity e){
        int damage = (int) Math.floor( Math.random() * (4 - 2) + 2);
        e.takeDamage(damage);
        return "incinerates " + e.getName() + " for " + damage + " damage!"; 
    }

    /**
     * Thunderclap does 3 - 4 damage
     */
    @Override
    public String thunderclap(Entity e){
        int damage = (int) Math.floor( Math.random() * (5 - 3) + 3);
        e.takeDamage(damage);
        return "zaps " + e.getName() + " for " + damage + " damage!"; 
    }
}
