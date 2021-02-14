package cool.zzy.datastructure.linearlist;

/**
 * xyz.zzyitj.demo.datastructure.linearlist
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/5/19 2:19 下午
 * @since 1.0
 */
public interface ZList<E> {
    /**
     * 大小
     *
     * @return list size
     */
    int size();

    /**
     * 获取index处的元素
     *
     * @param index 下标
     * @return 获取元素
     */
    E get(int index);

    /**
     * 设置index处的元素，如果不存在就添加
     *
     * @param index   下标
     * @param element 元素
     * @return 替换掉的旧值
     */
    E set(int index, E element);

    /**
     * 在index处添加元素
     *
     * @param index   下标
     * @param element 元素
     */
    void add(int index, E element);

    /**
     * 在最后添加元素
     *
     * @param element 元素
     */
    void add(E element);

    /**
     * 删除index处的元素
     *
     * @param index 下标
     * @return 删除的元素
     */
    E remove(int index);
}
