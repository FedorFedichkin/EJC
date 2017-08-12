package task_09_sort3;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SelectionSortRealisationTest {

    @Test
    public void testSelectionSort() {
        SelectionSortRealisation selectionSortRealisation = new SelectionSortRealisation();

        int arrayLength = 100;
        int[] arrayToSort = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            Random random = new Random();
            arrayToSort[i] = random.nextInt(999) + 1;
        }

        int[] sortedArray = new int[arrayLength];
        System.arraycopy(arrayToSort, 0, sortedArray, 0, arrayLength);

        selectionSortRealisation.selectionSort(arrayToSort);
        Arrays.sort(sortedArray);
        Assert.assertArrayEquals(sortedArray, arrayToSort);
    }
}
