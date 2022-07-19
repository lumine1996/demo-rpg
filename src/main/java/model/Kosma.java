package model;

import base.Damage;
import base.Debuff;
import base.Role;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Kosma extends Role {
    public static final String NAME = "科斯魔";
    private static final Long ATK = 19L;
    private static final Long DEF = 11L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 2;

    private final Random rand = new Random();

    public Kosma() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void attack(Role target) {
        super.attack(target);
        if (Math.random() < 0.15) {
            target.setDebuff(new Debuff(3, new Damage(0L, 4L)));
        }
    }

    @Override
    public void skillAttack(Role target) {
        long magicDamage = target.getDebuff().getRound() > 0 ? 3L : 0L;
        for (int i = 0; i < 4; i++) {
            long physicDamage = rand.nextInt(12) + 11L;
            long finalDamage = physicDamage > target.getDef() ? physicDamage - target.getDef() : 0L;
            target.underAttack(this, new Damage(finalDamage, magicDamage));
        }
    }
}
