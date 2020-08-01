package model;

import base.Damage;
import base.Role;

import java.util.Random;

/**
 * @author 675056544@qq.com 2020-07-30
 */
public class Bronya2020 extends Role {
    public static final String NAME = "布洛妮娅";
    private static final Long ATK = 21L;
    private static final Long DEF = 10L;
    private static final Long HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    // 普通攻击附带钻头的概率
    private static final double DRILL_CHANCE = 0.25;
    // 钻头伤害
    private static final Long DRILL_DAMAGE = 12L;
    // 钻头攻击次数
    private static final int DRILL_TIMES = 4;

    private final Random rand = new Random();

    public Bronya2020 (){
        super(NAME, ATK, DEF, HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        super.normalAttack(target);
        if (Math.random() <= DRILL_CHANCE) {
            System.out.println(this.getName() + "对" + target.getName() + "使用了钻头攻击");
            int damageCount = 0;
            for (int i = 0; i < DRILL_TIMES; i++) {
                Damage drillDamage = new Damage(DRILL_DAMAGE - target.getDef(), 0L);
                Damage realDamage = target.underAttack(this, drillDamage);
                damageCount += realDamage.getPhysicDamage();
            }
            System.out.println("钻头攻击累计造成" + damageCount + "点伤害");
        }
    }

    @Override
    public void skillAttack(Role target) {
        //造成1~100点元素伤害
        Long hurt = rand.nextInt(100) + 1L;
        Damage damage = new Damage(0L, hurt);
        target.underAttack(this, damage);
    }
}
