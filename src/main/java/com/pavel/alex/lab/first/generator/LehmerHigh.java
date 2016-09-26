package com.pavel.alex.lab.first.generator;


public class LehmerHigh extends AbstractLehmer {
    @Override
    public long generateNext() {
        curState=(a*curState+c) % m;
        long next = curState>>55;
        return next;
    }

    @Override
    public String getName() {
        return "LehmerHigh";
    }
}
