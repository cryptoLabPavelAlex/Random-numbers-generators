package com.pavel.alex.lab.first.test;

import com.pavel.alex.lab.first.generator.Generator;

import java.util.List;
import java.util.Map;

import static com.pavel.alex.lab.first.additional.Utils.*;


public final class StatisticalSignIndependencyTest extends AbstractStatisticalTest {

    public StatisticalSignIndependencyTest(Generator generator, int sampleLength, double trustLevel) {
        super(generator,sampleLength,trustLevel,255*255);
    }

    @Override
    public double computeStatistic() {
        double result = 0;
        int n = data.length/2;
        List<Map.Entry<Integer,Integer>> pairs = getPairs(data);
        Map<Map.Entry<Integer,Integer>,Integer> pairsCount = getPairsCount(pairs);
        Map<Integer,Integer> firstPlaceCount = getCountElementFirstPlace(pairs);
        Map<Integer,Integer> secondPlaceCount = getCountElementSecondPlace(pairs);

        for (int i=0 ; i < 256 ; i++){
            for (int j=0 ; j < 256 ; j++){
                Integer ijValueCount = getPairCountFromMap(i,j,pairsCount);
                if(ijValueCount!=null){
                    int v = firstPlaceCount.get(i);
                    int a = secondPlaceCount.get(j);
                    double ijSquared = ijValueCount * ijValueCount;
                    double res = ijSquared / (v * a);
                    result+= res;
                }
            }
        }
        return n * (result - 1);
    }

    @Override
    public String getName() {
        return "Критерий проверки независимости знаков";
    }
}
