package base;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-14 15:36:02
 */

@Slf4j
public class Debuff {
    private int round;
    private Damage damage;
    private Long atkDownPoint;

    public Debuff() {
        this.round = 0;
        this.damage = new Damage();
        this.atkDownPoint = 0L;
    }

    public Debuff(int round, Damage damage) {
        this.round = round;
        this.damage = damage;
        this.atkDownPoint = 0L;
    }

    public Debuff(int round, Long atkDownPoint) {
        this.round = round;
        this.damage =  new Damage();
        this.atkDownPoint = atkDownPoint;
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

    public Long getAtkDownPoint() {
        return atkDownPoint;
    }

    public void setAtkDownPoint(Long atkDownPoint) {
        this.atkDownPoint = atkDownPoint;
    }
}
