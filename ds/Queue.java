package com.home.latest.ds;

/**
 * Created by pranabdas on 3/2/16.
 */
public interface Queue<T> extends Iterable<T> {

    void enqueue(T item);

    T dequeue();

    boolean isEmpty();

    int size();

    T peek();
}
