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

    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // 当前的指针
            int currentIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[currentIndex]) {
                    currentIndex = j;
                }
            }
            if (i != currentIndex) {
                int temp = array[i];
                array[i] = array[currentIndex];
                array[currentIndex] = temp;
            }
        }
        return array;
    }
}
