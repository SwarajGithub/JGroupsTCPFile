package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] string = new String[6];
        int i = 0;
        try {
            File myObj = new File("/opt/file.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                string[i++] = data;
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        new SimpleChat(string[0],
                string[1],
                string[2],
                Long.parseLong(string[3]),
                Long.parseLong(string[4])
        ).start();
    }
}
