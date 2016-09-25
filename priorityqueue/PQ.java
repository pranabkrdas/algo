package com.home.latest.priorityqueue;

import com.home.old.concurrency.ConditionBoundedBuffer;

/**
 * Created by pranabdas on 5/28/16.
 */
public interface PQ<T> {

    void insert(T key);

    T delete();

    boolean isEmpty();

    int size();

    T top();

}
