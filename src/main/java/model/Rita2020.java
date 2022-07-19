package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-12 20:59:17
 */
@Slf4j
public class Rita2020 extends Role {
    public static final String NAME = "丽塔";
    private static final Long ATK = 26L;
    private static final Long DEF = 11L;
    private static final Long HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 4;

    private static final double REDUCE_RATE = 0.35;
    private static final Long REDUCE_DAMAGE = 3L;
    private static final Long REDUCE_ATK = 4L;
    private static final Long CURE_POINT = 4L;

    private boolean hasConfused = false;


    public Rita2020(){
        super(NAME, ATK, DEF, HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        if (Math.random() <= REDUCE_RATE) {
            // 伤害降低3点，对方攻击降低4点
            log.info(NAME + "本次伤害减少3点，并使" + target.getName() + "攻击力降低4点");
            Damage damage = new Damage(getAtk() - target.getDef() - REDUCE_DAMAGE, 0L);
            Damage finalDamage = target.underAttack(this, damage);
            target.setAtk(target.getAtk() - REDUCE_ATK);
            afterAttack(target, finalDamage);
        } else {
            super.normalAttack(target);
        }

    }

    @Override
    public void skillAttack(Role target) {
        // 不造成伤害，沉默对手2回合
        target.cure(CURE_POINT);
        target.setSilentRound(2);
        log.info(NAME + "使" + target.getName() + "两个回合无法使用必杀技");
        hasConfused = true;
    }

    @Override
    public Damage underAttack(Role from, Damage damage) {
        if (hasConfused) {
            // 先还原未减去防御力的伤害，乘以0.4后再减去防御力
            damage.setPhysicDamage((long)((damage.getPhysicDamage() + this.getDef()) * 0.4) - this.getDef());
            // 元素伤害不考虑防御力
            damage.setMagicDamage((long) (damage.getMagicDamage() * 0.4));

        }
        return super.underAttack(from, damage);
    }
}
