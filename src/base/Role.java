package base;
/**
 * @author 675056544@qq.com 2019-07-12 17:46:30
 */
public class Role {
    /**
     * 名字
     */
    private String name;
    /**
     * 物理攻击力
     */
    private Long atk;
    /**
     * 魔法攻击力
     */
    private Long mgk;
    /**
     * 物理防御
     */
    private Long def;
    /**
     * 魔法防御
     */
    private Long rgs;
    /**
     * 生命值
     */
    private Long hp;
    /**
     * 最大生命值
     */
    private Long maxHp;
    /**
     * 命中率
     */
    private double hit;
    /**
     * 闪避率
     */
    private double avd;
    /**
     * 技能触发概率
     */
    private double skillProbability;
    /**
     * 技能触发标志
     */
    private Boolean skillFlag;
    /**
     * 技能触发回合
     */
    private int skillRound;
    /**
     * 当前回合
     */
    private int round;
    /**
     * 剩余沉默回合
     */
    private int silentRound;
    /**
     * 剩余无法攻击回合
     */
    private int forbiddenRound;
    /**
     * 负面状态
     */
    private Debuff debuff;

    public Role(String name, Long atk, Long def, Long maxHp, double avd,
                double skillProbability, int skillRound) {
        this.name = name;
        this.atk = atk;
        this.mgk = 0L;
        this.def = def;
        this.rgs = 0L;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.hit = 1.0;
        this.avd = avd;
        this.skillProbability = skillProbability;
        this.skillRound = skillRound;

        this.skillFlag = false;
        this.round = 0;
        this.forbiddenRound = 0;
        this.debuff = new Debuff();
    }


    /**
     * 攻击前行为
     */
    public void beforeAttack(Role target) {
        if (debuff.getRound() > 0) {
            underAttack(debuff.getDamage());
            debuff.setRound(debuff.getRound() - 1);
        }

        round ++;
        if (forbiddenRound > 0) {
            forbiddenRound--;
        } else {
            attack(target);
        }

        if (silentRound > 0) {
            silentRound --;
        }
    }

    /**
     * 攻击
     */
    public void attack(Role target) {
        //生成0~1之间的随机浮点数，小于技能概率时则触发技能
        Boolean roundFlag = skillRound != 0 && this.round % skillRound == 0;
        Boolean probabilityFlag = skillProbability != 0.0 && Math.random() <= skillProbability;
        skillFlag = silentRound == 0 && (roundFlag || probabilityFlag);

        if (forbiddenRound == 0) {
            if (skillFlag) {
                skillAttack(target);
                skillFlag = false;
            } else {
                normalAttack(target);
            }
        }

    }

    /**
     * 普通攻击
     */
    public void normalAttack(Role target) {
        Damage damage = new Damage(atk - target.getDef(), 0L);
        target.underAttack(damage);
        afterAttack(target, damage);
    }

    /**
     * 技能攻击
     */
    public void skillAttack(Role target) {
        Damage damage = new Damage(0L, atk * 3L);
        target.underAttack(damage);
    }

    /**
     * 攻击后行为
     */
    public void afterAttack(Role target, Damage damage) {
        System.out.println(this.getName() + "对" + target.getName() + "造成了" + damage.getPhysicalDamage() + "点伤害");
    }

    /**
     * 受到攻击
     */
    public void underAttack(Damage damage) {
        if (damage.getPhysicalDamage() > 0) {
            hp -= damage.getPhysicalDamage();
        } else {
            damage.setPhysicalDamage(0L);
        }
        if (damage.getMagicDamage() > 0) {
            hp -= damage.getMagicDamage();
        } else {
            damage.setMagicDamage(0L);
        }

    }

    /**
     * 治疗
     */
    public void cure(Long curePoint) {
        if (this.hp + curePoint > 100L) {
            curePoint = 100L - this.hp;
        }
        this.hp += curePoint;
        if (curePoint > 0) {
            System.out.println(this.name + "获得了" + curePoint + "点治疗");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAtk() {
        return atk;
    }

    public void setAtk(Long atk) {
        this.atk = atk;
    }

    public Long getMgk() {
        return mgk;
    }

    public void setMgk(Long mgk) {
        this.mgk = mgk;
    }

    public Long getDef() {
        return def;
    }

    public void setDef(Long def) {
        this.def = def;
    }

    public Long getRgs() {
        return rgs;
    }

    public void setRgs(Long rgs) {
        this.rgs = rgs;
    }

    public Long getHp() {
        return hp;
    }

    public void setHp(Long hp) {
        this.hp = hp;
    }

    public Boolean getSkillFlag() {
        return skillFlag;
    }

    public void setSkillFlag(Boolean skillFlag) {
        this.skillFlag = skillFlag;
    }

    public int getForbiddenRound() {
        return forbiddenRound;
    }

    public void setForbiddenRound(int forbiddenRound) {
        this.forbiddenRound = forbiddenRound;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
