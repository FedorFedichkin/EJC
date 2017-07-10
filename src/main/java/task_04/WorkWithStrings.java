package main.java.task_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorkWithStrings {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String string = reader.readLine();
            showOddCharsFromString(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showOddCharsFromString(String string) {
        char[] myCharArray = string.toCharArray();
        for (int i = 0; i < myCharArray.length; i++) {
            if (i % 2 == 0) {
                System.out.print(myCharArray[i] + " ");
            }
        }
    }
}
