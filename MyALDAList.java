// @author Jimmie Nilsson jini6619

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyALDAList<T> implements ALDAList<T> {


    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T element) {
        if (isEmpty()) {
            head = new Node<>(element);
            tail = head;
        } else {
            tail.next = new Node<>(element);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index is negative or greater than the size of the list");
        }
        Node<T> newElement = new Node<>(element);
        if (isEmpty()){
            head = newElement;
            tail = newElement;
            return;
        }
        if (index == 0) {
            newElement.next = head;
            head = newElement;
            size++;
            return;
        }
        if (index == size) {
            tail.next = newElement;
            tail = newElement;
            size++;
            return;
        }
        int i = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (i == index - 1) {
                newElement.next = current.next;
                current.next = newElement;
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
        if (index == 0) {
            Node<T> removed = head;
            head = head.next;
            size--;
            return removed.data;
        }

        Node<T> previous = null;
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            previous = current;
           current = current.next;
        }
        if (current.next == null){
            previous.next = null;
        }else {
            previous.next = current.next;
        }
        size--;
        return current.data;

    }

    @Override
    public boolean remove(T element) {
        if (head.data == element || head.data.equals(element)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> previous = head;
        for (Node<T> current = head.next; current != null; previous = current, current = current.next) {
            if ((current.data == element || current.data.equals(element)) && current.next != null) {
                previous.next = current.next;
                size--;
                return true;
            }else if ((current.data == element || current.data.equals(element)) && current.next == null){
                previous.next = null;
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
        for (Node<T> temp = head; temp != null; temp = temp.next) {
            if (i == index) {
                return temp.data;
            }
            i++;
        }
        return null;
    }

    private boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        for (Node<T> temp = head; temp != null; temp = temp.next) {
            if (temp.data == element || temp.data.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (current.data == element || current.data.equals(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
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
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<T> temp = head; temp != null; temp = temp.next) {
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
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
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
