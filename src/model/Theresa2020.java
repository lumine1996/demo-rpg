package model;

import base.Damage;
import base.Role;

import java.util.Random;

/**
 * @author 675056544@qq.com 2020-07-31
 */
public class Theresa2020 extends Role {
    public static final String NAME = "德丽莎";
    private static final Long ATK = 19L;
    private static final Long DEF = 12L;
    private static final Long MAX_HP = 100L;
    private static final int SKILL_ROUND = 3;

    private static final double REDUCE_DEF_CHANCE = 0.3;
    private static final Long REDUCE_DEF_POINT = 5L;
    private static final int KICK_TIMES = 5;
    private static final Long KICK_DAMAGE = 16L;


    private final Random rand = new Random();

    public Theresa2020(){
        super(NAME, ATK, DEF, MAX_HP, 0, 0, SKILL_ROUND);
    }

    @Override
    public void afterAttack(Role target, Damage damage) {
        if (Math.random() <= REDUCE_DEF_CHANCE) {
            target.setDef(target.getDef() - REDUCE_DEF_POINT);
            if (target.getDef() < 0) {
                target.setDef(0L);
                System.out.println(target.getName() + "防御力被降到了0点");
            } else {
                System.out.println(target.getName() + "防御力被降低了5点");
            }
        }
    }

    @Override
    public void skillAttack(Role target) {
        System.out.println(this.getName() + "对" + target.getName() + "使用了在线踢人");
        int damageCount = 0;
        for (int i = 0; i < KICK_TIMES; i++) {
            Damage kickDamage = new Damage(KICK_DAMAGE - target.getDef(), 0L);
            Damage realDamage = target.underAttack(this, kickDamage);
            damageCount += realDamage.getPhysicDamage();
        }
        System.out.println("在线踢人累计造成" + damageCount + "点伤害");
    }
}
