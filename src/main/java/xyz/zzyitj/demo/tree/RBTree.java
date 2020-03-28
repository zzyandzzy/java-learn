package xyz.zzyitj.demo.tree;

/**
 * xyz.zzyitj.demo.tree
 * <p>
 * <p>
 * 红黑树的性质：
 * 1：节点只能是红色或者黑色
 * 2：根节点只能是黑色
 * 3：叶子节点（NIL）只能是黑色
 * 4：如果一个节点是红色的，那么它的两个叶子节点一定是黑色的
 * 可以推导出不能有两个连续的红色节点
 * 5：从任意节点到其后代节点的简单路径上经过的黑色节点的数目都一样
 * 简称黑高相同
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/3/27 2:59 下午
 * @since 1.0
 */
public class RBTree<K extends Comparable<K>, V> {
    private RBTNode<K, V> root; // 根结点
    // 默认RED为false的好处是不用初始化插入节点的颜色
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(RBTNode<K, V> root) {
        this.root = root;
    }

    public static class RBTNode<K extends Comparable<K>, V> {
        /*
         * 节点必须为红色，因为插入红色节点可能违背第5条性质，而插入黑色节点一定违背第5条性质
         * <p>
         * B为黑色节点
         * R为红色节点
         * N为NIL即空的黑色节点
         * ^和>都表示为插入位置
         * <p>
         * 如果插入黑色节点，因为性质3叶子节点只能是黑色的，违背了性质5
         *      B                        B
         *    /   \                    /   \
         *   B     R     插入后--->    B     R
         *  / \   /  \               / \   / \
         * B   N N   N              B   N B  N
         * ^                       / \
         *                        N   N
         * 插入前到根节点的黑高为3
         * 插入后到根节点的黑高为4（N算黑色节点）
         * <p>
         * 如果插入红色色节点，那么可能没有违背性质5
         *      B                       B
         *    /   \                    /  \
         *   B     R     插入后--->    B    R
         *  / \   /  \               / \  / \
         * N   N N    N            >R  N N  N
         *                        / \
         *                       N   N
         * 插入前到根节点的黑高为3
         * 插入后到根节点的黑高还是为3
         */
        boolean color; // 颜色
        K key;    // 键
        V value;  // 值
        RBTNode<K, V> left; // 左孩子
        RBTNode<K, V> right; // 右孩子
        RBTNode<K, V> parent; // 父结点

        public RBTNode(K key, V value, RBTNode<K, V> parent, RBTNode<K, V> left, RBTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBTNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(RBTNode<K, V> left) {
            this.left = left;
        }

        public RBTNode<K, V> getRight() {
            return right;
        }

        public void setRight(RBTNode<K, V> right) {
            this.right = right;
        }

        public RBTNode<K, V> getParent() {
            return parent;
        }

        public void setParent(RBTNode<K, V> parent) {
            this.parent = parent;
        }
    }

    /**
     * 插入节点
     *
     * @param key   键
     * @param value 值
     */
    public void put(K key, V value) {
        RBTNode<K, V> node = new RBTNode<>(key, value, null, null, null);
        putVal(node);
    }

    public boolean delete(RBTNode<K, V> node) {
        return delete(node.key);
    }

    public boolean delete(K key) {
        return deleteByKey(key);
    }

