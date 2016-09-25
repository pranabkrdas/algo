package com.home.latest.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pranabdas on 11/26/15.
 */
public class StackImpl<T> implements Stack<T> {
    private Node first;
    private int count;

    public void push(T item){
        Node oldfirst = first;
        first = new Node(item);
        first.next = oldfirst;
        count++;
    }

    public T pop(){
        T item = first.item;
        first = first.next;
        count--;
        return item;
    }

    public T peek(){
        return first.item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return count;
    }

    public Iterator<T> iterator(){
        return new StackIterator();
    }

    private class Node{
        T item;
        Node next;

        public Node(T item){
            this.item = item;
        }
    }

    private class StackIterator implements Iterator<T>{
        Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }

        public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}
