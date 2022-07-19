package model;

import base.Damage;
import base.Role;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-13 22:35:29
 */
@Slf4j
public class Fuka extends Role {
    public static final String NAME = "符华";
    private static final Long ATK = 27L;
    private static final Long DEF = 8L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    private int lockRound = -1;
    private boolean hasLocked = false;
    private final Random rand = new Random();

    public Fuka() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }


    @Override
    public void skillAttack(Role target) {
        //造成无视防御的伤害，目标防御力不参与计算
        Long magicDamage = rand.nextInt(21) + 10L;
        Damage damage = new Damage(0L, magicDamage);
        target.underAttack(this, damage);
    }

    @Override
    public Damage underAttack(Role from, Damage damage) {
        // 已经锁血，终生免疫元素伤害
        if (hasLocked) {
            damage.setMagicDamage(0L);
        }

        damage = super.underAttack(from, damage);
        // 锁血触发机制
        if (this.getHp() <= 0 && !hasLocked) {
            lockRound = this.getRound();
            hasLocked = true;
            log.info("符华锁血1点，并免疫元素伤害");
        }

        // 锁血回合免疫所有伤害
        if (lockRound == this.getRound()) {
            this.setHp(1L);
            return new Damage();
        }
        return damage;
    }

}
