package com.home.latest.sorting;

import com.home.latest.Util;

/**
 * Created by pranabdas on 6/15/16.
 */
public class Randomized3WayQuickSort {

    public void sort(Comparable[] a){
        KnuthShuffle.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    /**
     * v = a[lo], a[lt] will always have v
     * a[i] < v then exch elements of a[i] and a[lt] and increment both i and lt.
     * a[i] > v then exch element of a[i] and a[gt] and decrement gt.
     * a[i] == v then increment i.
     * @param a
     * @param lo
     * @param hi
     * @return
     */

    public void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;
        int i = lo;
        int lt = lo;
        int gt = hi;
        Comparable v = a[lo];
        while(i < gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                Util.exch(a, i++, lt++);
            }
            else if(cmp > 0){
                Util.exch(a, i, gt--);
            }
            else{
                i++;
            }
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
}
