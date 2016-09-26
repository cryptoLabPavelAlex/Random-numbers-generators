package com.pavel.alex.lab.first.generator;

import java.util.Random;


public class EricWolfram implements Generator {

    private long r;

    private long firstState;

    public EricWolfram() {
        Random random = new Random();
        firstState = random.nextLong();
        r = firstState;
    }

    @Override
    public long generateNext() {
        r = Long.rotateLeft(r,1) ^ (r^Long.rotateRight(r,1));
        return r % 2;
    }

    @Override
    public Type getType() {
        return Type.BIT;
    }

    @Override
    public String getName() {
        return "EricWolfram";
    }
}
