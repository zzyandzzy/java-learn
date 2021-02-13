package cool.zzy.demo.algorithm.sort;

/**
 * xyz.zzyitj.demo.algorithm.sort
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/30 11:38 上午
 * @since 1.0
 */
public class InsertSort extends SortImpl {

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        insertSort.printSortArray();
    }

    @Override
    public int[] sort(int[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            //待排元素小于有序序列的最后一个元素时，向前插入
            if (array[i] < array[i - 1]) {
                temp = array[i];
                for (int j = i; j >= 0; j--) {
                    if (j > 0 && array[j - 1] > temp) {
                        array[j] = array[j - 1];
                    } else {
                        array[j] = temp;
                        break;
                    }
                }
            }
        }
        return array;
    }
}
