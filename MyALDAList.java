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
        if (isEmpty()) {
            add(element);
            return;
        }
        if (index == 0) {
            addFirst(newElement);
            return;
        }
        if (index == size) {
            addLast(newElement);
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
        if (!isIndexWithinBounds(index) || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            Node<T> removed = head;
            removeFirst();
            return removed.data;
        }
        Node<T> previous = null;
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        if (current.next == null) {
            previous.next = null;
            tail = previous;
        } else {
            previous.next = current.next;
        }
        size--;
        return current.data;

    }

    @Override
    public boolean remove(T element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (head.data == element || head.data.equals(element)) {
            removeFirst();
            return true;
        }
        Node<T> previous = head;
        for (Node<T> current = head.next; current != null; previous = current, current = current.next) {
            if ((current.data == element || current.data.equals(element))) {
                if (current.next == null) { // if removing last element update tail
                    previous.next = null;
                    tail = previous;
                } else { // in all other cases
                    previous.next = current.next;
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (!isIndexWithinBounds(index) || isEmpty()) {
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
        return head == null;
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

    private void addLast(Node<T> element) {
        tail.next = element;
        tail = element;
        size++;
    }

    private void addFirst(Node<T> element) {
        element.next = head;
        head = element;
        size++;
    }

    private boolean isIndexWithinBounds(int index) {
        if (index >= size || index < 0) {
            return false;
        }
        return true;
    }

    private void removeFirst() {
        head = head.next;
        size--;
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
            Node<T> previous;
            Node<T> previousPrevious;
            boolean canRemove;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                previousPrevious = previous;
                previous = current;
                T data = current.data;
                current = current.next;
                canRemove = true;
                return data;
            }

            @Override
            public void remove() {
                if (!canRemove) {
                    throw new IllegalStateException();
                }
                if (previousPrevious == null) {
                    head = current;
                    previous = previousPrevious;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    previousPrevious.next = current;
                    previous = previousPrevious;
                    if (current == null) {
                        tail = previousPrevious;
                    }
                }
                canRemove = false;
                size--;
            }
        };
    }


    private static class Node<T> {
        T data;
        Node<T> next;

        private Node(T data) {
            this.data = data;
        }
    }
}
