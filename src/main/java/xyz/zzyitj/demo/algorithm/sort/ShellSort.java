package xyz.zzyitj.demo.algorithm.sort;

/**
 * xyz.zzyitj.demo.algorithm.sort
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/8/31 13:48
 * @since 1.0
 */
public class ShellSort extends SortImpl {
    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.printSortArray();
    }

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
            gap = (int) Math.floor(gap / 3);
        }
        return array;
    }
}
