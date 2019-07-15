package model;

import base.Damage;
import base.Debuff;
import base.Role;

/**
 * @author 675056544@qq.com
 */
public class Kallen extends Role {
    public static final String NAME = "卡莲";
    private static final Long ATK = 26L;
    private static final Long DEF = 6L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0.0;
    private static final double SKILL_RATE = 0.3;
    private static final int SKILL_ROUND = 0;

    private static final double KILL_RATE = 0.05;

    public Kallen() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_RATE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        Damage normalDamage = new Damage(ATK - target.getDef(), 0L);
        Damage finalDamage = target.underAttack(NAME, normalDamage);
        afterAttack(target, finalDamage);
        if (Math.random() <= KILL_RATE && Math.random() >= target.getAvd()) {
            target.setHp(0L);
            System.out.println(this.getName() + "不小心把" + target.getName() + "打成了废人");
        }
    }

    @Override
    public void skillAttack(Role target) {
        normalAttack(target);
        Debuff debuff = new Debuff(2,  15L);
        target.setDebuff(debuff);
        System.out.println(this.getName() + "使" + target.getName() + "降低15点攻击力2回合");
    }
}
