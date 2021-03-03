package cool.zzy.algorithm.sort;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/4/30 11:10 上午
 * @since 1.0
 */
public class SelectSort extends SortImpl {
    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        selectSort.printSortArray();
    }

    /**
     * 最好时间复杂度：O(n^2)
     * 最坏时间复杂度：O(n^2)
     * 平均时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param array 待排序数组
     * @return 排序好的数组
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // 保存最小指针
            int minIndex = i;
            // 循环从之后的数组找到最小值
            for (int j = i; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            // 交换最小值
            if (i != minIndex) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }
}
