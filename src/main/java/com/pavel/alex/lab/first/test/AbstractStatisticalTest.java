package com.pavel.alex.lab.first.test;

import com.pavel.alex.lab.first.generator.Generator;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.math.BigInteger;
import java.util.*;

public abstract class AbstractStatisticalTest implements  StatisticalTest {

    protected int[] data;

    protected double trustLevel;

    protected int l;

    private Generator generator;

    public AbstractStatisticalTest(Generator generator,int sampleLength,double trustLevel,int l) {
        this.trustLevel = trustLevel;
        this.generator=generator;
        this.l = l;
        if(generator.getType().equals(Generator.Type.BYTE)){
            data = new  int[sampleLength];
            for (int i = 0 ; i < sampleLength ; i++)
                data[i] = (int) generator.generateNext();
        }
        if(generator.getType().equals(Generator.Type.BIT)){
            data = new int[sampleLength];
            for (int i = 0 ; i < sampleLength ; i++){
                StringBuilder builder = new StringBuilder();
                for(int j = 0 ; j < 8 ; j++)
                    builder.append(generator.generateNext());
                 int v = new BigInteger(builder.toString(),2).intValue();
                data[i]= v;
            }
        }
    }

    @Override
    public void test() {
        System.out.println("--- Generator : "+generator.getName()+" ---");
        System.out.println("--- Test : "+this.getName()+", уровень доверия: "+this.trustLevel+" ---");
        System.out.println("statistic: "+computeStatistic());
        System.out.println("limit value: "+limitValue(trustLevel));
        System.out.println();
    }

    @Override
    public double limitValue(double trustLevel) {
        double quantile = new NormalDistribution(0,1).inverseCumulativeProbability(1-trustLevel);
        return Math.sqrt(2*l)*quantile+l;
    }

}
