package com.pavel.alex.lab.first.generator;


public class LehmerHigh extends AbstractLehmer {

    @Override
    public long generateNext() {
        curState=(a*curState+c) % m;
//        System.out.println(Long.toBinaryString(curState));
//        System.out.println(Long.toBinaryString(curState>>24));
        return curState>>24;
    }

    @Override
    public String getName() {
        return "LehmerHigh";
    }
}
