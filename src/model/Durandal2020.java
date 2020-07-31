package model;

import base.Damage;
import base.Role;

public class Durandal2020 extends Role {
    public static final String NAME = "幽兰黛尔";
    private static final Long ATK = 19L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 0;

    // 反击必杀技的概率
    private static final double BACK_CHANCE = 0.16;
    // 反击伤害
    private static final Long BACK_DAMAGE = 30L;

    public Durandal2020() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void myTurn(Role role) {
        System.out.println("幽兰黛尔的攻击力增加了");
        setAtk(getAtk() + 3);
        //调用父类攻击前行为
        super.myTurn(role);
    }

    @Override
    public Damage underAttack(Role from, Damage damage) {
        boolean skillFlag = from.getSkillFlag();
        if (skillFlag && Math.random() <= BACK_CHANCE) {
            // 如果对方释放了必杀技，概率反击，并免疫对方伤害
            System.out.println("幽兰黛尔发动反击");
            Damage backDamage = new Damage(BACK_DAMAGE - from.getDef(), 0L);
            from.underAttack(this, backDamage);
            return new Damage();
        }
        return super.underAttack(from, damage);
    }
}
