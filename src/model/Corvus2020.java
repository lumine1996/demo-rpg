package model;

import base.Damage;
import base.Role;

import java.util.Random;

public class Corvus2020 extends Role {

    public static final String NAME = "渡鸦";
    private static final Long ATK = 23L;
    private static final Long DEF = 14L;
    private static final Long HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    private boolean isBuff = false;

    public Corvus2020() {
        super(NAME, ATK, DEF, HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    public void startBattle(Role target) {
        if (target instanceof Kiana2020 || Math.random() <= 0.25) {
            this.isBuff = true;
            System.out.println(NAME + "伤害提高25%");
        }
    }

    @Override
    public void normalAttack(Role target) {
        if (isBuff) {
            Damage damage = new Damage((long)(ATK * 1.25) - target.getDef(), 0L);
            Damage finalDamage = target.underAttack(this, damage);
            afterAttack(target, finalDamage);
        } else {
            super.normalAttack(target);
        }
    }

    public void skillAttack(Role target) {
        System.out.println(this.getName() + "对" + target.getName() + "使用了别墅小岛");
        int damageCount = 0;
        for (int i = 0; i < 7; i++) {
            Damage kickDamage = new Damage(16 - target.getDef(), 0L);
            Damage realDamage = target.underAttack(this, kickDamage);
            damageCount += realDamage.getPhysicDamage();
        }
        System.out.println("别墅小岛累计造成" + damageCount + "点伤害");

    }
}
