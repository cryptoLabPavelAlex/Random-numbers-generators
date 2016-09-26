package com.pavel.alex.lab.first;


import com.pavel.alex.lab.first.generator.BMBytes;
import com.pavel.alex.lab.first.generator.Generator;
import com.pavel.alex.lab.first.test.StatisticalTest;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ComputeStatistic {

    private Set<Class<? extends Generator>> generators;

    private Set<Class<? extends StatisticalTest>> tests;

    double trustLevels[] = {0.01, 0.05, 0.1};

    int sampleLength = 1000000;

    public ComputeStatistic(double[] trustLevels, int sampleLength) {
        init();
        this.trustLevels=trustLevels;
        this.sampleLength=sampleLength;
    }

    private void init() {
        List<ClassLoader> classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.pavel.alex.lab.first"))));

        generators = reflections.getSubTypesOf(Generator.class)
                .stream()
                .filter(
                        clazz -> !Modifier.isAbstract(clazz.getModifiers()) &&
                                clazz.getEnclosingClass() == null //check if not inner class
                        && !clazz.isAnnotationPresent(NotWorked.class)
                )
                .collect(Collectors.toSet());

        tests = reflections.getSubTypesOf(StatisticalTest.class)
                .stream()
                .filter(
                        clazz -> !Modifier.isAbstract(clazz.getModifiers())
                )
                .collect(Collectors.toSet());
    }

        public void computeResults(){
            double trustLevels[] = {0.01, 0.05, 0.1};
            int sampleLength = 1000000;

            for (int i = 0 ; i < trustLevels.length; i++){
                int j = i;
                new Thread(()->{
                    try {
                        for(Class<? extends Generator> generatorClass : generators){
                            for (Class<? extends StatisticalTest> testClass : tests){
                                StatisticalTest s = testClass
                                                .getConstructor(Generator.class,Integer.class,Double.class)
                                                .newInstance(generatorClass.newInstance(),sampleLength,trustLevels[j]);
                                s.test();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }).start();
            }


    }






}
