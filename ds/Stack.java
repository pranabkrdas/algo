package com.home.latest.ds;

/**
 * Created by pranabdas on 11/26/15.
 */
public interface Stack<T> extends Iterable<T>{

    void push(T item);

    T pop();

    T peek();

    boolean isEmpty();

    int size();
}
