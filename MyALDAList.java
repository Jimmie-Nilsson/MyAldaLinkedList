// Jimmie Nilsson jini6619

import java.util.Iterator;

public class MyALDAList<T> implements ALDAList<T> {
    @Override
    public void add(T element) {

    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
