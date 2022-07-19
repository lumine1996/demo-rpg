package base;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-12 18:10:29
 */

@Slf4j
public class Damage {
    private Long physicDamage;

    private Long magicDamage;

    public Damage (Long physicDamage, Long magicDamage) {
        this.physicDamage = physicDamage;
        this.magicDamage = magicDamage;
    }

    public Damage() {
        this.physicDamage = 0L;
        this.magicDamage = 0L;
    }

    public Long getPhysicDamage() {
        return physicDamage;
    }

    public void setPhysicDamage(Long physicDamage) {
        this.physicDamage = physicDamage;
    }

    public Long getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(Long magicDamage) {
        this.magicDamage = magicDamage;
    }
}
