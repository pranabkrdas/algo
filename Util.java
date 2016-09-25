package com.home.latest;

import com.home.latest.tree.BST;

/**
 * Created by pranabdas on 5/28/16.
 */
public class Util {

    public static <T> void resize(T[] elems, int resize){
        T[] temp = (T[]) new Object[resize];
        for(int i=0; i<resize; i++){
            temp[i] = elems[i];
        }
        elems = temp;
    }

    public static <T extends Comparable<T>> boolean less(T elem1, T elem2){
        int cmp = elem1.compareTo(elem2);
        if(cmp <= 0){
            return false;
        }
        else return true;
    }

    public static <T> void exch(T[] queue, int i, int j){
        T swap = queue[i];
        queue[i] = queue[j];
        queue[j] = swap;
    }

    public void mergeBST(BST bst1, BST bst2){

    }
}
