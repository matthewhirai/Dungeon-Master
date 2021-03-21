import java.util.Random;

public class MagicalEnemy extends Enemy implements Magical{

    /**
     * creates a magical enemy
     * @param n
     * @param mHp
     */
    public MagicalEnemy(String n, int mHp) {
        super(n, mHp);
    }

    @Override
    public String attack(Entity e) {
        Random random = new Random();
        int attackType = random.nextInt(3) + 1;
        String str = "";
        switch(attackType) {
            case 1:
                str = magicMissile(e);
                break;
            case 2:
                str = fireball(e);
                break;
            case 3:
                str = thunderclap(e);
                break;
        }
        return str;
    }

    @Override
    public String magicMissile(Entity e){
        int damage = (int) ((Math.floor(Math.random() * ((6 - 4) + 1))+ 4)); //range is 4-6
        e.takeDamage(damage);
        return getName() + " shoots " + e.getName() + " for " + damage + " damage!"; 
    }

    @Override
    public String fireball(Entity e){
        int damage = (int) ((Math.floor(Math.random() * ((9 - 6) + 1))+ 6)); //should be 6-8
        e.takeDamage(damage);
        return getName() + " incinerates " + e.getName() + " for " + damage + " damage!"; 
    }

    @Override
    public String thunderclap(Entity e){
        int damage = (int) ((Math.floor(Math.random() * ((10 - 8) + 1))+ 8)); //range 8-10
        e.takeDamage(damage);
        return getName() + " zaps " + e.getName() + " for " + damage + " damage!"; 
    }
}
