package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-12 23:27:38
 */
@Slf4j
public class Sheele extends Role {
    public static final String NAME = "希儿";
    private static final Long ATK = 23L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;
    private static final int SKILL_ROUND = 4;
    private static final double SKILL_HIT = 0.25;
    private static final Long SKILL_ATTACK = 100L;

    private static final Long CURE_POINT = 7L;

    public Sheele (){
        super(NAME, ATK, DEF, MAX_HP, 0, 0, SKILL_ROUND);
    }

    @Override
    public void myTurn(Role target) {
        cure(CURE_POINT);
        super.myTurn(target);
    }

    @Override
    public void skillAttack(Role target) {
        Damage damage = new Damage(0L, 0L);
        if (Math.random() <= SKILL_HIT) {
            damage.setPhysicDamage(SKILL_ATTACK - target.getDef());
            target.underAttack(this, damage);
        } else {
            log.info("可惜没命中");
        }
    }
}
