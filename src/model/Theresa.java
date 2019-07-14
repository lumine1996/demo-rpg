package model;

import base.Damage;
import base.Debuff;
import base.Role;

import java.util.Random;

/**
 * @author 675056544@qq.com 2019-07-13 16:15:00
 */
public class Theresa extends Role {
    public static final String NAME = "德丽莎";
    private static final Long ATK = 24L;
    private static final Long DEF = 8L;
    private static final Long MAX_HP = 100L;
    private static final int SKILL_ROUND = 2;
    private static final int SKILL_TIMES = 4;

    private Random rand = new Random();

    public Theresa (){
        super(NAME, ATK, DEF, MAX_HP, 0, 0.0, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        System.out.println(this.getName() + "发动必杀技");
        for (int i = 0; i < SKILL_TIMES; i++) {
            //造成无视防御的伤害，目标防御力不参与计算
            long magicDamage = rand.nextInt(16) + 1L;
            Damage damage = new Damage(0L, magicDamage);
            Damage finalDamage = target.underAttack(damage);
            afterAttack(target, finalDamage);
        }
    }

    @Override
    public Damage underAttack(Damage damage) {
        damage.setMagicDamage((damage.getMagicDamage() + 1) / 2);
        if (damage.getPhysicDamage() > 0) {
            this.setHp(this.getHp() - damage.getPhysicDamage());
        } else {
            damage.setPhysicDamage(0L);
        }
        if (damage.getMagicDamage() > 0) {
            this.setHp(this.getHp() - damage.getMagicDamage());
        } else {
            damage.setMagicDamage(0L);
        }
        return damage;
    }

    @Override
    public void underDebuff() {
        Debuff debuff = this.getDebuff();
        debuff.setRound(debuff.getRound() - 1);
        String name = NAME;
        Damage debuffDamage = new Damage();
        debuffDamage.setMagicDamage(debuff.getDamage().getMagicDamage());
        debuffDamage.setPhysicDamage(debuff.getDamage().getPhysicDamage());
        // System.out.println(debuffDamage + ", " +debuff.getDamage());
        underAttack(debuffDamage);
        if (debuff.getDamage().getMagicDamage() > 0) {
            System.out.println(name + "由于Debuff，受到" + (debuff.getDamage().getMagicDamage()+ 1) / 2 + "点元素伤害" +
                    "，Debuff还剩" + debuff.getRound() + "回合");
        }
        if (debuff.getAtkDownPoint() > 0) {
            debuff.setRound(debuff.getRound() - 1);
            System.out.println(name + "由于Debuff，攻击力降低" + debuff.getAtkDownPoint() + "点" +
                    "，Debuff还剩" + debuff.getRound() + "回合");
        }
    }
}
