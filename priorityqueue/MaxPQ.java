package com.home.latest.priorityqueue;

import com.home.latest.Util;

/**
 * Created by pranabdas on 5/28/16.
 */
public class MaxPQ<T extends Comparable<T>> implements PQ<T> {

    private T[] queue;
    private int N;

    public MaxPQ(int capacity){
        queue = (T[])new Comparable[capacity];
    }

    public MaxPQ(T[] keys){
        int size = keys.length+1;
        queue = (T[]) new Comparable[size];
        for(int i=1; i<size; i++){
            queue[i] = keys[i-1];
        }
        N = size - 1;
    }

    @Override
    public void insert(T key){
        if(null == queue || queue.length == 0){
            queue = (T[])new Comparable[16];
            queue[++N] = key;
            return;
        }
        if(queue.length == N) Util.resize(queue, 2*queue.length);
        queue[++N] = key;
        bubbleUp(N);
    }

    public void bubbleUp(int idx){
        while(idx > 1 && Util.less(queue[idx/2], queue[idx])){
            Util.exch(queue, idx, idx/2);
            idx = idx/2;
        }
    }

    @Override
    public T delete(){
        if(queue == null || queue.length == 0) return null;
        if(queue.length/4 > N) Util.resize(queue, queue.length/4);
        T top = top();
        queue[1] = queue[N--];
        queue[N+1] = null;
        bubbleDown(1);
        return top;
    }

    public void bubbleDown(int idx){
        int leftChild = 2*idx;
        while(leftChild <= N){
            if(leftChild < N && Util.less(queue[leftChild], queue[leftChild+1])){
                leftChild++;
            }
            if(!Util.less(queue[idx], queue[leftChild])) return;
            Util.exch(queue, idx, leftChild);
            idx = leftChild;
        }
    }

    @Override
    public boolean isEmpty(){
        return N == 0;
    }

    @Override
    public T top(){
        return queue[1];
    }

    @Override
    public int size(){
        return N;
    }

}
