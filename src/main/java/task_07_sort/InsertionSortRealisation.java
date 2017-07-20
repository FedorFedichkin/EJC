package main.java.task_07_sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Realisation of Insertion Sort using ArrayList to store 100 numbers
 * from 0 to 1000 generated randomly and printing it in initial order
 * and then in sorted way.
 *
 * @author Fedor Fedichkin
 */
public class InsertionSortRealisation {
    private static List<Integer> listToSort = new ArrayList<>();

    public static void main(String[] args) {
        initializeArrayList();
        System.out.println("Initial order of numbers:");
        printArrayListToConsole();
        insertionSort(listToSort);
        System.out.println("Sorted list:");
        printArrayListToConsole();
    }

    private static void initializeArrayList() {
        Random random = new Random();
        int quantityOfNumbersInArrayList = 100;
        for (int i = 0; i < quantityOfNumbersInArrayList; i++) {
            listToSort.add(random.nextInt(1000));
        }
    }

    public static List<Integer> insertionSort(List<Integer> integerList) {
        int i, j, newValue;
        for (i = 1; i < integerList.size(); i++) {
            newValue = integerList.get(i);
            j = i;
            while (j > 0 && integerList.get(j - 1) > newValue) {
                integerList.set(j, integerList.get(j - 1));
                j--;
            }
            integerList.set(j, newValue);
        }
        return integerList;
    }

    private static void printArrayListToConsole() {
        for (Integer listValue : listToSort) {
            System.out.print(listValue + " ");
        }
        System.out.println();
    }
}