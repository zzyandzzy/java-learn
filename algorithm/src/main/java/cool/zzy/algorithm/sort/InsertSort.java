package cool.zzy.algorithm.sort;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/4/30 11:38 上午
 * @since 1.0
 */
public class InsertSort extends SortImpl {

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        insertSort.printSortArray();
    }

    /**
     * 最好时间复杂度：O(n)
     * 最坏时间复杂度：O(n^2)
     * 平均时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param array 待排序数组
     * @return 排序好的数组
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 待排元素小于有序序列的最后一个元素时，向前插入
            if (array[i] < array[i - 1]) {
                // 记录当前要交换的值，即有序序列之后的一个元素
                int temp = array[i];
                for (int j = i; j >= 0; j--) {
                    // 如果有序序列大于有序序列之后的一个元素
                    if (j > 0 && array[j - 1] > temp) {
                        // 将有序序列后移一位
                        array[j] = array[j - 1];
                    } else {
                        // 令有序序列之后的一个元素插入到有序序列中的合适位置
                        array[j] = temp;
                        break;
                    }
                }
            }
        }
        return array;
    }
}
