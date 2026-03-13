package com.mymethods;

import java.util.Collection;

public class MyDblLinkedList<E extends Comparable<? super E>> implements MyList<E> {

    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    /** Create an empty list */
    public MyDblLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyDblLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    // Retrieve the element at the head
    // PRE: none
    // POST:verify the list is not empty
    // return the head element
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    // Retrieve the element at the head
    // PRE: none
    // POST:verify the list is not empty
    // return the tail element
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    // Add the element to the head of the list
    // PRE: accepts the element to add
    // POST:creates the new node
    // adds the element as the new 'head' element
    // adjusts tail if the list was empty
    // increases the size of the list
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // link the new node with the head
                             // prev is already null
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null)
            tail = head; // the new node is the only node in list
    }

    // Add an element to the end of the list
    // PRE: accepts the element to add
    // POST:creates the new node
    // adds the element as the tail element
    // adjusts head if the list was empty
    // increases the size of the list
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            newNode.prev = tail;
            tail.next = newNode; // Link the new with the last node
            tail = newNode; // tail now points to the last node
        }

        size++; // Increase size
    }

    @Override
    // Add a new element at the specified index in this list
    // PRE: accepts the element to add & index location
    // POST:if index is 0 - addfirst
    // if index is at or after size - addlast
    // create node, get to index position
    // adjust pointers, increase size

    public void add(int index, E e) {
        if (index < 0) throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for range 0-" + (size - 1));
        // When list is empty, index will always fall into one of the first two conditions
        if (index == 0) {
            addFirst(e); // addFirst will handle the empty list event
        } else if (index >= size) { // addLast will handle the empty list event
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(e);
            newNode.next = current.next;
            (current.next).prev = newNode;
            newNode.prev = current;
            current.next = newNode;
            size++;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E item : c) {
            if (add((E) item))
                modified = true;
        }
        return modified;
    }

    // Remove the tail node and return the object
    // PRE: None
    // POST: Check if list is empty (return null if so)
    // save tail element & adjust pointers
    // decrement size
    // return saved element
    public E removeLast() {
        if (size == 0)
            return null;
        else if (size == 1) {
            E temp = tail.element;
            head = tail = null;
            size = 0;
            return temp;
        } else {
            // in a doubly linked list, we can start at the tail.
            E temp = tail.element;
            tail = tail.prev;
            tail.next = null;
            size--;
            return temp;

        }
    }

    // TASK 1: REMOVE FIRST
    // Remove the head node and return the object
    // PRE: None
    // POST: Check if list is empty (return null if so)
    // save head element, remove head value & adjust pointers
    // return saved element
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            E temp = head.element;
            head = tail = null;
            size = 0;
            return temp;
        } else {
            E temp = head.element;
            head = head.next;
            head.prev = null;
            size--;
            return temp;
        }

    }
    // TASK 2: ADD BEFORE
    // PRE: accepts 2 elements
    // POST: inserts the new element as follows:
    // if the list is empty, add it to the front
    // if item 'prior' is not found, add to the end of the list
    // else add new node prior to the location of insertion

    public void addBefore(E prior, E data) {
        if (!contains(prior)) {
            addLast(data);
            return;
        }
        if (size == 1 || head.element.equals(prior)) { // Meaning the one element *must* be the prior element, or the first element is the prior.
            addFirst(data);
            return;
        } else { // We know by now that our prior element must be at least in the second position
            Node<E> current = head;
            while (!current.element.equals(prior)) { // Ends when the current node has the previous element
                current = current.next;
            }
            Node<E> newNode = new Node<>(data);
            newNode.next = current;
            current.prev = newNode;
            newNode.prev.next = newNode;
            size++;
        }

    }

    // TASK 3: DELETE BEFORE
    // PRE: Function accepts the element value immediately AFTER node to be deleted
    // POST: The node prior to the value given is deleted
    public E deleteBefore(E prior) {
        if (!contains(prior)) return null; // If prior not in list, can't delete before it
        if (size <= 1) return null; // If one element, can't delete before it
        if (head.element.equals(prior)) return null; // Same thing

        if (head.next.element.equals(prior)) {
            return removeFirst();
        }

        Node<E> current = head;
        while (current != null && current.next != null) {
            if (current.next.element.equals(prior)) { // current is to be deleted
                E tempE = current.element;
                current.next.prev = current.prev;
                current.prev.next = current.next;
                size--;
                return tempE;
            }
            current = current.next;
        }
        return null; // Shouldn't get here, the !contains check should stop it first.
    }

    @Override
    // Remove the element at the specified position in this list
    // PRE: accepts the index value
    // POST:verifies the value (return null if so)
    // use remove first & last if applicable
    // else find index position & adjust pointers
    // decrement size
    // return value
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            (current.next).prev = current.prev;
            size--;
            return current.element;
        }
    }

    @Override
    // Create a string that holds values in the array
    // PRE: none
    // POST:creates a string with array values & returns string
    public String toString() {

        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    @Override
    // Clear the list
    // PRE: none
    // POST:set head & tail to null & size to 0
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    // Return true if this list contains the element
    // PRE: accepts the object
    // POST:checks data elements if found, returns true
    // else returns false
    public boolean contains(Object e) {
        // Left as an exercise in lecture 8 (same code)
        Node<E> current = head;

        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                return true;
            current = current.next;
        }

        return false;
    }

    @Override
    // Retrieve the element at the index position
    // PRE: accepts the index
    // POST:verify the index & return null if invalid
    // return the element
    public E get(int index) {
        // Left as an exercise in lecture 8 (same code)
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current.element;
    }

    @Override /**
               * Return the index of the head matching element in
               * this list. Return -1 if no match.
               */
    // PRE: accepts an object to find in the list
    // POST: if found, returns the first index where item was found
    // if not, return -1
    public int indexOf(Object e) {
        // Left as an exercise in lecture 8 (same code)
        int position = 0;
        Node<E> current = head;

        while (current != null) {
            if (current.element.equals(e))
                return position;
            current = current.next;
            position++;
        }

        return -1;
    }

    @Override
    // Returns the last index of the matching object or -1 if not found
    // PRE: accepts an object
    // POST:returns the last index if found or -1 if not

    public int lastIndexOf(E e) {
        if (!contains(e)) return -1;
        Node<E> temp = head;
        Integer lastIndex = -1;
        for (int i = 0; temp != null; i++) {
            if (temp.element.equals(e)) lastIndex = i;
            temp = temp.next;
        }
        return lastIndex;
    }

    @Override
    // Replace the element at the specified position with new element
    // PRE: accepts the index value & new element
    // POST:verifies the value (will throw an exception if invalid)
    // saves old value at the index
    // sets index value to new element
    // returns element

    public E set(int index, E e) {
        // Left as an exercise in lecture 8 (same code)
        // return null;
        if (size == 0 || index < 0 || index > size) {
            return null;
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;

        // replace current position with new data
        E currentValue = current.element;
        current.element = e;
        return currentValue;
    }

    // TASK 4: Complete the code to determine if a list is circular
    // PRE: none
    // POST: returns true if the list is circular, false if not
    public boolean isCircular() {
        return tail.next == head;
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public java.util.Iterator<E> reverseIterator() {
        return new ReverseLinkedListIterator();
    }

    @Override /** Return the number of elements in this list */
    public int size() {
        return size;
    }

    // PRE: accepts an elemenet to add in ordered
    // assumes all items added previously were added in order
    // POST: if list is empty - add the element first
    // advances a reference to the first element >= to element to add
    // if 'past' tail - add element to the end
    // if not, add before the current element
    public void addOrdered(E e) {
        if (isEmpty()) {
            addFirst(e);
            return;
        }
        Node<E> current = head;
        while (current != null && (current.element).compareTo(e) < 0) {
            current = current.next;
        }

        if (current == null)
            addLast(e);
        else
            addBefore(current.element, e);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        // TODO: not implementing at this time
        return null;
    }

    @Override
    // Retains the elements that are both in c and in this collection.
    public boolean retainAll(Collection<?> c) {
        // not implementing at this time
        return false;
    }

    private class LinkedListIterator
            implements java.util.Iterator<E> {
        private Node<E> current = head; // Current index

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

    }

    private class ReverseLinkedListIterator implements java.util.Iterator<E> {
        private Node<E> current = tail; // Start from tail instead of head

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.prev; // Move backwards
            return e;
        }

    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

}
