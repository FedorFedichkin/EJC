package main.java.task_04;

public class WorkWithStrings2 {
    public static void main(String[] args) {
        String[] strings = new String[] {"АРГЕНТИНА МАНИТ НЕГРА", "ПОТ КАК ПОТОП", "А РОЗА УПАЛА НА ЛАПУ АЗОРА"};
        for (String string : strings) {
            System.out.println(isPolyndrom(string) ? string + " is a polyndrom!" : string + " is not a polyndrom.");
        }
    }

    private static boolean isPolyndrom(String s){
        String string = s.replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(string).reverse();
        return (string.equals(stringBuilder.toString()));
    }
}
