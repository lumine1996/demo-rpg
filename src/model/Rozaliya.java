package model;

import base.Damage;
import base.Role;

/**
 * @author 675056544@qq.com 2019-07-13 12:10:29
 */
public class Rozaliya extends Role {
    public static final String NAME = "萝莎莉娅";
    private static final Long ATK = 30L;
    private static final Long DEF = 4L;
    private static final Long HP = 100L;
    private static final int SKILL_ROUND = 3;
    private static final Long SKILL_DAMAGE = 15L;
    private static final double CHANGE_RATE = 0.3;
    private static final double BUFF_RATE = 0.5;
    private double damageRate;

    public Rozaliya (){
        super(NAME, ATK, DEF, HP, 0, SKILL_ROUND);
    }

    @Override
    public void beforeAttack(Role role) {
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
        super.beforeAttack(role);
    }

    @Override
    public void skillAttack(Role target) {
        Long damageCount = 0L;
        for (int i = 0; i < 10; i++) {
            Long damage = Math.round(SKILL_DAMAGE * damageRate) - target.getDef();
            damageCount += damage;
        }

        //下回合无法攻击
        this.setSilentRound(1);
        System.out.println(this.getName() + "发动必杀技，对" + target.getName() + "累计造成" + damageCount + "点伤害，" +
                "但下回合无法攻击");
        Damage damage = new Damage(damageCount, 0L);
        target.underAttack(damage);
    }

    @Override
    public void normalAttack(Role target) {
        Damage damage = new Damage(Math.round(this.getAtk() * this.damageRate) - target.getDef(), 0L);
        target.underAttack(damage);
        afterAttack(target, damage);
    }

    public double getDamageRate() {
        return damageRate;
    }

    public void setDamageRate(double damageRate) {
        this.damageRate = damageRate;
    }
}
