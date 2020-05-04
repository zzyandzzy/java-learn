package xyz.zzyitj.demo.util;

import java.util.Random;

/**
 * xyz.zzyitj.demo.util
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/30 11:18 上午
 * @since 1.0
 */
public class SortUtils {
    public static int[] genArray() {
        return genArray(1000);
    }

    public static int[] genArray(int count) {
        int[] array = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = random.nextInt(count);
        }
        return array;
    }
}
