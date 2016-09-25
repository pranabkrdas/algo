package com.home.latest.sorting;

/**
 * Created by pranabdas on 12/10/15.
 */
public class SortUtil {

    public static void printInput(Comparable[] a){
        for(int i=0; i<a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[]a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a){
        for(int i=1; i<a.length; i++){
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i=lo+1; i<hi; i++){
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }
}
