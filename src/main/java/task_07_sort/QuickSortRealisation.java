package task_07_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
        QuickSortRealisation quickSortRealisation = new QuickSortRealisation();
        quickSortRealisation.initializeArrayList();
        System.out.println("Initial order of numbers:");
        quickSortRealisation.printArrayListToConsole();
        quickSort(0, listToSort.size() - 1);
        System.out.println("Sorted list:");
        quickSortRealisation.printArrayListToConsole();
    }

    private void initializeArrayList() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Integer valueFromConsole;
            String enteredLine;
            int quantityOfNumbersInArrayList = 10;
            for (int i = 0; i < quantityOfNumbersInArrayList; i++) {
                enteredLine = reader.readLine();
                if (enteredLine.matches("[0-9]")) {
                    valueFromConsole = Integer.parseInt(enteredLine);
                    listToSort.add(valueFromConsole);
                } else {
                    System.out.println("The entered value is not a number. Please, try again.");
                    quantityOfNumbersInArrayList++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void quickSort(int left, int right) {
        if (left >= right)
            return;
        int i = left;
        int j = right;
        int pivot = i - (i - j) / 2;
        while (i < j) {
            while (i < pivot && (listToSort.get(i) <= listToSort.get(pivot))) {
                i++;
            }
            while (j > pivot && (listToSort.get(pivot) <= listToSort.get(j))) {
                j--;
            }
            if (i < j) {
                int temp = listToSort.get(i);
                listToSort.set(i, listToSort.get(j));
                listToSort.set(j, temp);
                if (i == pivot)
                    pivot = j;
                else if (j == pivot)
                    pivot = i;
            }
        }
        quickSort(left, pivot);
        quickSort(pivot + 1, right);
    }

    private void printArrayListToConsole() {
        for (Integer listValue : listToSort) {
            System.out.print(listValue + " ");
        }
        System.out.println();
    }
}


