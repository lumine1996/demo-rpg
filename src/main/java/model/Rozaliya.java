package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-13 12:10:29
 */
@Slf4j
public class Rozaliya extends Role {
    public static final String NAME = "萝莎莉娅";
    private static final Long ATK = 30L;
    private static final Long DEF = 4L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    private static final Long SKILL_DAMAGE = 15L;
    private static final double CHANGE_RATE = 0.3;
    private static final double BUFF_RATE = 0.5;
    private double damageRate;

    public Rozaliya (){
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void myTurn(Role role) {
        //第一回合正常攻击系数，之后30%概率1.5或0.5
        if (this.getRound() == 0) {
            this.damageRate = 1.0;
        } else {
            if (Math.random() <= CHANGE_RATE) {
                if (Math.random() <= BUFF_RATE) {
                    this.damageRate = 1.5;
                } else {
                    this.damageRate = 0.5;
                }
            } else {
                this.damageRate = 1.0;
            }
        }
        //调用父类攻击前行为
        super.myTurn(role);
    }

    @Override
    public void skillAttack(Role target) {
        Long damageCount = 0L;
        for (int i = 0; i < 10; i++) {
            Long physicDamage = Math.round(SKILL_DAMAGE * damageRate) - target.getDef();
            Damage damage = new Damage(physicDamage, 0L);
            Damage finalDamage = target.underAttack(this, damage);
            damageCount += finalDamage.getPhysicDamage();
        }

        //下回合无法攻击
        this.setForbiddenRound(1);
        log.info(NAME + "累计造成" + damageCount + "点伤害，但下回合无法攻击");
    }

    @Override
    public void normalAttack(Role target) {
        Damage damage = new Damage(Math.round((getAtk() - getDebuff().getAtkDownPoint()) * damageRate) - target.getDef(), 0L);
        Damage finalDamage = target.underAttack(this, damage);
        afterAttack(target, finalDamage);
    }
}
