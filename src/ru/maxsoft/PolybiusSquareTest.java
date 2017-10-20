package ru.maxsoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolybiusSquareTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Cipherable ps = new PolybiusSquare();

        String str = reader.readLine();
        String cipher = ps.encode(str);

        System.out.println(cipher);

//        System.out.println(ps.decode(str));
    }
}
