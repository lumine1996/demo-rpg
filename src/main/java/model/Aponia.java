package model;

import base.Damage;
import base.Role;


import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm
 */
@Slf4j
public class Aponia extends Role {
    public static final String NAME = "阿波尼亚";
    private static final Long ATK = 21L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 4;

    public Aponia() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        Damage damage = new Damage((long) (this.getAtk() * 1.7), 0L);
        target.underAttack(this, damage);
        target.setForbiddenRound(1);
    }
}
