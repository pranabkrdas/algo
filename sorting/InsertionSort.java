package com.home.latest.sorting;

/**
 * Created by pranabdas on 12/10/15.
 */
public class InsertionSort {

    public static void sort(Comparable[] a){
       sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        for(int i=lo; i<=hi; i++){
            for(int j=i; j>0; j--){
                if(SortUtil.less(a[j], a[j-1])) SortUtil.exch(a, j, j-1);
                else break;
            }
        }
        SortUtil.printInput(a);
    }

    public static void main(String[] args){
        Integer[] inp = {7, 8, 1, 12, 3, 2, 5, -6, 101, 0, 56, 43};
        sort(inp);
        SortUtil.printInput(inp);
        assert SortUtil.isSorted(inp);
    }
}