    /*
     * 首先，将红黑树当作一颗二叉查找树，将该节点从二叉查找树中删除；然后，通过"旋转和重新着色"等一系列来修正该树，
     * 使之重新成为一棵红黑树。详细描述如下：
     * 第一步：将红黑树当作一颗二叉查找树，将节点删除。
     *     这和"删除常规二叉查找树中删除节点的方法是一样的"。分3种情况：
     *         ① 被删除节点没有儿子，即为叶节点。那么，直接将该节点删除就OK了。
     *         ② 被删除节点只有一个儿子。那么，直接删除该节点，并用该节点的唯一子节点顶替它的位置。
     *         ③ 被删除节点有两个儿子。
     *             那么，先找出它的后继节点；然后把“它的后继节点的内容”复制给“该节点的内容”；
     *             之后，删除“它的后继节点”。在这里，后继节点相当于替身，在将后继节点的内容复制给"被删除节点"之后，再将后继节点删除。
     *             这样就巧妙的将问题转换为"删除后继节点"的情况了，下面就考虑后继节点。
     *             在"被删除节点"有两个非空子节点的情况下，它的后继节点不可能是双子非空。
     *             既然"的后继节点"不可能双子都非空，就意味着"该节点的后继节点"要么没有儿子，要么只有一个儿子。
     *             若没有儿子，则按"情况① "进行处理；若只有一个儿子，则按"情况② "进行处理。
     *
     * 第二步：通过"旋转和重新着色"等一系列来修正该树，使之重新成为一棵红黑树。
     *         因为"第一步"中删除节点之后，可能会违背红黑树的特性。所以需要通过"旋转和重新着色"来修正该树，使之重新成为一棵红黑树。
     */
    private boolean deleteByKey(K key) {

        return false;
    }

    /**
     * 先查找到要插入的位置再修复因为插入破坏的红黑树平衡
     *
     * @param node 要插入的节点
     */
    private void putVal(RBTNode<K, V> node) {
        int cmp;
        RBTNode<K, V> parent = null;
        RBTNode<K, V> x = this.root;
        // 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            parent = x;
            cmp = node.key.compareTo(x.key);
            // 说明要添加的node比x大，往右走！
            if (cmp > 0) {
                x = x.right;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                // 插入的key已经存在直接赋值走人
                x.value = node.value;
                return;
            }
        }
        node.parent = parent;
        if (parent != null) {
            // 处理插入到父节点左边还是右边
            cmp = node.key.compareTo(parent.key);
            // 插入右边
            if (cmp > 0) {
                parent.right = node;
            } else {// 因为等于0的情况上面已经排除了，所以这里只有小于0的情况
                parent.left = node;
            }
        } else {
            // 根节点都没有，肯定是空表，让根节点等于当前插入的节点
            this.root = node;
        }
        // 修复因为插入破坏的红黑树平衡
        putValFixUp(node);
    }

