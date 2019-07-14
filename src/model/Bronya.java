package model;

import base.Damage;
import base.Role;
import java.util.Random;

/**
 * @author 675056544@qq.com 2019-07-12 22:14:32
 */
public class Bronya extends Role {
    public static final String NAME = "布洛妮娅";
    private static final Long ATK = 26L;
    private static final Long DEF = 8L;
    private static final Long HP = 100L;
    private static final double HIT = 1.0;
    private static final double AVD = 0.15;
    private static final int SKILL_ROUND = 3;
    private Random rand = new Random();

    public Bronya (){
        super(NAME, ATK, DEF, HP, HIT, AVD, 0, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        //造成1~100点伤害
        long hurt = rand.nextInt(100) + 1L;
        Damage damage = new Damage(hurt, 0L);
        target.underAttack(damage);
        System.out.println(this.getName() + "发动必杀技，对" + target.getName() + "造成" + damage.getPhysicalDamage() + "点伤害");
    }
}
