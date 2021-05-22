package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char ch = 'K';
        int i = 33;
        long l = 123456789090L;
        byte b = 127;
        short s = 9876;
        double d = 0.7;
        float f = 987.654F;
        boolean bl = true;

        LOG.debug("Variables char : {}, int : {}, long : {}, byte : {}, short : {}, double : {}, float : {}, boolean : {}, ", ch, i, l, b, s, d, f, bl);
    }
}