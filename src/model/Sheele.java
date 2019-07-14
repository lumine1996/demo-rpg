package model;

import base.Damage;
import base.Role;

/**
 * @author 675056544@qq.com 2019-07-12 23:27:38
 */
public class Sheele extends Role{
    public static final String NAME = "希儿";
    private static final Long ATK = 23L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;
    private static final int SKILL_ROUND = 4;
    private static final double SKILL_HIT = 0.25;
    private static final Long SKILL_ATTACK = 100L;

    private static final Long CURE_POINT = 7L;

    public Sheele (){
        super(NAME, ATK, DEF, MAX_HP, 0, 0.0, SKILL_ROUND);
    }

    @Override
    public void myTurn(Role target) {
        cure(CURE_POINT);
        super.myTurn(target);
    }

    @Override
    public void skillAttack(Role role) {
        System.out.print(this.getName() + "使用了必杀技，");
        Damage damage = new Damage(0L, 0L);
        if (Math.random() <= SKILL_HIT) {
            damage.setPhysicalDamage(SKILL_ATTACK - role.getDef());
            role.underAttack(damage);
            System.out.println("对" + role.getName() + "造成了" + damage.getPhysicalDamage() + "点伤害");
        } else {
            System.out.println("可惜没命中");
        }
    }
}
