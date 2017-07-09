package main.java.task_04;

public class WorkWithStrings2 {
    public static void main(String[] args) {
        String[] strings = new String[] {"АРГЕНТИНА МАНИТ НЕГРА", "ПОТ КАК ПОТОП", "А РОЗА УПАЛА НА ЛАПУ АЗОРА"};
        for (String string : strings) {
            if (isPolyndrom(string)) {
                System.out.println(string + " is a polyndrom!");
            } else {
                System.out.println(string + " is not a polyndrom.");
            }
        }
    }

    private static boolean isPolyndrom(String string){
        String s = string.replaceAll(" ", "");
        StringBuilder s1 = new StringBuilder(s);
        StringBuilder s2 = s1.reverse();
        s1 = new StringBuilder(s);
        return (s1.toString().equals(s2.toString()));
    }
}
