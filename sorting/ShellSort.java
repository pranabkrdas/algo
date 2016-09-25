package com.home.latest.sorting;

import com.home.latest.Util;

/**
 * Created by pranabdas on 12/10/15.
 */
public class ShellSort {

    public static void sort(Comparable[] a){
        int N = a.length;
        int k = 1;
        while(k < N/3) k = 3*k + 1;
        while(k >= 1) {
            for (int i = k; i < N; i++) {
                for (int j = i; j >= k; j = j - k) {
                    if (a[j].compareTo(a[j - k]) < 0) {
                        Util.exch(a, j, j-k);
                    }
                    else break;
                }
            }
            k = k/3;
        }
    }

    public static void main(String[] args){
        Integer[] inp = {5, 8, 1, 12, 3, 2, 5, -6, 101, 0};
        sort(inp);
        SortUtil.printInput(inp);
        assert SortUtil.isSorted(inp);
    }
}
