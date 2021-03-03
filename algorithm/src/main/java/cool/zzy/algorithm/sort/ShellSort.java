package cool.zzy.algorithm.sort;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/8/31 13:48
 * @since 1.0
 */
public class ShellSort extends SortImpl {
    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.printSortArray();
    }

    /**
     * 最好时间复杂度：O(nlog^2n)
     * 最坏时间复杂度：O(nlog^2n)
     * 平均时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     *
     * @param array 待排序数组
     * @return 排序好的数组
     */
    @Override
    public int[] sort(int[] array) {
        int gap = 1;
        while (gap < array.length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                int tmp = array[i];
                int j = i - gap;
                while (j >= 0 && array[j] > tmp) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                array[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3.0);
        }
        return array;
    }
}
