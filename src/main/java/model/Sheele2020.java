package model;

import base.Role;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;
/**
 * @author cgm 2020-07-30
 */

@Slf4j
public class Sheele2020 extends Role {
    public static final String NAME = "希儿";
    private static final Long ATK = 23L;
    private static final Long DEF = 13L;
    private static final Long MAX_HP = 100L;
    private static final int SKILL_ROUND = 2;

    private final Random rand = new Random();

    public Sheele2020 (){
        super(NAME, ATK, DEF, MAX_HP, 0, 0, SKILL_ROUND);
    }

    /**
     * 普通攻击设置为黑希
     * @param target 攻击目标
     */
    @Override
    public void normalAttack(Role target) {
        log.info("拥抱你的力量吧，希儿");
        setAtk(getAtk() + 10L);
        setDef(getDef() - 5L);
        super.normalAttack(target);
    }

    /**
     * 技能攻击设置为白希，2回合一次
     * @param target 攻击目标
     */
    @Override
    public void skillAttack(Role target) {
        log.info("好，好的");
        Long curePoint = rand.nextInt(15) + 1L;
        cure(curePoint);

        setAtk(getAtk() - 10L);
        setDef(getDef() + 5L);
        super.normalAttack(target);
    }
}
