package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-14 13:51:31
 */
@Slf4j
public class Mei extends Role {
    public static final String NAME = "芽衣";
    private static final Long ATK = 26L;
    private static final Long DEF = 6L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0.3;
    private static final int SKILL_ROUND = 0;

    // 普攻附带伤害
    private static final Long MGK = 5L;

    // 技能额外伤害
    private static final Long SKILL_DAMAGE = 15L;

    public Mei() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        Damage normalDamage = new Damage(ATK - this.getDebuff().getAtkDownPoint() - target.getDef(), MGK);
        Damage finalDamage = target.underAttack(this, normalDamage);
        afterAttack(target, finalDamage);
    }

    @Override
    public void skillAttack(Role target) {
        normalAttack(target);

        Damage extraDamage = new Damage(0L, SKILL_DAMAGE);
        target.underAttack(this, extraDamage);
        target.setSilentRound(1);
        log.info(NAME + "使" + target.getName() + "下回合无法使用技能");
    }

}
