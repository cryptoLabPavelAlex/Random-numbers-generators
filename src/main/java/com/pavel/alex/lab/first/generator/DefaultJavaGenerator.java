package com.pavel.alex.lab.first.generator;

import com.pavel.alex.lab.first.Run;
import java.util.Random;

//@Run
public class DefaultJavaGenerator implements Generator {

    private Random random;

    public DefaultJavaGenerator() {
        random = new Random();
    }


    @Override
    public long generateNext() {
        return ((random.nextLong() % 256)+256)%256;
    }

    @Override
    public Type getType() {
        return Type.BYTE;
    }

    @Override
    public String getName() {
        return "Default Java generator";
    }
}
