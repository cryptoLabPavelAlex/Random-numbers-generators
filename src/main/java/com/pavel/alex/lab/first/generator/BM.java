package com.pavel.alex.lab.first.generator;

import java.math.BigInteger;
import java.util.Random;

public class BM implements Generator {

    protected BigInteger p;

    protected BigInteger a;

    protected BigInteger T;

    public BM() {
        String pHex = "CEA42B987C44FA642D80AD9F51F10457690DEF10C83D0BC1BCEE12FC3B6093E3";
        String aHex = "5B88C41246790891C095E2878880342E88C79974303BD0400B090FE38A688356";
        p = new BigInteger(pHex, 16);
        a = new BigInteger(aHex, 16);
        T = nextRandomBigInteger(p);
    }


    @Override
    public long generateNext() {
        T = a.modPow(T,p);
        return (T.compareTo(p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)))==-1) ? 1 : 0;
    }

    @Override
    public Type getType() {
        return Type.BIT;
    }

    protected BigInteger nextRandomBigInteger(BigInteger n)  {
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while( result.compareTo(n) >= 0 ) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }

    @Override
    public String getName() {
        return "BM";
    }


}
