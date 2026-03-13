package com.mymethods;

public class MyQueue<E extends Comparable<? super E>> {
    private final MyDblLinkedList<E> list = new MyDblLinkedList<>();

    public void enqueue(E item) {
        list.addLast(item);
    }

    public E pop() {
        return list.removeFirst();
    }

    public E peek() {
        return list.getFirst();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}