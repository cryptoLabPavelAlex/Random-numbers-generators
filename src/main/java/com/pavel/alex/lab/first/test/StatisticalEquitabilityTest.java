package com.pavel.alex.lab.first.test;

import com.pavel.alex.lab.first.generator.Generator;

import java.util.Map;

import static com.pavel.alex.lab.first.additional.Utils.getByteFrequency;


public final class StatisticalEquitabilityTest extends AbstractStatisticalTest {


    public StatisticalEquitabilityTest(Generator generator, int sampleLength, double trustLevel) {
        super(generator,sampleLength,trustLevel,255);
    }

    @Override
    public double computeStatistic() {
        Map<Integer,Integer> map = getByteFrequency(data);
        int m = data.length;
        double n = m/256;
        double result = 0;
        for(int j = 0 ; j < 256; j++ ){
            result+= Math.pow((map.get(j)-n),2)/n;
        }
        return result;
    }

    @Override
    public String getName() {
        return "Критерий проверки равномерности знаков";
    }

}
