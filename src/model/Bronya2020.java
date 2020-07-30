package model;

import base.Damage;
import base.Debuff;
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
    // 普通攻击附带钻头的概率
    private static final double DRILL_RATE = 0.25;
    // 钻头伤害
    private static final Long DRILL_DAMAGE = 12L;

    private static final int SKILL_ROUND = 3;
    private final Random rand = new Random();

    public Bronya2020 (){
        super(NAME, ATK, DEF, HP, AVD, 0.0, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        super.normalAttack(target);
        if (Math.random() <= DRILL_RATE) {
            Damage drillDamage = new Damage(DRILL_DAMAGE - target.getDef(), 0L);
            target.underAttack(this.getName(), drillDamage);
            System.out.println(this.getName() + "对" + target.getName() + "使用了钻头攻击");
        }
    }

    @Override
    public void skillAttack(Role target) {
        //造成1~100点元素伤害
        long hurt = rand.nextInt(100) + 1L;
        Damage damage = new Damage(0L, hurt);
        target.underAttack(this.getName(), damage);
    }
}
