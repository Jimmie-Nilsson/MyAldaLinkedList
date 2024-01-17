// @author Jimmie Nilsson jini6619

import java.util.Iterator;

public class MyALDAList<T> implements ALDAList<T> {


    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public void add(T element) {
        if (first == null) {
            first = new Node<>(element);
            last = first;
        } else {
            last.next = new Node<>(element);
            last = last.next;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index is negative or greater than the size of the list");
        }
        Node<T> newElement = new Node<>(element);
        if (index == 0) {
            newElement.next = first;
            first = newElement;
            size++;
            return;
        }
        if (index == size) {
            last.next = newElement;
            last = newElement;
            size++;
            return;
        }
        int i = 0;
        for (Node<T> temp = first; temp != null; temp = temp.next) {
            if (i == index - 1) {
                newElement.next = temp.next;
                temp.next = newElement;
                size++;
                return;
            }
            i++;
        }
    }

    @Override
    public T remove(int index) {
        if (!isIndexWithinBounds(index)) {
            throw new IndexOutOfBoundsException("Index is bigger than the list");
        }
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> removed;
        if (index == 0) {
            removed = first;
            first = first.next;
            size--;
            return removed.data;
        }
        int i = 0;
        Node<T> previous = first;
        for (Node<T> current = first; current != null; previous = current, current = current.next) {
            if (i == index){
                previous.next = current.next;
                size--;
                return current.data;
            }
            i++;
        }
        return null;
    }

    @Override
    public boolean remove(T element) {
        if (first.data == element || first.data.equals(element)) {
            first = first.next;
            size--;
            return true;
        }
        Node<T> previous = first;
        for (Node<T> current = first.next; current != null; previous = current, current = current.next) {
            if (current.data == element || current.data.equals(element)){
                previous.next = current.next;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (!isIndexWithinBounds(index)) {
            throw new IndexOutOfBoundsException("Index is not within list");
        }
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        for (Node<T> temp = first; temp != null; temp = temp.next) {
            if (i == index) {
                return temp.data;
            }
            i++;
        }
        return null;
    }

    private boolean isEmpty() {
        if (first == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        for (Node<T> temp = first; temp != null; temp = temp.next) {
            if (temp.data == element || temp.data.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        for (Node<T> current = first; current != null; current = current.next){
            if (current.data == element || current.data.equals(element)){
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isIndexWithinBounds(int index) {
        if (index >= size || index < 0) {
            return false;
        }
        return true;
    }

    public String toString() {
        if (first == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<T> temp = first; temp != null; temp = temp.next) {
            sb.append(temp.data);
            if (temp.next != null) {
                sb.append(", ");
            }

        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
                return null;
            }
        };
    }


    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
