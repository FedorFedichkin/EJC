package main.java.task_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorkWithStrings2 {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String s1 = reader.readLine();
            String s2 = s1;
            s2 = s2.replaceAll(" ", "");
            System.out.println(s2);
            StringBuilder s3 = new StringBuilder(s2);
            StringBuilder s4 = s3.reverse();
            s3 = new StringBuilder(s2);
            if (s3.toString().equals(s4.toString())){
                System.out.println(s1 + " is a polyndrom!");
            } else {
                System.out.println(s1 + " is not a polyndrom.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
