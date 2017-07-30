package task_09_sort3;

import java.util.Random;

public class SelectionSortRealisation {
    private static int[] arrayToSort = new int[10];

    public static void main(String[] args) {
        SelectionSortRealisation bubbleSortRealisation = new SelectionSortRealisation();
        bubbleSortRealisation.initialiseArray();
        System.out.println("Unsorted array:");
        bubbleSortRealisation.arrayToConsole(arrayToSort);
        bubbleSortRealisation.selectionSort(arrayToSort);
        System.out.println();
        System.out.println("Sorted array:");
        bubbleSortRealisation.arrayToConsole(arrayToSort);
    }

    /**
     * Array initialisation with random integer numbers from 1 to 1000.
     */
    private void initialiseArray() {
        Random random = new Random();
        for (int i = 0; i < arrayToSort.length; i++) {
            arrayToSort[i] = random.nextInt(999) + 1;
        }
    }

    /**
     * Method where Selection sorting happens
     *
     * @param arrayToSort - array to sort
     */
    public void selectionSort(int[] arrayToSort) {
        int indexOfMinimalElement;
        int temp;
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            indexOfMinimalElement = i;
            for (int j = i + 1; j < arrayToSort.length; j++) {
                if (arrayToSort[j] < arrayToSort[indexOfMinimalElement]) {
                    indexOfMinimalElement = j;
                }
            }
            temp = arrayToSort[indexOfMinimalElement];
            arrayToSort[indexOfMinimalElement] = arrayToSort[i];
            arrayToSort[i] = temp;
        }
    }

    /**
     * Method that prints array to console
     *
     * @param arrayToConsole - array to print
     */
    private void arrayToConsole(int[] arrayToConsole) {
        for (int elementOfArray : arrayToConsole) {
            System.out.print(elementOfArray + " ");
        }
        System.out.println();
    }
}
