package com.home.latest.sorting;

/**
 * Most Significant Digit first Sorting
 * Partition array into R pieces according to 1st character(use key-indexed counting)
 * Recursively sort all strings that start with each character(key-indexed counting delineate subarrays to sort)
 * Is Cache inefficient
 * Too much memory storing count[]
 * Too much overhead initializing count[] and aux[]
 * Is a stable sort
 * Created by pranabdas on 12/14/15.
 */
public class MSD {

    public static final int R = 256;

    public static void sort(String[] a){
        String[] aux = new String[a.length];
        sort(a, aux, 0, a.length-1, 0);
    }

    public static void sort(String[] a, String[] aux, int lo, int hi, int d){
        if(lo >= hi) return;
        int N = a.length;
        int[] count = new int[R+1];
        for(int i=lo; i<=hi; i++){
            count[charAt(a[i], d) + 1]++;
        }
        for(int r=0; r<R; r++){
            count[r+1] += count[r];
        }
        for(int i=lo; i<=hi; i++){
            aux[count[charAt(a[i], d)]++] = a[i];
        }
        for(int i=lo; i<=hi; i++){
            a[i] = aux[i];
        }

        for(int r=0 ; r<R; r++){
            sort(a, aux, lo+count[r], lo+count[r+1]-1, d+1);
        }
    }

    public static int charAt(String s, int d){
        if(d < s.length()) return s.charAt(d);
        return -1;
    }

    public static void main(String[] args){
        String[] inp = {"abc", "abbadfaf", "aba", "zaa", "zabadfaf", "xazafda"};
        sort(inp);
        SortUtil.printInput(inp);
    }
}
