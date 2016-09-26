package com.pavel.alex.lab.first.generator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Librarian implements Generator {

    private final String FILENAME = "kant.txt";
    private int index = 0;
    private int[] array;

    public Librarian() {
        try {
            System.setIn(new FileInputStream(FILENAME));
            Scanner sc = new Scanner(System.in);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            byte[] b = sb.toString().getBytes();

             array = new int[b.length];
            for (int i = 0 ; i < array.length ; i++){
                if(b[i]<0) array[i] = b[i] + 256;
                else array[i] = b[i];
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long generateNext() {
        if (index>array.length){
            index=0;
        }
        return array[index];
    }

    @Override
    public Type getType() {
        return Type.BYTE;
    }

    @Override
    public String getName() {
        return "Librarian";
    }
}
