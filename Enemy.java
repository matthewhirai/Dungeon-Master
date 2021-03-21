import java.util.Random;
public class Enemy extends Entity{
    /**
     * calls Entity calls to get name and maxHp
     * @param n
     * @param mHp
     */
    public Enemy(String n, int mHp) {
        super(n, mHp);
    }

    @Override
    public String attack(Entity h) {
        Random random = new Random();
        int damage = random.nextInt(3) + 1;
        String str = getName() + " attacks " + h.getName() + " for " + 
            damage + " damage";
        h.takeDamage(damage);
        return str;
    }
}
