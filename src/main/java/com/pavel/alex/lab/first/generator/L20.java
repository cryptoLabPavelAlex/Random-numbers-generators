package com.pavel.alex.lab.first.generator;

import com.pavel.alex.lab.first.Run;
import java.util.Random;

//@Run
public class L20 implements Generator {

    public Generator lfsr;

    public L20() {
        Random random = new Random();
        long l1Px = 0b00010100010000000001L;
        long l1Px2 = 0b100000000001000101000L;
        long l1Px3 = 0b00101000100000000001L;
        long randomInitState = random.nextLong() % ((2<<19) -1);
        lfsr = new Generator.LFSR(l1Px2,randomInitState);
    }

    @Override
    public long generateNext() {
        return  lfsr.generateNext();
    }

    @Override
    public Type getType() {
        return lfsr.getType();
    }

    @Override
    public String getName() {
        return "L20";
    }
}
