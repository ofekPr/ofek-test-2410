import java.util.Scanner;

public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public Node(T[] array) {
        this.value = array[0];
        Node<T> p = new Node<T>(array[1]);
        this.next = p;
        for (int i = 2; i < array.length; i++) {
            p.setNext(new Node<T>(array[i]));
            p  = p.getNext();
        }
    }

    public T getValue() {
        return this.value;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public boolean hasNext() {
        return (this.next != null);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public String toString() {
        return value + " ==> " + next;
    }
}