package main.java.task_07_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Realisation of QuickSort using ArrayList to store 25 numbers entered from
 * console and printing it in initial order and then in sorted way.
 *
 * @author Fedor Fedichkin
 */

public class QuickSortRealisation {
    private static List<Integer> listToSort = new ArrayList<>();

    public static void main(String[] args) {
        initializeArrayList();
        System.out.println("Initial order of numbers:");
        printArrayListToConsole();
        quickSort(0, listToSort.size() - 1);
        System.out.println("Sorted list:");
        printArrayListToConsole();
    }

    private static void initializeArrayList() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Integer valueFromConsole;
            String enteredLine;
            int quantityOfNumbersInArrayList = 25;
            for (int i = 0; i < quantityOfNumbersInArrayList; i++) {
                enteredLine = reader.readLine();
                if (enteredLine.matches("[0-9]")) {
                    valueFromConsole = Integer.parseInt(enteredLine);
                    listToSort.add(valueFromConsole);
                } else {
                    System.out.println("The entered value is not a number. Please, try again.");
                    ++quantityOfNumbersInArrayList;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void quickSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (listToSort.get(i) <= listToSort.get(cur))) {
                i++;
            }
            while (j > cur && (listToSort.get(cur) <= listToSort.get(j))) {
                j--;
            }
            if (i < j) {

                int temp = listToSort.get(i);
                listToSort.set(i, listToSort.get(j));
                listToSort.set(j, temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        quickSort(start, cur);
        quickSort(cur + 1, end);
    }

    private static void printArrayListToConsole() {
        for (Integer listValue : listToSort) {
            System.out.print(listValue + " ");
        }
        System.out.println();
    }
}


