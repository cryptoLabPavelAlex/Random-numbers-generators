package com.pavel.alex.lab.first.generator;

import java.math.BigInteger;
import java.util.Random;


public class BBS implements Generator {

    protected BigInteger p;

    protected BigInteger q;

    protected BigInteger n;

    protected BigInteger r;

    public BBS() {
        Random random = new Random();
        String pHex = "CD5BBB96D30086EC484EBA3D7F9CAEB07";
        String qHex = "425D2B9BFDB25B9CF6C416CC6E37B59C1F";
        p = new BigInteger(pHex, 16);
        q = new BigInteger(qHex, 16);
        n = p.multiply(q);
        r = new BigInteger(String.valueOf(random.nextLong()));
    }

    @Override
    public long generateNext() {
        r = r.shiftLeft(1).mod(n);
        return r.mod(BigInteger.valueOf(2)).longValue();
    }

    @Override
    public Type getType() {
        return Type.BIT;
    }

    @Override
    public String getName() {
        return "BBS";
    }
}
