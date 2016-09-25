package com.home.latest.sorting;

import com.home.latest.Util;

/**
 * Created by pranabdas on 6/15/16.
 */
public class QuickSelect {

    /**
     * return kth largest element
     * @param a
     * @param k
     * @return
     */
    public static Comparable select(Comparable[] a, int k){
        if(k > a.length-1) return null;
        KnuthShuffle.shuffle(a);
        return select(a, k-1, 0, a.length-1);
    }

    public static Comparable select(Comparable[] a, int k, int lo, int hi){
        int partition = partition(a, 0, a.length-1);
        if(partition == k){
            return a[partition];
        }
        else if(partition < k){
            select(a, k, lo, partition-1);
        }
        else{
            select(a, k, partition+1, hi);
        }
        return a[k];
    }

    public static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi+1;
        while(true){
            while(a[++i].compareTo(a[lo]) < 0){
                if(i == hi) break;
            }
            while(a[--j].compareTo(a[lo]) > 0){
                if(j == lo) break;
            }
            if(i >= j) break;
            Util.exch(a, i, j);
        }
        Util.exch(a, j, lo);
        return j;
    }
}
