package model;

import base.Damage;
import base.Role;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2020-07-31
 */
@Slf4j
public class Fuka2020 extends Role {
    public static final String NAME = "符华";
    private static final Long ATK = 17L;
    private static final Long DEF = 15L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    private static final Long SKILL_DAMAGE = 18L;
    private static final double REDUCE_HIT = 0.25;

    public Fuka2020() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        Damage damage = new Damage(0L, getAtk());
        Damage finalDamage = target.underAttack(this, damage);
        afterAttack(target, finalDamage);
    }


    @Override
    public void skillAttack(Role target) {
        log.info(this.getName() + "发动形之笔墨泼了" + target.getName() + "一脸墨水");
        Damage damage = new Damage(0L, SKILL_DAMAGE);
        target.underAttack(this, damage);
        target.setHit(target.getHit() - target.getHit() * REDUCE_HIT);
        log.info(target.getName() + "的命中率降至" + target.getHit());
    }

}
