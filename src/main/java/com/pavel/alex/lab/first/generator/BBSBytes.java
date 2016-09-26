package com.pavel.alex.lab.first.generator;

import java.math.BigInteger;

public class BBSBytes extends BBS {

    @Override
    public long generateNext() {
        r = r.shiftLeft(1).mod(n);
        return r.mod(BigInteger.valueOf(256)).longValue();
    }

    @Override
    public Type getType() {
        return Type.BYTE;
    }

    @Override
    public String getName() {
        return "BBSBytes";
    }

}
