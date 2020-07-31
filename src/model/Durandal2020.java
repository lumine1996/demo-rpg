package model;

import base.Role;

public class Durandal2020 extends Role {
    public static final String NAME = "幽兰黛尔";
    private static final Long ATK = 19L;
    private static final Long DEF = 10L;
    private static final Long MAX_HP = 100L;

    public Durandal2020() {
        super(NAME, ATK, DEF, MAX_HP, 0.0, 0.0, 0);
    }

    @Override
    public void myTurn(Role role) {
        System.out.println("幽兰黛尔的攻击力增加了");
        setAtk(getAtk() + 3);
        //调用父类攻击前行为
        super.myTurn(role);
    }
}
