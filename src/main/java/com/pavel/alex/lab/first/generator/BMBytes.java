package com.pavel.alex.lab.first.generator;


import java.math.BigInteger;

public class BMBytes extends BM {

    public BMBytes() {
        super();
    }

    @Override
    public long generateNext() {
        T = a.modPow(T,p);
        BigInteger k = (T.multiply(BigInteger.valueOf(256)).divide(p.subtract(BigInteger.valueOf(1)))).subtract(BigInteger.valueOf(1));
        if (k.compareTo(T.multiply(BigInteger.valueOf(256)).divide(p.subtract(BigInteger.valueOf(1))))<0){
            throw new IllegalStateException();
        }
        return Long.valueOf(String.valueOf(k));
    }

    @Override
    public Type getType() {
        return Type.BYTE;
    }

    @Override
    public String getName() {
        return "BMBytes";
    }

}
