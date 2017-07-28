package task_09_sort3;

import java.util.Random;

public class RadixSortRealisation {
    private static int[] arrayToSort = new int[10];

    public static void main(String[] args) {
        RadixSortRealisation radixSortRealisation = new RadixSortRealisation();
        Random random = new Random();
        for (int i = 0; i < arrayToSort.length; i++) {
            arrayToSort[i] = random.nextInt(999) + 1;
        }
        System.out.println("Unsorted array:");
        radixSortRealisation.arrayToConsole(arrayToSort);
        radixSortRealisation.radixSort(arrayToSort);
        System.out.println();
        System.out.println("Sorted array:");
        radixSortRealisation.arrayToConsole(arrayToSort);
    }

    private static int getMaxNumberOfDigits(int arrayToSort[]) {
        int maxNumberOfDigits = arrayToSort[0];
        for (int i = 1; i < arrayToSort.length; i++)
            if (arrayToSort[i] > maxNumberOfDigits)
                maxNumberOfDigits = arrayToSort[i];
        return maxNumberOfDigits;
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

    /**
     * Method where Radix sorting happens
     *
     * @param arrayToSort - array to sort
     */
    public void radixSort(int[] arrayToSort) {
        int maxNumberOfDigits = getMaxNumberOfDigits(arrayToSort);
        for (int decade = 1; maxNumberOfDigits / decade > 0; decade *= 10) {
            int outputArray[] = new int[arrayToSort.length];
            int i;
            int countArray[] = new int[10];

            for (int j = 0; j < countArray.length; j++) {
                countArray[j] = 0;
            }
            for (i = 0; i < arrayToSort.length; i++) {
                countArray[(arrayToSort[i] / decade) % 10]++;
            }
            for (i = 1; i < 10; i++) {
                countArray[i] += countArray[i - 1];
            }
            for (i = arrayToSort.length - 1; i >= 0; i--) {
                outputArray[countArray[(arrayToSort[i] / decade) % 10] - 1] = arrayToSort[i];
                countArray[(arrayToSort[i] / decade) % 10]--;
            }
            for (i = 0; i < arrayToSort.length; i++) {
                arrayToSort[i] = outputArray[i];
            }
        }
    }
}
