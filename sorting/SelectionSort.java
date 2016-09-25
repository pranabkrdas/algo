package com.home.latest.sorting;

/**
 * Created by pranabdas on 12/10/15.
 */
public class SelectionSort {

    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=0; i<N; i++){
            int minElemIndx = i;
            for(int j=i+1; j<N; j++){
                if(SortUtil.less(a[j], a[minElemIndx])) minElemIndx = j;
            }
            SortUtil.exch(a, i, minElemIndx);
        }
    }

    public static void main(String[] args){
        Integer[] inp = {5, 8, 1, 12, 3, 2, 5, -6, 101, 0};
        sort(inp);
        SortUtil.printInput(inp);
        assert SortUtil.isSorted(inp);
    }
}
