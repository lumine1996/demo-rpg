package model;

import base.Damage;
import base.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mobius extends Role {
    public static final String NAME = "梅比乌斯";
    private static final Long ATK = 21L;
    private static final Long DEF = 11L;
    private static final Long MAX_HP = 100L;
    private static final double AVD = 0;
    private static final double SKILL_CHANCE = 0;
    private static final int SKILL_ROUND = 3;

    public Mobius() {
        super(NAME, ATK, DEF, MAX_HP, AVD, SKILL_CHANCE, SKILL_ROUND);
    }

    @Override
    public void normalAttack(Role target) {
        super.normalAttack(target);

        // 仅普攻概率触发
        if (Math.random() < 0.33) {
            if (target.getDef() > 3) {
                // 每次-3防御, 最低下降至0
                target.setDef(target.getDef() - 3);
            } else {
                target.setDef(0L);
            }
        }
    }

    @Override
    public void skillAttack(Role target) {
        // 造成33点伤害
        Long hurt = 33L - target.getDef();
        Damage damage = new Damage(hurt, 0L);
        target.underAttack(this, damage);

        // 33%概率昏迷对方一回合
        if (Math.random() < 0.33) {
            log.info("{}陷入了昏迷", target.getName());
            target.setForbiddenRound(1);
        }
    }
}
