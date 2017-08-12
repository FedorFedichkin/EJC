package task_09_sort3;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class RadixSortRealisationTest {

    @Test
    public void testSelectionSort() {
        RadixSortRealisation radixSortRealisation = new RadixSortRealisation();

        int arrayLength = 100;
        int[] arrayToSort = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            Random random = new Random();
            arrayToSort[i] = random.nextInt(999) + 1;
        }

        int[] sortedArray = new int[arrayLength];
        System.arraycopy(arrayToSort, 0, sortedArray, 0, arrayLength);

        radixSortRealisation.radixSort(arrayToSort);
        Arrays.sort(sortedArray);
        Assert.assertArrayEquals(sortedArray, arrayToSort);
    }
}
