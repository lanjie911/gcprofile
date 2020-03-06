package cn.bj.brook.basegramma;

import java.util.logging.Logger;

public class Logging {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Logging.class.getCanonicalName());
        logger.warning("heihei");
    }
}
