package task_08_sort2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class MergeSortRealisationTest {

    @Test
    public void testMergeSort() {
        MergeSortRealisation mergeSortRealisation = new MergeSortRealisation();
        int arrayLength = 100;

        int[] arrayToSort = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            Random random = new Random();
            arrayToSort[i] = random.nextInt(999) + 1;
        }

        int[] sortedArray = new int[arrayLength];
        System.arraycopy(arrayToSort, 0, sortedArray, 0, arrayLength);

        mergeSortRealisation.mergeSort(arrayToSort, 0, arrayLength);
        Arrays.sort(sortedArray);
        Assert.assertArrayEquals(sortedArray, arrayToSort);
    }

    @Test
    public void testBinarySearchInArray() {
        MergeSortRealisation mergeSortRealisation = new MergeSortRealisation();
        int arrayLength = 100;
        int[] sortedArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            Random random = new Random();
            sortedArray[i] = random.nextInt(999) + 1;
        }
        Arrays.sort(sortedArray);
        int indexOfElementToFind = mergeSortRealisation.binarySearchInArray(sortedArray, sortedArray[5]);
        Assert.assertEquals(indexOfElementToFind, 5);
    }
}


