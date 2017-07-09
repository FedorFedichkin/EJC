package main.java.task_04;

public class WorkWithStrings2 {
    public static void main(String[] args) {
        String[] strings = new String[] {"АРГЕНТИНА МАНИТ НЕГРА", "ПОТ КАК ПОТОП", "А РОЗА УПАЛА НА ЛАПУ АЗОРА"};
        for (String string : strings) {
            System.out.println(isPalindrome(string) ? string + " is a palindrome!" : string + " is not a palindrome.");
        }
    }

    private static boolean isPalindrome(String s){
        String string = s.replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(string).reverse();
        return (string.equals(stringBuilder.toString()));
    }
}
