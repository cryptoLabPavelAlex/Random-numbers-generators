package com.pavel.alex.lab.first.test;

import com.pavel.alex.lab.first.Run;
import com.pavel.alex.lab.first.generator.Generator;

import java.util.Map;

import static com.pavel.alex.lab.first.additional.Utils.getByteFrequency;

@Run
public final class StatisticalEquitabilityTest extends AbstractStatisticalTest {


    public StatisticalEquitabilityTest(Generator generator, Integer sampleLength, Double trustLevel) {
        super(generator,sampleLength,trustLevel,255);
    }

    @Override
    public double computeStatistic() {
        Map<Integer,Integer> map = getByteFrequency(data);
        int m = data.length;
        long n = m/256;
        double result = 0;
        for(int j = 0 ; j < 256; j++ ){
            long val = (map.get(j)-n);
            long v = val * val;
            result+=v;
        }
        return result/n;
    }

    @Override
    public String getName() {
        return "Критерий проверки равномерности знаков";
    }

}
