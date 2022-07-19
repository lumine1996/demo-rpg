package app;

import base.Role;
import lombok.extern.slf4j.Slf4j;
import model.*;

/**
 * @author cgm
 */

@Slf4j
public class Main {

    public static void main(String[] args) {
        //前者胜利次数
        int formerWin = 0;
        //模拟次数
        int times = 100000;
        String formerName = "";
        String latterName = "";
        for (int i = 0; i < times; i++) {
            // 先手
            Role former = new Aponia();
            // 后手
            Role latter = new Pardofelis();
            if (i == 0) {
                formerName = former.getName();
                latterName = latter.getName();
            }
            if (vs(former, latter) == 1) {
                formerWin++;
            }
        }
        log.info(formerName + "（先手）胜利" + formerWin + "次");
        log.info(latterName + "（后手）胜利" + (times - formerWin) + "次");
    }

    private static int vs(Role former, Role latter) {
        int currentRole = 1;
        int currentRound = 1;
        former.startBattle(latter);
        latter.startBattle(former);
        while (former.getHp() > 0 && latter.getHp() > 0) {
            if (currentRole == 1) {
                log.info("【第" + currentRound + "回合】");
                former.myTurn(latter);
                currentRole = 2;
            } else {
                latter.myTurn(former);
                currentRole = 1;
                currentRound++;
            }
        }
        if (former.getHp() > 0) {
            log.info("【" + former.getName() + "胜利-------------------】\n");
            return 1;
        } else {
            log.info("【" + latter.getName() + "胜利-------------------】\n");
            return 2;
        }
    }
}
