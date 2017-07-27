package task_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * This class was made to find the number of unique letters in an entered line (String).
 */
public class Zlatopolsky9154 {
    public static void main(String[] args) {
        Zlatopolsky9154 zlatopolsky9154 = new Zlatopolsky9154();
        int numberOfUniqueLettersInEnteredString;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            numberOfUniqueLettersInEnteredString = zlatopolsky9154.findTheNumberOfUniqueLetters(bufferedReader.readLine());
            System.out.println(numberOfUniqueLettersInEnteredString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findTheNumberOfUniqueLetters(String enteredString){
        char[] enteredStringAsCharArray = enteredString.toCharArray();
        Set<Character> setOfDifferentLettersFromEnteredString = new HashSet<>();
        for (char charFromEnteredString : enteredStringAsCharArray) {
            if (Character.isAlphabetic(charFromEnteredString)){
                setOfDifferentLettersFromEnteredString.add(charFromEnteredString);
            }
        }
        return setOfDifferentLettersFromEnteredString.size();
    }
}
