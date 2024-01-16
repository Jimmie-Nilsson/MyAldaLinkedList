// Jimmie Nilsson jini6619

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyALDAList<T> implements ALDAList<T>, Iterable<T> {


    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T element) {
        if (first == null) {
            first = new Node<>(element);
            last = first;
        } else {
            last.next = new Node<>(element);
            last = last.next;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException("Index is negative or greater than the size of the list");
        }
        int i = 0;
        Node<T> newElement = new Node<>(element);
        for (Node<T> temp = first; temp != null; temp = temp.next){
            if (index == 0){
                newElement.next = first;
                first = newElement;
                return;
            }
            if (i == index -1){
                newElement.next = temp.next;
                temp.next = newElement;
                return;
            }
        }
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
        if (index > size()){
            throw new IndexOutOfBoundsException("Index is bigger than the list");
        }
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        for (Node<T> temp = first; temp.next != null; temp = temp.next){

        }
    }
    private boolean isEmpty(){
        if (first == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        for (Node<T> temp = first; temp != null; temp = temp.next) {
            if (temp == element || temp.equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
    }

    @Override
    public int size() {
        if (first == null){
            return -1;
        }
        int i = 0;
        Node<T> current = first;
        while (current.next != null) {
            i++;
        }
        return i;
    }

    public String toString(){
        if (first == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        for (Node<T> temp = first; temp != null; temp = temp.next){
            sb.append(temp);
        }
        return sb.toString();
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
