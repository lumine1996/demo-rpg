package model;

import base.Damage;
import base.Role;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-12 22:14:32
 */
@Slf4j
public class Bronya extends Role {
    public static final String NAME = "布洛妮娅";
    private static final Long ATK = 26L;
    private static final Long DEF = 8L;
    private static final Long HP = 100L;
    private static final double AVD = 0.15;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;
    private final Random rand = new Random();

    public Bronya() {
        super(NAME, ATK, DEF, HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        //造成1~100点伤害
        Long hurt = rand.nextInt(100) + 1L;
        Damage damage = new Damage(hurt, 0L);
        target.underAttack(this, damage);
    }
}
