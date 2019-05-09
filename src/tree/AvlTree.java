package tree;

import java.util.Map;

/**
 * @Author Rock Lee
 * @Date 2019/5/5 0005 17:10
 * 带平衡的二叉查找树
 * 平衡条件：每个节点，左子树与右子树的高度差最大为1
 *
 * 当α节点的左子树与右子树的高度差2时，平衡被破坏。α节点必须重新调增平衡。
 * 插入时这种不平衡共有4中情况：
 *      1）对α的左儿子的左子树进行一次插入 -> 单旋转
 *      2）对α的左儿子的右子树进行一次插入 -> 双旋转
 *      3）对α的右儿子的左子树进行一次插入 -> 双旋转
 *      4）对α的右儿子的右子树进行一次插入 -> 单旋转
 *
 * 单旋转： 情况 1）将 α 节点的左儿子上移一层 ，4）是镜像操作
 *
 *
 *           k2                                                  k2                                    k1
 *      k1         Z      将 x 插入 X 的左节点 = >          k1         Z     k1上移一层            X        k2
 *    X    Y                                             X    Y                                 x         Y    Z
 *                                                     x
 *
 * 双旋转： 情况 2）先将 α 节点的左儿子与 α 节点左儿子的右儿子旋转一次，再将 α 节点与新的左儿子旋转一次, 3)是镜像操作
 *
 *           k2                                                  k2                                      k2                                         k3
 *      k1         Z      将 y 插入 y 的左节点 = >           k1         Z     k1与Y旋转                k3        Z        k2与Y旋转            k1         k2
 *    X    k3                                             X    k3                                   k1   y                                   X        y     Z
 *                                                              y                               X
 *
 *
 */
public class AvlTree<T extends Comparable<? super T>> {

    private static class AvlNode<T> {
        public T element;
        public AvlNode<T> leftNode;
        public AvlNode<T> rightNode;
        public int height;

        public AvlNode(T element) {
            this(element,null,null);
        }

        public AvlNode(T element, AvlNode<T> leftNode, AvlNode<T> rightNode) {
            this.element = element;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.height = 0;
        }


    }

    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<T> root;

    public AvlTree() {
        root = null;
    }

    public void insert(T element){
       root = insert(root,element);
    }

    private AvlNode<T> insert(AvlNode<T> node, T element) {
        if (node == null){
            return new AvlNode<>(element);
        }

        int result = node.element.compareTo(element);
        if (result > 0){
            node.rightNode = insert(node.rightNode,element);
        }else if (result < 0){
            node.leftNode = insert(node.leftNode,element);
        }else{
            // 值相同  do nothing
        }

        return balance(node);
    }

    private AvlNode<T> balance(AvlNode<T> node){
        if (node == null){
            return node;
        }

        if (height(node.leftNode) - height(node.rightNode) > ALLOWED_IMBALANCE){
            if (height(node.leftNode.leftNode) >= height(node.leftNode.rightNode)){
                node = rotateWithLeftChild(node);//1)
            }else{
                node = doubleWithLeftChild(node);//2)
            }

        }else if (height(node.rightNode) - height(node.leftNode) > ALLOWED_IMBALANCE){
            if (height(node.rightNode.rightNode) >= height(node.rightNode.leftNode)){
                node = rotateWithRightChild(node);//4)
            }else{
                node = doubleWithRightChild(node);//3)
            }
        }

        node.height = Math.max(height(node.leftNode),height(node.rightNode));

        return node;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> node) {
        node.rightNode = rotateWithLeftChild(node.rightNode);
        return rotateWithRightChild(node);
    }

    /*
    先将 α 节点的左儿子与 α 节点左儿子的右儿子旋转一次，再将 α 节点与新的左儿子旋转一次
     */
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> node) {
        node.leftNode = rotateWithRightChild(node.leftNode);
        return rotateWithLeftChild(node);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> node) {
        AvlNode<T> newNode = node.rightNode;
        node.rightNode = newNode.leftNode;
        newNode.leftNode = node;

        node.height = Math.max(height(node.leftNode),height(node.rightNode)) + 1;
        newNode.height = Math.max(height(newNode.rightNode),node.height) + 1;

        return newNode;
    }

    /**
     * 单旋转： 情况 1）将 α 节点的左儿子上移一层 ，4）是镜像操作
     *
     *
     *           k2                                                  k2                                    k1
     *      k1         Z      将 x 插入 X 的左节点 = >          k1         Z     k1上移一层            X        k2
     *    X    Y                                             X    Y                                 x         Y    Z
     *                                                     x
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> node) {
        AvlNode<T> newNode = node.leftNode;

        node.leftNode = newNode.rightNode;
        newNode.rightNode = node;

        node.height = Math.max(height(node.leftNode),height(node.rightNode)) + 1;
        newNode.height = Math.max(height(newNode.leftNode),node.height) + 1;
        return newNode;
    }

    public void remove(T element){
        root = remove(root,element);
    }

    private AvlNode<T> remove(AvlNode<T> node, T element) {
        if (node == null){
            return node;
        }
        int result = node.element.compareTo(element);

        if (result > 0){
            node.rightNode = remove(node.rightNode,element);
        }else if (result < 0){
            node.leftNode = remove(node.leftNode,element);
        }else if(node.leftNode != null && node.rightNode != null){
            node.element = findMin(node.rightNode).element;
            node.rightNode = remove(node.rightNode,node.element);
        }else{
            node = node.leftNode == null ? node.leftNode : node.rightNode;
        }

        return balance(node);
    }

    private int height(AvlNode<T> node){
        return node == null ? -1 : node.height;
    }

    public boolean contains(T element){
        return contains(root,element);
    }

    private boolean contains(AvlNode<T> node, T element) {
        while (node != null){
            int result = node.element.compareTo(element);
            if (result > 0 ){
                node = node.rightNode;
            }else if (result < 0){
                node = node.leftNode;
            }else{
                return true;
            }
        }
        return false;
    }

    public T findMax(){
       if (isEmpty()){
           return null;
       }
       return findMax(root).element;
    }

    private  AvlNode<T> findMax(AvlNode<T> node) {
        if (node == null){
            return  node;
        }
        while (node.rightNode != null){
            node = node.rightNode;
        }
        return node;
    }

    public T findMin(){
        if (isEmpty()){
            return null;
        }
        return findMin(root).element;
    }

    private  AvlNode<T> findMin(AvlNode<T> node) {
        if (node == null){
            return  node;
        }
        while (node.leftNode != null){
            node = node.leftNode;
        }
        return node;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }
}
