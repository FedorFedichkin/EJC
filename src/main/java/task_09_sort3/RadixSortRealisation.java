package task_09_sort3;

import java.util.Random;

/**
 * Radix sort, LSD version.
 */
public class RadixSortRealisation {
    private static int[] arrayToSort = new int[10];

    public static void main(String[] args) {
        RadixSortRealisation radixSortRealisation = new RadixSortRealisation();
        radixSortRealisation.initialiseArray();
        System.out.println("Unsorted array:");
        radixSortRealisation.arrayToConsole(arrayToSort);
        radixSortRealisation.radixSort(arrayToSort);
        System.out.println();
        System.out.println("Sorted array:");
        radixSortRealisation.arrayToConsole(arrayToSort);
    }

    /**
     * @param arrayToSort - array of interest where search happens
     * @return - quantity of digits in the longest number of array
     */
    private static int getMaxNumberOfDigits(int arrayToSort[]) {
        int maxNumberOfDigits = arrayToSort[0];
        for (int i = 1; i < arrayToSort.length; i++) {
            if (arrayToSort[i] > maxNumberOfDigits) {
                maxNumberOfDigits = arrayToSort[i];
            }
        }
        return maxNumberOfDigits;
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
            int countArray[] = new int[10];
            for (int i = 0; i < countArray.length; i++) {
                countArray[i] = 0;
            }
            for (int elementOfArray : arrayToSort) {
                countArray[(elementOfArray / decade) % 10]++;
            }
            for (int i = 1; i < 10; i++) {
                countArray[i] += countArray[i - 1];
            }
            for (int i = arrayToSort.length - 1; i >= 0; i--) {
                outputArray[countArray[(arrayToSort[i] / decade) % 10] - 1] = arrayToSort[i];
                countArray[(arrayToSort[i] / decade) % 10]--;
            }
            System.arraycopy(outputArray, 0, arrayToSort, 0, arrayToSort.length);
        }
    }
}
