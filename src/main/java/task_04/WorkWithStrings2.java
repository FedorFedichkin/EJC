package main.java.task_04;

public class WorkWithStrings2 {
    public static void main(String[] args) {
        String[] stringsToCheckIfPalindrome = new String[]{"АРГЕНТИНА МАНИТ НЕГРА", "ПОТ КАК ПОТОП", "А РОЗА УПАЛА НА ЛАПУ АЗОРА"};
        for (String string : stringsToCheckIfPalindrome) {
            System.out.println(isPalindrome(string) ? string + " is a palindrome!" : string + " is not a palindrome.");
        }
    }

    private static boolean isPalindrome(String initialStringFromMethodArgument) {
        String stringFromMethodArgumentWithoutSpaces = initialStringFromMethodArgument.replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(stringFromMethodArgumentWithoutSpaces).reverse();
        return (stringFromMethodArgumentWithoutSpaces.equals(stringBuilder.toString()));
    }
}
