package task_07_sort;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

public class QuickSortRealisationTest {

    @Test
    public void testQuickSort() {
        QuickSortRealisation quickSortRealisation = new QuickSortRealisation();

        int[] arrayToSort = new int[100];
        for (int i = 0; i < arrayToSort.length; i++) {
            Random random = new Random();
            arrayToSort[i] = random.nextInt(999)+1;
        }

        List<Integer> listToSort = new ArrayList<>();
        int[] sortedArray = new int[100];
        for (int i = 0; i < arrayToSort.length; i++) {
            sortedArray[i] = arrayToSort[i];
            listToSort.add(arrayToSort[i]);
        }

        QuickSortRealisation.setListToSort(listToSort);
        QuickSortRealisation.quickSort(0, arrayToSort.length-1);
        listToSort = QuickSortRealisation.getListToSort();
        for (int i = 0; i < listToSort.size(); i++) {
            arrayToSort[i] = listToSort.get(i);
        }

        Arrays.sort(sortedArray);
        Assert.assertArrayEquals(sortedArray, arrayToSort);
    }
}

