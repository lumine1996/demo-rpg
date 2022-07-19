package model;

import base.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Himeko2020 extends Role{

    public static final String NAME = "姬子";
    private static final Long ATK = 23L;
    private static final Long DEF = 9L;
    private static final Long HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 2;

    public Himeko2020() {
        super(NAME, ATK, DEF, HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void startBattle(Role target) {
        if (target instanceof Kiana2020 || target instanceof Sakura2020 || target instanceof Theresa2020
                || target instanceof Rozaliya2020 || target instanceof Durandal2020) {
            log.info(NAME + "伤害提高25%");
        }
    }

    @Override
    public void skillAttack(Role target) {
        log.info(this.getName() + "对" + target.getName() + "使用了干杯，朋友");
        setAtk(getAtk() * 2);
        setHit(getHit() - 0.35);
        super.normalAttack(target);
    }
}
