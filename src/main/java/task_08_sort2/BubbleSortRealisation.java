package task_08_sort2;

import java.util.Random;

/**
 * This class was made to demonstrate classic bubble sort in an array of 100
 * integer elements with values generated randomly from 1 to 1000.
 *
 * @author Fedor Fedichkin
 */
public class BubbleSortRealisation {

    private static int[] arrayToSort = new int[100];

    public static void main(String[] args) {
        BubbleSortRealisation bubbleSortRealisation = new BubbleSortRealisation();
        Random random = new Random();
        for (int i = 0; i < arrayToSort.length; i++) {
            arrayToSort[i] = random.nextInt(999) + 1;
        }
        System.out.println("Unsorted array:");
        bubbleSortRealisation.arrayToConsole(arrayToSort);
        arrayToSort = bubbleSortRealisation.bubbleSort(arrayToSort);
        System.out.println();
        System.out.println("Sorted array:");
        bubbleSortRealisation.arrayToConsole(arrayToSort);
    }

    /**
     * Swap function for two elements in array at positions i and j.
     *
     * @param i - array position number of the first element to swap
     * @param j - array position number of the second element to swap
     */
    private static void swap(int i, int j) {
        arrayToSort[i] = arrayToSort[i] + arrayToSort[j];
        arrayToSort[j] = arrayToSort[i] - arrayToSort[j];
        arrayToSort[i] = arrayToSort[i] - arrayToSort[j];
    }

    /**
     * Method where bubble sorting happens
     *
     * @param arrayToSort - array to sort
     */
    int[] bubbleSort(int[] arrayToSort) {
        for (int i = 0; i < arrayToSort.length; i++) {
            for (int j = i + 1; j < arrayToSort.length; j++) {
                if (arrayToSort[i] > arrayToSort[j]) {
                    arrayToSort[i] = arrayToSort[i] + arrayToSort[j];
                    arrayToSort[j] = arrayToSort[i] - arrayToSort[j];
                    arrayToSort[i] = arrayToSort[i] - arrayToSort[j];
                }
            }
        }
        return arrayToSort;
    }

    /**
     * Method that prints array to console
     *
     * @param arrayToConsole - array to print
     */
    private void arrayToConsole(int[] arrayToConsole) {
        for (int anArrayToConsole : arrayToConsole) {
            System.out.print(anArrayToConsole + " ");
        }
        System.out.println();
    }
}
