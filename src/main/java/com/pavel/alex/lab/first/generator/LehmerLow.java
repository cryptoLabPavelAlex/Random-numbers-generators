package com.pavel.alex.lab.first.generator;


public class LehmerLow extends AbstractLehmer {
    @Override
    public long generateNext() {
        curState=(a*curState+c) % m;
//        System.out.println(Long.toBinaryString(curState));
//        System.out.println(Long.toBinaryString(curState&0b11111111));
        return curState & 0b11111111;
    }

    @Override
    public String getName() {
        return "LehmerLow";
    }
}
