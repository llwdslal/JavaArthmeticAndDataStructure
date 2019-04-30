package tree;

import javax.swing.text.html.parser.Entity;

/**
 * @Author Rock Lee
 * @Date 2019/4/30 0030 10:32
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private static class BinaryNode<T>{
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T element) {
            this(element,null,null);
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T entity){
        return contains(entity,root);
    }

    private boolean contains(T entity, BinaryNode<T> node) {
        if (root == null){
            return false;
        }

        int result = node.element.compareTo(entity);

        if (result == 0){
            return true;
        }else if (result < 0){
            return contains(entity,node.left);
        }else {
            return contains(entity,node.right);
        }
    }

    public T findMin(){
        return findMin(root);
    }

    private T findMin(BinaryNode<T> node) {
        if (node == null){
            return null;
        }else if (node.left == null){
            return node.element;
        }else{
            return findMin(node.left);
        }
    }

    public T findMax(){
        return findMax(root);
    }

    private T findMax(BinaryNode<T> node) {
        if (node == null){
            return null;
        }else if (node.right == null){
            return node.element;
        }else{
            return findMax(node.right);
        }
    }

    public void insert(T entity){
        root = insert(entity,root);
    }

    private BinaryNode<T> insert(T entity, BinaryNode<T> node) {
        if (node == null){
            return new BinaryNode<>(entity,null,null);
        }

        int result = node.element.compareTo(entity);

        if (result < 0){
            node.left = insert(entity,node.left);
        }else if (result > 0){
            node.right = insert(entity,node.right);
        }else{
            //TODO 重复值操作
        }
        return node;
    }

    public void remove(T entity){
        root =  remove(entity,root);
    }

    private  BinaryNode<T> remove(T entity, BinaryNode<T> node) {
        if (node == null){
            return node;
        }

        int result = node.element.compareTo(entity);

        if (result < 0){
            node.left = remove(entity,node.left);
        }else if (result > 0){
            node.right = remove(entity,node.right);
        }else if (node.left != null && node.right != null){
            T rightMin = findMin(node.right);
            node.element = rightMin;
            node.right = remove(rightMin,node.right);
        }else{
            node = (node.left != null) ? node.left : node.right;
        }

        return node;
    }

    public void printTree(){

    }
}
