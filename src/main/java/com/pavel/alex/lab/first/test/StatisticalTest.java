package com.pavel.alex.lab.first.test;


public interface StatisticalTest {
    void test();
    double computeStatistic();
    double limitValue(double trustLevel);
    String getName();
}
