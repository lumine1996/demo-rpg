package model;

import base.Damage;
import base.Debuff;
import base.Role;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@EqualsAndHashCode(callSuper = true)
public class Fuka2022 extends Role {
    public static final String NAME = "符华";
    private static final Long ATK = 21L;
    private static final Long DEF = 12L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 2;

    private final Random rand = new Random();

    private Long extraMagic = 0L;

    public Fuka2022() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void myTurn(Role target) {
        // 清除防御力buff
        if (this.getDef() > DEF) {
            this.setDef(DEF);
        }
        super.myTurn(target);
    }

    @Override
    public void normalAttack(Role target) {
        super.normalAttack(target);

        // 拥有额外元素伤害
        if (this.extraMagic > 0) {
            Damage damage = new Damage(0L, extraMagic);
            target.underAttack(this, damage);
            extraMagic = 0L;
        }
    }

    @Override
    public void skillAttack(Role target) {
        // 防御提升3点
        setDef(getDef() + 3);
        // 下次附带10~33点元素伤害
        this.extraMagic = rand.nextInt(24) + 10L;
        log.info("符华下次会额外造成元素伤害");

        // 因为本回合不攻击, 攻击力Debuff顺延
        if (ATK - this.getAtk() > 0) {
            this.setDebuff(new Debuff(1, ATK - this.getAtk()));
        }
    }

    @Override
    public Damage underAttack(Role from, Damage damage) {
        // 受到的所有伤害降低20%
        long physicMinus = (long) (damage.getPhysicDamage() * 0.2);
        long magicMinus = (long) (damage.getMagicDamage() * 0.2);
        damage.setPhysicDamage(damage.getPhysicDamage() - physicMinus);
        damage.setMagicDamage(damage.getMagicDamage() - magicMinus);
        return super.underAttack(from, damage);
    }
}
