package task_07_sort;

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

    public static void main(String[] args) {
        List<Integer> listToSort;
        InsertionSortRealisation insertionSortRealisation = new InsertionSortRealisation();
        listToSort = insertionSortRealisation.initializeArrayList();
        System.out.println("Initial order of numbers:");
        insertionSortRealisation.printArrayListToConsole(listToSort);
        listToSort = insertionSortRealisation.insertionSort(listToSort);
        System.out.println("Sorted list:");
        insertionSortRealisation.printArrayListToConsole(listToSort);
    }

    private List<Integer> initializeArrayList() {
        List<Integer> listToSort = new ArrayList<>();
        Random random = new Random();
        int quantityOfNumbersInArrayList = 100;
        for (int i = 0; i < quantityOfNumbersInArrayList; i++) {
            listToSort.add(random.nextInt(1001));
        }
        return listToSort;
    }

    public List<Integer> insertionSort(List<Integer> integerList) {
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

    private void printArrayListToConsole(List<Integer> listToSort) {
        for (Integer listValue : listToSort) {
            System.out.print(listValue + " ");
        }
        System.out.println();
    }
}