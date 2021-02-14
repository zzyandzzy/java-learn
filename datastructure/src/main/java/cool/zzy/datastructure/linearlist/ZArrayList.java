package cool.zzy.datastructure.linearlist;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/5/19 2:26 下午
 * @since 1.0
 */
public class ZArrayList<E> implements ZList<E> {
    private static final int DEFAULT_DATA_LENGTH = 16;

    transient Object[] data;

    private int size;

    /**
     * 和JDK源码的差别在与JDK源码是用到list的时候才初始化大小
     */
    public ZArrayList() {
        data = new Object[DEFAULT_DATA_LENGTH];
    }

    public ZArrayList(int size) {
        data = new Object[size];
    }

    /**
     * 返回大小，不能直接返回data.length，因为data数组长度不一定是当前元素的总和
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) data[index];
    }

    @Override
    public E set(int index, E element) {
        E oldValue = get(index);
        data[index] = element;
        return oldValue;
    }

    @Override
    public void add(E element) {
        // 先计算当前的空间够不够
        sizeCheckAdd();
        data[size++] = element;
    }

    @Override
    public void add(int index, E element) {
        // 先计算当前的空间够不够
        sizeCheckAdd();
        // 再检查index是否超标
        rangeCheckAdd(index);
        data[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        E oldValue = get(index);
        data[index] = null;
        size--;
        return oldValue;
    }

    private void rangeCheckAdd(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void sizeCheckAdd() {
        if (size == data.length) {
            // 空间都不够了你添加啥
            throw new ArrayIndexOutOfBoundsException("Length: " + data.length + ", Size: " + size);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
