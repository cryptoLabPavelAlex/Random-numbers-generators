package com.pavel.alex.lab.first;


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

    private double trustLevels[];

    private int sampleLength;

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
                        && clazz.isAnnotationPresent(Run.class)
                )
                .collect(Collectors.toSet());

        tests = reflections.getSubTypesOf(StatisticalTest.class)
                .stream()
                .filter(
                        clazz -> !Modifier.isAbstract(clazz.getModifiers())
//                                && clazz.isAnnotationPresent(Run.class)
                )
                .collect(Collectors.toSet());
    }

        public void computeResults(){

            for (int i = 0 ; i < trustLevels.length; i++){
                int j = i;
                new Thread(()->{
                    try {
                        for(Class<? extends Generator> generatorClass : generators){
                            for (Class<? extends StatisticalTest> testClass : tests){
                                Generator g = generatorClass.newInstance();
                                StatisticalTest s = testClass
                                                .getConstructor(Generator.class,Integer.class,Double.class)
                                                .newInstance(g,sampleLength,trustLevels[j]);
                                System.out.println("Test: "+s.getName()+", Generator: "+g.getName()+" trust level: "+trustLevels[j]);
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
