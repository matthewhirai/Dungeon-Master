public abstract class EnemyDecorator extends Enemy{
    private Enemy enemy;

    public EnemyDecorator(Enemy e, String n, int h) {
        super(n, h);
        enemy = e;
    }

    /**
     * Amount of damage enemy does to player
     * @param e
     * @return string of damage done to player
     */
    @Override
    public String attack(Entity e) {
        return enemy.attack(e);
    }
}
