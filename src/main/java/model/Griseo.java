package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Griseo extends Role {
    public static final String NAME = "格蕾修";
    private static final Long ATK = 16L;
    private static final Long DEF = 11L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    private final Random rand = new Random();

    private Long barrierHp = 0L;

    private Long defBuff = 0L;

    public Griseo() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void myTurn(Role target) {
        if (Math.random() < 0.4 && defBuff < 10) {
            this.setDef(this.getDef() + 2);
            defBuff += 2;
            log.info(NAME + "防御累计提升" + defBuff);
        }
        super.myTurn(target);
    }

    @Override
    public void skillAttack(Role target) {
        if (this.barrierHp > 0) {
            Damage damage = new Damage(this.getDef() - target.getDef(), 0L);
            target.underAttack(this, damage);
        }
        this.barrierHp = 15L;
    }

    @Override
    public Damage underAttack(Role from, Damage damage) {
        if (this.barrierHp <= 0) {
            return super.underAttack(from, damage);
        }

        if (damage.getPhysicDamage() < this.barrierHp) {
            this.barrierHp -= damage.getPhysicDamage();
            damage.setPhysicDamage(0L);
        } else {
            damage.setPhysicDamage(damage.getMagicDamage() - this.barrierHp);
            this.barrierHp = 0L;
            Damage broken = new Damage(this.getDef() * (rand.nextInt(3) + 2) - from.getDef(),
                    0L);
            from.underAttack(this, broken);
        }
        return super.underAttack(from, damage);
    }
}
