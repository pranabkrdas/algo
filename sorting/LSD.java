package com.home.latest.sorting;

import java.util.Arrays;

/**
 * LSD Radix sort
 * considers characters from right to left
 * stably sort using dth char as the key(key indexed counting)
 * used for strings of same length.
 * eg. sorting 1 million 32-bit numbers
 * Created by pranabdas on 12/14/15.
 */
public class LSD {

    public static final int R = 256;

    public static void sort(String[] a, int W){
        int N = a.length;
        for(int i=W-1; i>=0; i--) {
            int[] count = new int[R+1];
            Arrays.fill(count, 0);
            for(int j=0; j<N; j++){
                count[a[j].charAt(i) + 1]++;
            }
            for(int r=0; r<R; r++){
                count[r+1] += count[r];
            }
            String[] aux = new String[N];
            for(int k=0; k<N; k++){
                aux[count[a[k].charAt(i)]++] = a[k];
            }
            for(int l=0; l<N; l++){
                a[l] = aux[l];
            }
        }
        assert SortUtil.isSorted(a);
    }

    public static void main(String[] args){
        String[] inp = {"abc", "abb", "aba", "zaa", "zab", "xaz"};
        sort(inp, 3);
        SortUtil.printInput(inp);
    }
}
