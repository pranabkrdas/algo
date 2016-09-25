package com.home.latest.ds;

import java.util.Iterator;

/**
 * Created by pranabdas on 3/3/16.
 */
public class QueueWithTwoStacks<T> implements Queue<T> {

    private Stack<T> stk1 = new StackImpl();
    private Stack<T> stk2 = new StackImpl();

    private class Node{
        T item;
        Node next;
    }

    public void enqueue(T item){
        stk1.push(item);
    }

    public T dequeue(){
        if(!stk2.isEmpty()) return stk2.pop();
        if(stk1.isEmpty()) return null;
        while(!stk1.isEmpty()){
            stk2.push(stk1.pop());
        }
        return stk2.pop();
    }

    public boolean isEmpty(){
        return stk1.isEmpty() && stk2.isEmpty();
    }

    public int size(){
        return stk1.size() + stk2.size();
    }

    public T peek(){
        if(!stk2.isEmpty()) return stk2.peek();
        if(stk1.isEmpty()) return null;
        while(!stk1.isEmpty()){
            stk2.push(stk1.pop());
        }
        return stk2.peek();
    }

    public Iterator<T> iterator(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator{


        public boolean hasNext(){
            return false;
        }

        public T next(){
            if(!hasNext()) return null;
            return null;
        }

        public void remove(){

        }
    }
}
