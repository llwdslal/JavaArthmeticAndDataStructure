package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author Rock Lee
 * @Date 2019/4/28 0028 17:20
 */
public class MyLinkedList<T> implements Iterable<T> {
    private static class Node<T>{
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private int theSize = 0;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyLinkedList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    public int size(){
        return theSize;
    }

    public boolean add(T entity){
        add(theSize,entity);
        return  true;
    }

    public void add(int index, T entity){
        addBefore(getNode(index,0,theSize),entity);
    }

    public T get(int index){
        return getNode(index).data;
    }

    public T set(int index, T newVal){
        Node<T> node = getNode(index);
        T oldVal = node.data;
        node.data = newVal;
        return oldVal;
    }

    public T remove(int index){
        return remove(getNode(index));
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    public void printList(){
        System.out.println(size());
        this.forEach(item -> System.out.print(item +"  ") );
    }

    public boolean contains(T entity){
        return indexOf(entity) != -1;
    }

    private int indexOf(T entity){
        int index = 0;
        if (entity == null){
            for (Node<T> i = beginMarker; i != null; i = i.next){
                if (i.data == null){
                    return index;
                }
                index ++;
            }
        }else{
            for (Node<T> i = beginMarker; i != null; i = i.next){
                if (i.data.equals(entity)){
                    return index;
                }
                index ++;
            }
        }

        return -1;
    }

    private T remove(Node<T> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        theSize --;
        modCount ++;

        return node.data;
    }

    private void addBefore(Node<T> p,T entity){
        Node<T> newNode = new Node<>(entity,p.prev,p);
        p.prev.next = newNode;
        p.prev = newNode;

        theSize ++;
        modCount ++;
    }

    private Node<T> getNode(int index){
        return getNode(index,0,theSize -1);
    }

    private Node<T> getNode(int index, int lower,int upper){

        if (index < lower || index > upper){
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> returnNode;

        if (index < theSize / 2){
            returnNode = beginMarker;
            for (int i = 0; i < index; i++) {
                returnNode = returnNode.next;
            }
        }else{
            returnNode = endMarker;
            for (int i = theSize; i > index; i--) {
                returnNode = returnNode.prev;
            }
        }

        return returnNode;
    }

    private void doClear() {
        beginMarker = new Node<>(null,null,null);
        endMarker = new Node<>(null, beginMarker,null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount ++;
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {

            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (!hasNext()){
                throw  new NoSuchElementException();
            }

            T data = current.data;
            current = current.next;
            okToRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }

            if (!okToRemove){
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);

            expectedModCount ++;

            okToRemove = false;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        System.out.println(list.get(5));
        list.printList();

        System.out.println("---------------");
        list.add(20,20);
        list.printList();

        System.out.println("---------------");
        list.remove(15);
        list.printList();

        System.out.println("---------------");
        list.set(15,15);
        list.printList();

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        System.out.println("---------------");
        list.printList();
    }
}
