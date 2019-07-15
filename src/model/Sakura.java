package model;

import base.Damage;
import base.Debuff;
import base.Role;

/**
 * @author guangming.chen@hand-china.com 2019-07-14 16:15:23
 */
public class Sakura extends Role {
    public static final String NAME = "八重樱";
    private static final Long ATK = 28L;
    private static final Long DEF = 7L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0.0;
    private static final double SKILL_RATE = 0.25;
    private static final int SKILL_ROUND = 0;

    private static final int BURN_ROUND = 3;
    private static final Long BURN_DAMAGE = 5L;
    private static final double BURN_RATE = 0.20;

    public Sakura() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_RATE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        super.normalAttack(target);
        if (Math.random() <= BURN_RATE) {
            Damage burnDamage = new Damage(0L, BURN_DAMAGE);
            Debuff debuff = new Debuff(BURN_ROUND, burnDamage);
            target.setDebuff(debuff);
            System.out.println(this.getName() + "使" + target.getName() + "点燃3回合");
        }
    }

    @Override
    public void skillAttack(Role target) {
        Damage damage = new Damage((ATK - target.getDef()) * 2, 0L);
        target.underAttack(this.getName(), damage);
    }
}
