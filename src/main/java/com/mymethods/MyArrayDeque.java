package com.mymethods;

public class MyArrayDeque<E> {

    private E[] data;
    private int head; // index of first element
    private int tail; // index one past last element
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayDeque() {
        data = (E[]) new Object[4]; // power of two
        head = 0;
        tail = 0;
        size = 0;
    }

    // TASK 2: ADDFIRST
    // PRE: accepts an item to add
    // POST: if list is full, resize
    // calculate head index (using circular logic)
    // add item to location of head
    // update size

    // SIMILAR: boolean offerFirst(E e);
    // SIMILAR: void push(E e);
    public void addFirst(E item) {
        if (size == data.length)
            resize();
        head = (head - 1 + data.length) % data.length;
        data[head] = item;
        size++;
    }

    // PRE: accepts new element
    // POST: if list is full, resize
    // set tail location to new eleemnt
    // update tail & size
    // SIMILAR: boolean offerLast(E e);
    public void addLast(E item) {
        if (size == data.length) {
            resize();
        }

        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
    }

    // PRE: none
    // POST: if list is empty - throw exception
    // else save the first position (to return)
    // update head & size, return saved item
    // SIMILAR: E pollFirst();
    // SIMILAR: E pop();
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        E item = (E) data[head];
        data[head] = null; // avoid memory leak
        head = (head + 1) % data.length;
        size--;

        return item;
    }

    // TASK 3: REMOVELAST
    // PRE: none
    // POST: if list is not empty, reset the tail
    // (using circular queue logic)
    // save item to return, decrement size
    // return saved item

    // SIMILAR: E pollLast();
    // tail points 1 past the last element
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }

        tail = (tail - 1 + data.length) % data.length;
        E item = (E) data[tail];
        data[tail] = null;
        size--;
        return item;
    }

    // PRE: none
    // POST: if empty, throws illegal state
    // else returns the first element
    // Peek front
    // E peekFirst();
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return (E) data[head];
    }

    // PRE: none
    // POST: if empty, throws illegal state
    // else returns the last element
    // Peek back
    // SIMILAR: E peekLast();
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return (E) data[tail];
    }

    @SuppressWarnings("unchecked")
    // TASK 1
    // PRE: existing array is full
    // POST: allocate new array with 2*the current capacity
    // for items up to size (current capacity)
    // set location [0] to the value at head
    // until all items are copied
    // reset head & rear positions
    // reset data to new array

    private void resize() {
        E[] newData = (E[]) new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        head = 0;
        tail = size;
        data = newData;
    }

    // PRE: none
    // POST: returns the size of the arraydeque
    public int size() {
        return size;
    }

    // PRE: none
    // POST: returns true if the arraydeque is empty
    // false if not
    public boolean isEmpty() {
        return size == 0;
    }

    // PRE: none
    // POST: returns a string representing the
    // arraydeque contents
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            int index = (head + i) % data.length;
            sb.append(data[index]);

            if (i < size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    // TASK 4: ADDPRIORITY
    // PRE: accepts new item to add
    // assumes all previous elements were
    // added as priority
    // POST: adds new item & places based on
    // highest value
    @SuppressWarnings("unchecked")
    public void addPriority(E e) {

        if (size == 0) {
            addFirst(e);
            return;
        }

        if (size == data.length)
            resize();
        if (e instanceof Comparable ec
                && getFirst() instanceof Comparable fc
                && ec.compareTo(fc) > 0) {
            addFirst(e);
            return;
        }

        // Find point of insertion
        int i;
        for (i = 0; i < size; i++) {
            if (e instanceof Comparable ec
                    && data[(head + i) % data.length] instanceof Comparable fc
                    && ec.compareTo(fc) <= 0) {
                break;
            }
        }
        // Shift values "down"
        for (int j = size; j > i; j--) {
            int oldPos = (head + j - 1) & data.length;
            int newPos = (head + j) % data.length;
            data[newPos] = data[oldPos];
        }
        // Add new value
        size++;
        data[(head + i) % data.length] = e;
        tail = (head + size) % data.length;
    }

    // TASK 5: REMOVEITEM
    // PRE: accepts an item to remove
    // POST: removes item from queue, leaving
    // remaining items in order
    public void removeItem(E e) {
        int origSize = size();
        if (isEmpty())
            throw new IllegalStateException("Deque is empty");
        for (int i = 0; i < origSize; i++) {
            if (getFirst() != null && getFirst().equals(e)) {
                removeFirst();
            } else {
                addLast(removeFirst());
            }
        }
    }

}