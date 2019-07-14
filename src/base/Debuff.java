package base;

/**
 * @author guangming.chen@hand-china.com 2019-07-14 15:36:02
 */
public class Debuff {
    private int round;
    private Damage damage;

    public Debuff() {
        this.round = 0;
        this.damage = new Damage();
    }

    public Debuff(int round, Damage damage) {
        this.round = round;
        this.damage = damage;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }
}
