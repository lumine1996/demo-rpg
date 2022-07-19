package model;

import base.Damage;
import base.Debuff;
import base.Role;
import lombok.EqualsAndHashCode;

import java.util.Random;

@EqualsAndHashCode(callSuper = true)
public class Elysia extends Role {
    public static final String NAME = "爱莉希雅";
    private static final Long ATK = 21L;
    private static final Long DEF = 8L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 2;

    private final Random rand = new Random();

    public Elysia() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void attack(Role target) {
        super.attack(target);

        // 每次攻击概率触发
        if (Math.random() < 0.35) {
            // 造成11点元素伤害
            Damage damage = new Damage(0L, 11L);
            target.underAttack(this, damage);
        }
    }

    @Override
    public void skillAttack(Role target) {
        // 造成25~50点伤害
        Long hurt = rand.nextInt(26) + 25L - target.getDef();
        Damage damage = new Damage(hurt, 0L);
        target.underAttack(this, damage);

        // 下次攻击力降低6点
        Debuff debuff = new Debuff(1, 6L);
        target.setDebuff(debuff);
    }
}
