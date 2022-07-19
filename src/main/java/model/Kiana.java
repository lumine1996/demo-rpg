package model;

import base.Role;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cgm 2019-07-12 17:52:11
 */
@Slf4j
public class Kiana extends Role {
    public Kiana () {
        super("Kiana", 24L, 11L, 100L, 0.30, 0, 0);
    }
}
