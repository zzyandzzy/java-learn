package cool.zzy.algorithm.sort;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/6/18 6:35 下午
 * @since 1.0
 */
public class QuickSort extends SortImpl {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.printSortArray();
    }

    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        if (array == null || array.length == 0 || left > right) {
            return;
        }
        int key = array[left];
        int i = left;
        int j = right;
        while (i != j) {
            while (array[j] >= key && i < j) {
                j--;
            }
            while (array[i] <= key && i < j) {
                i++;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        array[left] = array[i];
        array[i] = key;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }
}
