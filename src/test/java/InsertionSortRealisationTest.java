
import org.junit.Assert;
import org.junit.Test;
import task_07_sort.InsertionSortRealisation;

import java.util.*;

public class InsertionSortRealisationTest {

    @Test
    public void testInsertionSort() {
        InsertionSortRealisation insertionSortRealisation = new InsertionSortRealisation();

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

        listToSort = insertionSortRealisation.insertionSort(listToSort);
        for (int i = 0; i < listToSort.size(); i++) {
            arrayToSort[i] = listToSort.get(i);
        }

        Arrays.sort(sortedArray);
        Assert.assertArrayEquals(sortedArray, arrayToSort);
    }
}

