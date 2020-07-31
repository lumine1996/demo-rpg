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
    private double skillChance;
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
                double skillChance, int skillRound) {
        this.name = name;
        this.atk = atk;
        this.mgk = 0L;
        this.def = def;
        this.rgs = 0L;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.hit = 1.0;
        this.avd = avd;
        this.skillChance = skillChance;
        this.skillRound = skillRound;

        this.skillFlag = false;
        this.round = 0;
        this.forbiddenRound = 0;
        this.debuff = new Debuff();
    }


    /**
     * 我的回合
     */
    public void myTurn(Role target) {
        round ++;
        if (debuff.getRound() > 0) {
            underDebuff();
        } else {
            debuff = new Debuff();
        }

        if (forbiddenRound > 0) {
            forbiddenRound--;
        } else {
            attack(target);
        }

        if (silentRound > 0) {
            silentRound --;
        }
    }

    public void underDebuff() {
        debuff.setRound(debuff.getRound() - 1);
        if (debuff.getDamage().getMagicDamage() > 0) {
            underAttack(null , debuff.getDamage());
            System.out.println("Debuff还剩" + debuff.getRound() + "回合");
        }
        if (debuff.getAtkDownPoint() > 0) {
            debuff.setRound(debuff.getRound() - 1);
            System.out.println(name + "由于Debuff，攻击力降低" + debuff.getAtkDownPoint() + "点" +
                    "，Debuff还剩" + debuff.getRound() + "回合");
        }
    }

    /**
     * 攻击
     */
    public void attack(Role target) {
        //生成0~1之间的随机浮点数，小于技能概率时则触发技能
        float zeroCheck = 1e-6f;
        Boolean roundFlag = skillRound != 0 && this.round % skillRound == 0;
        Boolean chanceFlag = skillChance > zeroCheck && Math.random() <= skillChance;
        // 是否发动必杀技（一般为第二技能）
        setSkillFlag(silentRound == 0 && (roundFlag || chanceFlag));

        if (forbiddenRound == 0) {
            if (silentRound == 0 && (roundFlag || chanceFlag)) {
                skillFlag = true;
                System.out.println(this.getName() + "发动了必杀技");
                if (Math.random() <= target.getAvd()) {
                    System.out.println(this.getName() + "攻击" + target.getName() +
                            "，但对方闪避了攻击");
                } else {
                    skillAttack(target);
                }
                skillFlag = false;
            } else {
                if (Math.random() <= target.getAvd()) {
                    System.out.println(this.getName() + "攻击" + target.getName() +
                            "，但对方闪避了攻击");
                } else {
                    normalAttack(target);
                }
            }
        }
    }

    /**
     * 普通攻击
     */
    public void normalAttack(Role target) {
        Damage damage = new Damage(atk - debuff.getAtkDownPoint() - target.getDef(), 0L);
        Damage finalDamage = target.underAttack(this, damage);
        afterAttack(target, finalDamage);
    }

    /**
     * 技能攻击
     */
    public void skillAttack(Role target) {
        Damage damage = new Damage(0L, atk * 3L);
        target.underAttack(this, damage);
    }

    /**
     * 攻击后行为
     */
    public void afterAttack(Role target, Damage damage) {
        // 默认攻击后无动作
    }

    /**
     * 受到攻击
     */
    public Damage underAttack(Role from, Damage damage) {
        String fromName = "间接攻击";
        if (from != null) {
            fromName = from.getName();
        }

        if (damage.getPhysicDamage() > 0) {
            hp -= damage.getPhysicDamage();
            System.out.println(fromName + "对" + this.getName() + "造成了" + damage.getPhysicDamage() + "点伤害");
        } else {
            damage.setPhysicDamage(0L);
        }
        if (damage.getMagicDamage() > 0) {
            hp -= damage.getMagicDamage();
            System.out.println(fromName + "对" + this.getName() + "造成了" + damage.getMagicDamage() + "点元素伤害");
        } else {
            damage.setMagicDamage(0L);
        }
        return damage;

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

    public int getSilentRound() {
        return silentRound;
    }

    public void setSilentRound(int silentRound) {
        this.silentRound = silentRound;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public void setDebuff(Debuff debuff) {
        this.debuff = debuff;
    }

    public Long getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Long maxHp) {
        this.maxHp = maxHp;
    }

    public double getHit() {
        return hit;
    }

    public void setHit(double hit) {
        this.hit = hit;
    }

    public double getAvd() {
        return avd;
    }

    public void setAvd(double avd) {
        this.avd = avd;
    }

    public double getSkillChance() {
        return skillChance;
    }

    public void setSkillChance(double skillChance) {
        this.skillChance = skillChance;
    }

    public int getSkillRound() {
        return skillRound;
    }

    public void setSkillRound(int skillRound) {
        this.skillRound = skillRound;
    }
}
