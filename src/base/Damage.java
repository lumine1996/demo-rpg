package base;

/**
 * @author 675056544@qq.com 2019-07-12 18:10:29
 */
public class Damage {
    private Long physicalDamage;

    private Long magicDamage;

    public Damage (Long physicalDamage, Long magicDamage) {
        this.physicalDamage = physicalDamage;
        this.magicDamage = magicDamage;
    }

    public Damage() {
        this.physicalDamage = 0L;
        this.magicDamage = 0L;
    }

    public Long getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(Long physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public Long getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(Long magicDamage) {
        this.magicDamage = magicDamage;
    }
}
