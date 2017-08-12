package task_08_sort2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class BubbleSortRealisationTest {

    @Test
    public void testBubbleSort() {
        BubbleSortRealisation bubbleSortRealisation = new BubbleSortRealisation();

        int[] arrayToSort = new int[100];
        for (int i = 0; i < arrayToSort.length; i++) {
            Random random = new Random();
            arrayToSort[i] = random.nextInt(999) + 1;
        }

        int[] sortedArray = new int[100];
        System.arraycopy(arrayToSort, 0, sortedArray, 0, arrayToSort.length);

        arrayToSort = bubbleSortRealisation.bubbleSort(arrayToSort);
        Arrays.sort(sortedArray);
        Assert.assertArrayEquals(sortedArray, arrayToSort);
    }
}


