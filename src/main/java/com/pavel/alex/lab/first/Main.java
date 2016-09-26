package com.pavel.alex.lab.first;


public class Main {
    public static void main(String[] args) {

        ComputeStatistic statistic = new ComputeStatistic(new double[]{0.01, 0.05, 0.1},1000000);
        statistic.computeResults();

    }

}
