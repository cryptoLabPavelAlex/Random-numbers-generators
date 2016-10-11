package com.pavel.alex.lab.first;



public class Main {
    public static void main(String[] args) {

//        L20 g = new L20();
//        for (int i = 0 ; i < 10 ;i++){
//            g.generateNext();
//        }

        double[] trustLevel = new double[]{0.01, 0.1, 0.05};
        double[] trust = new double[]{ 0.05};
        ComputeStatistic statistic = new ComputeStatistic(trust,1000000);
        statistic.computeResults();
    }

}
