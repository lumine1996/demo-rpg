package model;

import base.Role;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VillV extends Role {
    public static final String NAME = "维尔薇";
    private static final Long ATK = 20L;
    private static final Long DEF = 12L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    private boolean healed = false;

    private final Random rand = new Random();

    public VillV() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        // 先进行普通攻击
        super.normalAttack(target);
        // 混乱1回合
        target.setMessRound(1);
    }

    @Override
    public void myTurn(Role target) {
        // 治疗技能仅触发一次
        if (this.getHp() < 31 && !healed) {
            this.setAtk(this.getAtk() + rand.nextInt(9) + 2);
            this.setHp(this.getHp() + rand.nextInt(11) + 10);
            target.setHp(target.getHp() + rand.nextInt(11) + 10);
            this.healed = true;
        }
        super.myTurn(target);
    }
}
