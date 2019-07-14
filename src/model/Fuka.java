package model;

import base.Damage;
import base.Role;

import java.util.Random;

/**
 * @author 675056544@qq.com 2019-07-13 22:35:29
 */
public class Fuka extends Role{
    public static final String NAME = "符华";
    private static final Long ATK = 27L;
    private static final Long DEF = 8L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0.0;
    private static final double SKILL_RATE = 0.0;
    private static final int SKILL_ROUND = 3;
    private int lockRound = -1;
    private Boolean hasLocked = false;
    private Random rand = new Random();

    public Fuka() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_RATE, SKILL_ROUND);
    }


    @Override
    public void skillAttack(Role target) {
        //造成无视防御的伤害，目标防御力不参与计算
        long magicDamage = rand.nextInt(21) + 10L;
        Damage damage = new Damage(0L, magicDamage);
        target.underAttack(damage);
        System.out.println(this.getName() + "发动技能，对" + target.getName() + "造成了" + damage.getMagicDamage() + "点元素伤害");
    }

    @Override
    public void underAttack(Damage damage) {
        super.underAttack(damage);
        if (this.getHp() <= 0 && !hasLocked) {
            lockRound = this.getRound();
            hasLocked = true;
            System.out.println("符华锁血1点");
        }
        if (lockRound == this.getRound()) {
            this.setHp(1L);
        }
    }

}
