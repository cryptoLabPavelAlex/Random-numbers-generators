package com.pavel.alex.lab.first.test;

import com.pavel.alex.lab.first.generator.Generator;


import java.util.Map;

import static com.pavel.alex.lab.first.additional.Utils.getLineSegmentFrequencies;
import static com.pavel.alex.lab.first.additional.Utils.getSumElem;

public final class StatisticalUniformityTest extends AbstractStatisticalTest {

    private int r;

    private int n;

    private int m1;

    private int m;

    public StatisticalUniformityTest(Generator generator, int sampleLength, double trustLevel,int r) {
        super(generator, sampleLength, trustLevel, 255*(r-1));
        m = data.length;
        m1 = m/r;
        n = m1*r;
        this.r = r;
    }

    @Override
    public double computeStatistic() {
        Map<Integer,int[]> lineValsFrequency = getLineSegmentFrequencies(data,r);
        Map<Integer,Integer> elemInWholeLines = getSumElem(lineValsFrequency);
        int m1 = data.length/r;
        int n = m1 * r;
        double res = 0d;
        for (int i = 0 ; i < 256 ; i++){
            for (int j = 0 ; j < r ; j++){
                if(lineValsFrequency.get(i)!=null){
                  double v1 = lineValsFrequency.get(i)[j] * lineValsFrequency.get(i)[j];
                    double v2 = elemInWholeLines.get(i) * m1;
                    res+= v1/v2;
                }
            }
        }
        return n * (res - 1);
    }

    @Override
    public String getName() {
        return "Критерий проверки однородности двоичной последовательности";
    }
}
