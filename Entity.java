public abstract class Entity {
    private String name;
    private int maxHp;
    private int hp;

    public Entity(String name, int mHp) {
        this.name = name;
        maxHp = mHp;
        hp = mHp;
    }

    @Override
    public abstract String attack(Entity e);

    /**
     * gets the name of entity
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the hp of entity
     * @return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * gets the max hp of the entity
     * @return maxHp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * adds health to the player with a max of the maxHp
     * @param h
     */
    public void heal(int h) {
        hp += h;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    /**
     * subtracts health from entity, but cannot go below 0
     * @param d
     */
    public void takeDamage(int d) {
        hp -= d;
        if (hp < 0) {
            hp = 0;
        }
    }

    /**
     * @returns string of name, hp, and maxHp
     */
    public String toString() {
        String s = name + "\nHP: " + hp + "/" + maxHp;
        return s;
    }
}
