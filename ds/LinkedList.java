package com.home.latest.ds;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pranabdas on 5/15/16.
 */
public class LinkedList<E> {
    private Node first;
    private int size = 0;
    private int modCount;

    public class Node<E>{
        Node next;
        E item;

        Node(E elem1){
            item = elem1;
        }
    }

    public void add(E elem){
        Node temp = first;
        if(null == temp){
            temp = new Node(elem);
            first = temp;
            size++;
            return;
        }
        while(null != temp.next){
            temp = temp.next;
        }
        temp.next = new Node(elem);
        size++;
        modCount++;
    }

    public void delete(E elem){
        if(null == first) return;
        if(first.item == elem){
            first = null;
            return;
        }
        Node temp = first;
        while(temp != null && temp.next != null){
            if(temp.next == elem){
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        size--;
        modCount++;
    }

    public Node<E> node(int idx){
        Node<E> x = first;
        for(int i=0; i<idx; i++){
            x = x.next;
        }
        return x;
    }

    public Iterator<E> listIterator(int index){
        return new ListIter(index);
    }

    private class ListIter implements Iterator<E> {
        private int nextIndex;
        private int expectedModCount = modCount;
        private Node<E> lastReturned;
        private Node<E> next;

        ListIter(int idx){
            next = nextIndex == size ? null : node(idx);
            nextIndex = idx;
        }

        public boolean hasNext(){
            return nextIndex < size;
        }

        public E next(){
            checkForModification();
            if(!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        final void checkForModification(){
            if(expectedModCount != modCount)
                throw new ConcurrentModificationException();
        }
    }

    public void printList(){
        Iterator<E> itr = listIterator(0);
        StringBuilder sb = new StringBuilder();
        sb.append("list=[");
        while(itr.hasNext()){
            sb.append(itr.next()).append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public void reverseList(){
        if(first == null) return;
        Node p1 = null;
        Node p2 = first;
        Node p3 = first.next;
        while(p2 != null){
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            if(p3 != null) p3 = p3.next;
        }
        first = p1;
    }

    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(1);
        list.add(6);
        list.printList();
        list.reverseList();
        list.printList();
    }
}
