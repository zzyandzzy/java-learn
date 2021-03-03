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

    /**
     * 最好时间复杂度：O(nlogn)
     * 最坏时间复杂度：O(n^2)
     * 平均时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     *
     * @param array 待排序数组
     * @return 排序好的数组
     */
    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        if (array == null || array.length == 0 || left > right) {
            return;
        }
        // 选取基准值
        int pivot = array[left];
        int i = left;
        int j = right;
        while (i != j) {
            // 在右边找到比基准值小的元素
            while (array[j] >= pivot && i < j) {
                j--;
            }
            // 在左边找到比基准值大的元素
            while (array[i] <= pivot && i < j) {
                i++;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        array[left] = array[i];
        array[i] = pivot;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }
}
