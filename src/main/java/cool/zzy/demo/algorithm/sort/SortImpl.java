package cool.zzy.demo.algorithm.sort;

import cool.zzy.demo.util.SortUtils;

import java.util.Arrays;

/**
 * xyz.zzyitj.demo.algorithm.sort
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/30 11:12 上午
 * @since 1.0
 */
public abstract class SortImpl implements Sort {
    private int arrayLength = 1000;
    protected int[] array;
    private int[] oldArray;

    public SortImpl() {
    }

    @Override
    public void printSortArray() {
        array = SortUtils.genArray(arrayLength);
        oldArray = Arrays.copyOf(array, array.length);
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        long startTimeNano = System.nanoTime();
        array = sort(array);
        long endTime = System.currentTimeMillis();
        long endTimeNano = System.nanoTime();
        System.out.println(Arrays.toString(array));
        System.out.println("排序耗时: " + (endTime - startTime) + " ms");
        System.out.println("排序耗时: " + (endTimeNano - startTimeNano) + " ns");
        Arrays.sort(oldArray);
        boolean isSort = Arrays.equals(oldArray, array);
        if (isSort) {
            System.out.println("排序正确");
        } else {
            System.out.println("排序错误");
        }
    }

    public abstract int[] sort(int[] array);

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }
}
