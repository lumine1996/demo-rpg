package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-14 13:51:31
 */
@Slf4j
public class Mei2020 extends Role {
    public static final String NAME = "芽衣";
    private static final Long ATK = 22L;
    private static final Long DEF = 12L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0.3;
    private static final int SKILL_ROUND = 2;

    // 技能额外伤害
    private static final Long SKILL_DAMAGE = 3L;

    public Mei2020() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        if (Math.random() <= 0.3) {
            target.setForbiddenRound(1);
            log.info(NAME + "使" + target.getName() + "麻痹一回合");
        }
        super.normalAttack(target);
    }

    @Override
    public void skillAttack(Role target) {
        log.info(NAME + "发动了龙女仆");
        int damageCount = 0;
        for (int i = 0; i < 5; i++) {
            Damage kickDamage = new Damage(0L, SKILL_DAMAGE);
            Damage realDamage = target.underAttack(this, kickDamage);
            damageCount += realDamage.getMagicDamage();
        }
        log.info("龙女仆造成" + damageCount + "点伤害");
    }

}
