package task_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class was made to find the length of the largest sequence of similar characters in an entered line (String).
 */
public class Zlatopolsky9153 {
    public static void main(String[] args) {
        Zlatopolsky9153 zlatopolsky9153 = new Zlatopolsky9153();
        int maxLengthOfSubsequenceOfSimilarCharacters;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            maxLengthOfSubsequenceOfSimilarCharacters = zlatopolsky9153.getMostFrequentCharFromString(bufferedReader.readLine());
            System.out.println(maxLengthOfSubsequenceOfSimilarCharacters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getMostFrequentCharFromString(String enteredString){
        if (enteredString.isEmpty()){
            return 0;
        } else {
            char[] enteredStringAsCharArray = enteredString.toCharArray();
            int currentLengthOfSubsequence = 1;
            int maxLengthOfSubsequence = 1;
            for (int i = 1; i < enteredStringAsCharArray.length; i++) {
                if (enteredStringAsCharArray[i-1] == enteredStringAsCharArray[i]){
                    currentLengthOfSubsequence++;
                    if (maxLengthOfSubsequence < currentLengthOfSubsequence){
                        maxLengthOfSubsequence = currentLengthOfSubsequence;
                    }
                } else {
                    currentLengthOfSubsequence = 1;
                }
            }
            return maxLengthOfSubsequence;
        }
    }
}
