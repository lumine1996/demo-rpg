package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-14 16:15:23
 */

@Slf4j
public class Sakura2020 extends Role {
    public static final String NAME = "八重樱";
    private static final Long ATK = 28L;
    private static final Long DEF = 7L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0.25;
    private static final int SKILL_ROUND = 0;
    private static final double CURE_RATE = 0.30;

    public Sakura2020() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        if (Math.random() <= CURE_RATE) {
            cure(25L);
            log.info(NAME + "使用饭团，当前血量" + getHp());
        }
        super.normalAttack(target);

    }

    @Override
    public void skillAttack(Role target) {
        log.info(NAME + "发动了必杀技");
        Damage damage = new Damage(0L, 25L);
        target.underAttack(this, damage);
    }
}
