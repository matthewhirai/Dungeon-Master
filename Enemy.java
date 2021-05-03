public abstract class Enemy extends Entity{
    /**
     * calls Entity calls to get name and maxHp
     * @param n
     * @param mHp
     */
    public Enemy(String n, int mHp) {
        super(n, mHp);
    }

    /**
     * Attacks hero
     * @param Entity e since all entities are able to attack any entity, we call the class itself as parameter
     * @returns string text for player to read after attacking which includes entity attacked and the damage done
     */
    public abstract String attack( Entity e );
}
