package com.home.latest.ds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by pranabdas on 3/2/16.
 */
public class QueueImpl<T> implements Queue<T> {

    private Node first;
    private Node last;
    private int count;

    private class Node{
        T item;
        Node next;
    }

    public void enqueue(T data){
        Node oldLast = last;
        last = new Node();
        last.item = data;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        count++;
    }

    public T dequeue(){
        if(isEmpty()) return null;
        T item = first.item;
        first = first.next;
        if(null == first) last = null;
        count--;
        return item;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public T peek(){
        if(isEmpty()) return null;
        return first.item;
    }

    public Iterator<T> iterator(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T>{
        private Node current = first;

        public boolean hasNext(){
            return null != current;
        }

        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){
            List abs = new ArrayList();
        }
    }

}
