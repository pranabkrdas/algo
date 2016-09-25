package com.home.latest.sorting;

/**
 * Find kth largest element in an array of N elements
 * Finding median
 * Created by pranabdas on 12/13/15.
 */
public class Select {

    public static Comparable select(Comparable[] a, int k){
        KnuthShuffle.shuffle(a);
        //return select(a, 0, a.length);
        int i = 0;
        int j = a.length-1;
        while(i < j){
            int partition = QuickSort.partition(a, i, j);
            if(partition > k-1){
                j = partition-1;
            }
            else if(partition < k-1){
                i = partition+1;
            }
            else{
                System.out.println(k + "th largest elem=" + a[partition]);
                return a[partition];
            }
        }
        return a[k-1];
    }

    public static void main(String[] args){
        Integer[] inp = {7, 8, 1, 12, 3, 2, 5, -6, 101, 0, 56, 43};
        InsertionSort.sort(inp);
        select(inp, inp.length/3);
        SortUtil.printInput(inp);
    }
}
