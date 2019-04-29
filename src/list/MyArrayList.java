package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author Rock Lee
 * @Date 2019/4/28 0028 15:46
 */
public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theItems;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void trimToSize() {
        ensureCapacity(theSize);
    }

    public T get(int index) {
        if (index < 0 || index >= theSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public T set(int index, T newVal) {
        if (index < 0 || index >= theSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        T[] oldItems = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = oldItems[i];
        }
    }

    public boolean add(T entity) {
        add(theSize, entity);
        return true;
    }

    public void add(int index, T entity) {
        if (index < 0 || index > theSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (theItems.length == theSize) {
            ensureCapacity(theSize * 2 + 1);
        }
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = entity;
        theSize++;
    }

    public T remove(int index) {
        if (index < 0 || index >= theSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removedItem = theItems[index];

        for (int i = index; i < theSize - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedItem;
    }


    public void printList(){
        System.out.println(size());
        this.forEach(item -> System.out.print(item +"  ") );
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
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

    }
}
