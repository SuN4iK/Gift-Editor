package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Threadlets {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("test.txt");
            String str ="Hello world";
            byte[] buff = str.getBytes();
            fos.write(buff);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