    /*
     * 知识点：
     *     有父节点并且父节点是红色节点那么一定有祖父节点，因为根节点不可能是红色节点。
     * 可能有这几种情况：
     * 1：空树，直接把根节点染为黑色就行
     * 2：插入节点的父节点为黑色，不需要处理
     * 3：插入节点的父节点为红色，那么有这几种情况
     *     3.1：父节点在祖父节点的左边
     *         3.1.1 叔叔节点存在，并且为红色（父-叔双红），将父亲和叔叔节点染为黑色，将祖父节点染为红色，再把祖父节点当做当前节点递归
     *         3.1.2 叔叔节点不存在或者为黑色
     *             3.1.2.1 要插入的节点在父节点的左边（L-L双红）将父节点染为黑色，将祖父节点染为红色，以祖父节点右旋
     *             3.1.2.2 要插入的节点在父节点的右边（L-R双红）以父节点左旋，形成L-L双红，再把父节点当做当前节点递归
     *     3.2：父节点在祖父节点的右边
     *         3.2.1 叔叔节点存在，并且为红色（父-叔双红），将父亲和叔叔节点染为黑色，将祖父节点染为红色，再把祖父节点当做当前节点递归
     *         3.2.2：叔叔节点不存在或者为黑色
     *             3.2.2.1：要插入的节点在父节点的右边（R-R双红）将父节点染为黑色，将祖父节点染为红色，以祖父节点左旋
     *             3.2.2.2：要插入的节点在父节点的左边（R-L双红）以父节点右旋，形成R-R双红，再把父节点当做当前节点递归
     */
    private void putValFixUp(RBTNode<K, V> node) {
        // 暴力！直接解决第一种情况
        setBlack(this.root);
        RBTNode<K, V> parent = parentOf(node);// 父节点
        // 3：插入节点的父节点为红色
        if (parent != null && isRed(parent)) {
            RBTNode<K, V> grandparent = parentOf(parent);// 祖父节点
            // 3.1：父节点在祖父节点的左边
            if (parent == grandparent.left) {
                // 3.1.1 叔叔节点存在，并且为红色（父-叔双红），将父亲和叔叔节点染为黑色，将祖父节点染为红色，再把祖父节点当做当前节点递归
                RBTNode<K, V> uncle = grandparent.right;
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandparent);
                    putValFixUp(grandparent);
                } else {// 3.1.2 叔叔节点不存在或者为黑色
                    // 3.1.2.1 要插入的节点在父节点的左边（L-L双红）将父节点染为黑色，将祖父节点染为红色，以祖父节点右旋
                    if (node == parent.left) {
                        setBlack(parent);
                        setRed(grandparent);
                        rightRotate(grandparent);
                    } else {// 3.1.2.2 要插入的节点在父节点的右边（L-R双红）以父节点左旋，形成L-L双红，再把父节点当做当前节点递归
                        leftRotate(parent);
                        putValFixUp(parent);
                    }
                }
            } else {// 3.2：父节点在祖父节点的右边
                // 3.2.1 叔叔节点存在，并且为红色（父-叔双红），将父亲和叔叔节点染为黑色，将祖父节点染为红色，再把祖父节点当做当前节点递归
                RBTNode<K, V> uncle = grandparent.left;
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandparent);
                    putValFixUp(grandparent);
                } else {// 3.2.2：叔叔节点不存在或者为黑色
                    // 3.2.2.1：要插入的节点在父节点的右边（R-R双红）将父节点染为黑色，将祖父节点染为红色，以祖父节点左旋
                    if (node == parent.right) {
                        setBlack(parent);
                        setRed(grandparent);
                        leftRotate(grandparent);
                    } else {// 3.2.2.2：要插入的节点在父节点的左边（R-L双红）以父节点右旋，形成R-R双红，再把父节点当做当前节点递归
                        rightRotate(parent);
                        putValFixUp(parent);
                    }
                }
            }
        }
    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)-.           / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     *
     */
    private void leftRotate(RBTNode<K, V> x) {
        // 设置x的右孩子为y
        RBTNode<K, V> y = x.right;

        // 将 “y的左孩子” 设为 “x的右孩子”；
        // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;

        // 将 “x的父亲” 设为 “y的父亲”
        y.parent = x.parent;

        if (x.parent == null) {
            this.root = y;            // 如果 “x的父亲” 是空节点，则将y设为根节点
        } else {
            if (x.parent.left == x)
                x.parent.left = y;    // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            else
                x.parent.right = y;    // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
        }

        // 将 “x” 设为 “y的左孩子”
        y.left = x;
        // 将 “x的父节点” 设为 “y”
        x.parent = y;
    }

    /*
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \      --(右旋)-.            /  \                     #
     *        x   ry                           lx   y
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     *
     */
    private void rightRotate(RBTNode<K, V> y) {
        // 设置x是当前节点的左孩子。
        RBTNode<K, V> x = y.left;

        // 将 “x的右孩子” 设为 “y的左孩子”；
        // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;

        // 将 “y的父亲” 设为 “x的父亲”
        x.parent = y.parent;

        if (y.parent == null) {
            this.root = x;            // 如果 “y的父亲” 是空节点，则将x设为根节点
        } else {
            if (y == y.parent.right)
                y.parent.right = x;    // 如果 y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
            else
                y.parent.left = x;    // (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
        }

        // 将 “y” 设为 “x的右孩子”
        x.right = y;

        // 将 “y的父节点” 设为 “x”
        y.parent = x;
    }

    private RBTNode<K, V> parentOf(RBTNode<K, V> node) {
        if (node.parent != null) {
            return node.parent;
        }
        return null;
    }

    private boolean isRed(RBTNode<K, V> node) {
        return node.color == RED;
    }

    private boolean isBlack(RBTNode<K, V> node) {
        return !isRed(node);
    }

    private void setRed(RBTNode<K, V> node) {
        node.color = RED;
    }

    private void setBlack(RBTNode<K, V> node) {
        node.color = BLACK;
    }
}
