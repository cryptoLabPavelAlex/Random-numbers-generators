package com.pavel.alex.lab.first.generator;


import com.pavel.alex.lab.first.Run;
import java.util.Random;
//@Run
public class GeffeGen implements Generator {

    private Generator l1;
    private Generator l2;
    private Generator l3;

    public GeffeGen() {
        Random r = new Random();
        long l1Px = 0b1000000000000000000000001010011l;
        long l2Px = 0b10000000000000000000000000001001l;
        long l3Px = 0b100000000000000000000000010101111l;
        l1 = new LFSR(l1Px,r.nextLong());
        l2 = new LFSR(l2Px,r.nextLong());
        l3 = new LFSR(l3Px,r.nextLong());
    }

    public GeffeGen(Generator l1, Generator l2, Generator l3) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
    }

    @Override
    public long generateNext() {
        long x = l1.generateNext();
        long y = l2.generateNext();
        long s = l3.generateNext();
        return (s & x) ^ (s ^ 1) & y ;
    }

    @Override
    public Type getType() {
        return Type.BIT;
    }

    @Override
    public String getName() {
        return "GeffeGen";
    }
}
