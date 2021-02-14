package cool.zzy.algorithm.sort;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/4/30 11:54 上午
 * @since 1.0
 */
public class BubbleSort extends SortImpl {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.printSortArray();
    }

    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
