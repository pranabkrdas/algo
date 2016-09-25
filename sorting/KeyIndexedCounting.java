package com.home.latest.sorting;

import java.util.Arrays;

/**
 * Assumption: keys are integers between 0 and R-1
 * can use keys as array index
 * Created by pranabdas on 12/14/15.
 */
public class KeyIndexedCounting {

    public static final int R = 256;

    public static void sort(Character[] a){
        int N = a.length;
        Integer[] count = new Integer[R+1];
        Arrays.fill(count, 0);
        for(int i=0; i<N; i++){
            count[a[i]+1]++;
        }
        SortUtil.printInput(count);
        for(int i=0; i<R; i++){
            count[i+1] += count[i];
        }
        SortUtil.printInput(count);
        Character[] aux = new Character[N];
        for(int i=0; i<N; i++){
            aux[count[a[i]]++] = a[i];
        }
        SortUtil.printInput(count);
        SortUtil.printInput(aux);
        for(int i=0; i<N; i++){
            a[i] = aux[i];
        }
        SortUtil.isSorted(a);
    }

    public static void main(String[] args){
        Character[] inp = {'d', 'c', 'a', 'd', 'a', 'e', 'a', 'b', 'b', 'd', 'c', 'f', 'b', 'c', 'f', 'z', '8'};
        sort(inp);
        SortUtil.printInput(inp);
    }
}
