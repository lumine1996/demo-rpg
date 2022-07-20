package model;

import base.Damage;
import base.Role;
import lombok.EqualsAndHashCode;

import java.util.Random;

@EqualsAndHashCode(callSuper = true)
public class Sakura2022 extends Role {
    public static final String NAME = "樱";
    private static final Long ATK = 24L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0.15;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 2;

    private final Random rand = new Random();


    public Sakura2022() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        cure(rand.nextInt(5) + 1L);
        Damage damage = new Damage((long) (getAtk() * 1.3 - target.getDef()), 0L);
        target.underAttack(this, damage);
    }
}
