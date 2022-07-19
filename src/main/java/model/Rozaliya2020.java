package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-13 12:10:29
 */
@Slf4j
public class Rozaliya2020 extends Role {
    public static final String NAME = "萝莎莉娅";
    private static final Long ATK = 18L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 0;

    private static final Long SKILL_MAX_DAMAGE = 233L;
    private static final Long SKILL_MIN_DAMAGE = 50L;
    private static final double SKILL_MAX_RATE = 0.5;
    private double damageRate;
    private boolean hasRevive;

    public Rozaliya2020(){
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        Damage damage = new Damage();
        if (Math.random() <= SKILL_MAX_RATE) {
            log.info("萝莎莉娅使用了变成大星星");
            damage.setPhysicDamage(SKILL_MAX_DAMAGE - target.getDef());
        } else {
            log.info("萝莎莉娅使用了变成小星星");
            damage.setPhysicDamage(SKILL_MIN_DAMAGE - target.getDef());
        }
        target.underAttack(this, damage);
        setSkillChance(0);
    }

    @Override
    public Damage underAttack(Role from, Damage damage) {

        damage = super.underAttack(from, damage);
        // 复活触发机制
        if (this.getHp() <= 0 && !hasRevive) {
            this.setHp(20L);
            hasRevive = true;
            // 必杀技概率从0提升到1，使用后再降到0
            setSkillChance(1);
            log.info("罗莎莉亚免疫了致命伤害，血量变为20");
        }
        return damage;
    }

    public double getDamageRate() {
        return damageRate;
    }

    public void setDamageRate(double damageRate) {
        this.damageRate = damageRate;
    }
}
