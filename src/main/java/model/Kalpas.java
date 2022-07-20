package model;

import base.Damage;
import base.Role;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;


@Slf4j
@EqualsAndHashCode(callSuper = true)
public class Kalpas extends Role {
    public static final String NAME = "千劫";
    private static final Long ATK = 23L;
    private static final Long DEF = 9L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    private final Random rand = new Random();

    public Kalpas() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        if (getHp() <= 10) {
            super.skillAttack(target);
            return;
        }
        this.underAttack(this, new Damage(10L, 0L));
        Long physicHurt = 45L - target.getDef();
        Long magicDamage = rand.nextInt(20) + 1L;
        Damage damage = new Damage(physicHurt, magicDamage);
        target.underAttack(this, damage);
        log.info("千劫休息一回合");
        this.setForbiddenRound(1);
    }

    @Override
    public void myTurn(Role target) {
        setAtk(ATK + (MAX_HP - getHp()) / 5L);
        super.myTurn(target);
    }
}
