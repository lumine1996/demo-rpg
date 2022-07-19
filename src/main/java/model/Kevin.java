package model;

import base.Damage;
import base.Role;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Kevin extends Role {
    public static final String NAME = "凯文";
    private static final Long ATK = 20L;
    private static final Long DEF = 11L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    public Kevin() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void attack(Role target) {
        if (target.getName().equals(this.getName())) {
            super.attack(target);
        }

        super.attack(target);

        // 30%秒杀30%HP以下对手
        if (target.getHp() < 0.3 * target.getMaxHp() && Math.random() < 0.3) {
            target.setHp(0L);
        }
    }

    @Override
    public void skillAttack(Role target) {
        this.setAtk(this.getAtk() + 5);
        Damage damage = new Damage(0L, 25L);
        target.underAttack(this, damage);
    }
}
