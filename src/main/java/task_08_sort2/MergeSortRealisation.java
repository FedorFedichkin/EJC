package task_08_sort2;

import java.util.Random;

/**
 * This class was made to demonstrate classic bubble sort in an array of 100
 * elements with values generated randomly from 1 to 1000.
 *
 * @author Fedor Fedichkin
 */
public class MergeSortRealisation {
    private static int[] arrayToSort = new int[100];

    public static void main(String[] args) {
        MergeSortRealisation mergeSortRealisation = new MergeSortRealisation();
        Random random = new Random();
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            arrayToSort[i] = random.nextInt(999) + 1;
        }
        int myValue = 123;
        arrayToSort[arrayToSort.length - 1] = myValue;
        System.out.println("Unsorted array:");
        mergeSortRealisation.arrayToConsole(arrayToSort);
        mergeSortRealisation.mergeSort(arrayToSort, 0, arrayToSort.length);
        System.out.println();
        System.out.println("Sorted array:");
        mergeSortRealisation.arrayToConsole(arrayToSort);
        int indexOfMyValueInSortedArray = mergeSortRealisation.binarySearchInArray(arrayToSort, myValue);
        System.out.println("Index of my value " + myValue + " in sorted array is: " + indexOfMyValueInSortedArray);
    }

    /**
     * Method where merge sorting happens
     *
     * @param arrayToSort - array to sort
     * @param left        - left border of array
     * @param right       - right border of array
     */
    void mergeSort(int[] arrayToSort, int left, int right) {
        if (left + 1 < right) {
            int middle = (left + right) / 2;
            mergeSort(arrayToSort, left, middle);
            mergeSort(arrayToSort, middle, right);
            int length = right - left;
            int[] arrayBuffer = new int[length];
            int currentLeft = left;
            int currentMiddle = middle;
            for (int i = 0; i < length; i++) {
                if (currentMiddle >= right ||
                        currentLeft < middle &&
                                arrayToSort[currentLeft] < arrayToSort[currentMiddle]) {
                    arrayBuffer[i] = arrayToSort[currentLeft++];
                } else {
                    arrayBuffer[i] = arrayToSort[currentMiddle++];
                }
            }
            System.arraycopy(arrayBuffer, 0, arrayToSort, left, length);
        }
    }

    /**
     * Binary search of an index of a certain value in sorted array
     *
     * @param value - an element to find
     * @return - index of searched value in array (starts with 0)
     */
    int binarySearchInArray(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        int middle;
        while (true) {
            middle = left + (right - left) / 2;
            if (array[middle] == value) {
                return middle;
            }
            if (array[middle] > value) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
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
