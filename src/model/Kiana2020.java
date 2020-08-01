package model;

import base.Damage;
import base.Role;

/**
 * @author 675056544@qq.com 2020-07-31
 */
public class Kiana2020  extends Role {

    public static final String NAME = "琪亚娜";
    private static final Long ATK = 24L;
    private static final Long DEF = 11L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 2;

    // 眩晕自己的概率
    private static final double SILENT_CHANCE = 0.35;

    public Kiana2020() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void skillAttack(Role target) {
        // 追加造成2倍对方防御力的伤害，去掉对方防御力，即造成自身攻击力加对方防御力的伤害
        Damage damage = new Damage(getAtk() + target.getDef(), 0L);
        target.underAttack(this, damage);

        // 35%概率下回合无法攻击
        if (Math.random() <= SILENT_CHANCE) {
            setForbiddenRound(1);
            System.out.println("琪亚娜眩晕一回合");
        }
    }
}
