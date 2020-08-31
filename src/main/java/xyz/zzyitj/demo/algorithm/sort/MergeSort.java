package xyz.zzyitj.demo.algorithm.sort;

import java.util.Arrays;

/**
 * xyz.zzyitj.demo.algorithm.sort
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/8/31 14:17
 * @since 1.0
 */
public class MergeSort extends SortImpl {
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.printSortArray();
    }

    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int middle = (int) Math.floor(array.length / 2);

        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }
}
