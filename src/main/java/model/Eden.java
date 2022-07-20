package model;

import base.Role;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode(callSuper = true)
public class Eden extends Role {
    public static final String NAME = "伊甸";
    private static final Long ATK = 16L;
    private static final Long DEF = 12L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 2;

    private boolean twice = false;

    public Eden() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        super.normalAttack(target);
        if (Math.random() < 0.5 && !twice) {
            log.info("伊甸额外攻击一次");
            super.normalAttack(target);
            twice = true;
        }
    }

    @Override
    public void myTurn(Role target) {
        twice = false;
        super.myTurn(target);
    }

    @Override
    public void skillAttack(Role target) {
        setAtk(getAtk() + 4L);
        log.info("伊甸当前攻击力: {}", getAtk());
        this.normalAttack(target);

        // 额外回合
        log.info("【第" + (getRound() + 1) + "回合】, 伊甸先手");
        super.myTurn(target);
        target.myTurn(this);
    }
}
