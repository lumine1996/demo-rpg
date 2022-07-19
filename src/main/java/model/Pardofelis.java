package model;

import base.Damage;
import base.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pardofelis extends Role {
    public static final String NAME = "帕朵";
    private static final Long ATK = 17L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    public Pardofelis() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void attack(Role target) {
        super.attack(target);
        if (Math.random() < 0.3) {
            log.info("罐头出击!");
            Long hurt = 30 - target.getDef();
            Damage damage = new Damage(hurt, 0L);
            target.underAttack(this, damage);
        }
    }

    @Override
    public void skillAttack(Role target) {
        Long hurt = 30 - target.getDef();
        Damage damage = new Damage(hurt, 0L);
        target.underAttack(this, damage);
        super.cure(hurt);
    }
}
