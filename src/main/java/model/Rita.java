package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-12 20:59:17
 */
@Slf4j
public class Rita extends Role {
    public static final String NAME = "丽塔";
    private static final Long ATK = 26L;
    private static final Long DEF = 8L;
    private static final Long HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0.20;
    private static final int SKILL_ROUND = 0;

    private static final double CURE_RATE = 0.30;

    public Rita (){
        super(NAME, ATK, DEF, HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void afterAttack(Role target, Damage damage) {
        // 30%概率攻击回血
        if (Math.random() <= CURE_RATE) {
            this.setHp(this.getHp() + damage.getPhysicDamage());
            if (this.getHp() >= HP) {
                this.setHp(100L);
            }
            log.info(this.getName() + "恢复了" + damage.getPhysicDamage() + "点生命");
        }

    }

    @Override
    public void skillAttack(Role target) {
        //攻击并封锁对手攻击一回合
        normalAttack(target);
        target.setForbiddenRound(1);
        log.info(NAME + "使" + target.getName() + "下回合无法攻击");
    }
}
