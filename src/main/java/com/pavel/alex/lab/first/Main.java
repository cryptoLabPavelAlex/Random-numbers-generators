package com.pavel.alex.lab.first;


import com.pavel.alex.lab.first.generator.*;
import com.pavel.alex.lab.first.test.StatisticalEquitabilityTest;
import com.pavel.alex.lab.first.test.StatisticalSignIndependencyTest;
import com.pavel.alex.lab.first.test.StatisticalTest;
import com.pavel.alex.lab.first.test.StatisticalUniformityTest;

import java.util.Map;


public class Main {
    public static void main(String[] args) {


        Generator generator = new BBS();

        StatisticalTest test = new StatisticalSignIndependencyTest(generator,1000,0.05);
        test.test();

        test = new StatisticalEquitabilityTest(generator,10000,0.05);
        test.test();

        test = new StatisticalUniformityTest(generator,10000,0.05,100);
        test.test();

    }

}
