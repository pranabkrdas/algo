package com.home.latest.suffixtree;

import com.home.latest.sorting.ThreeWayStringQuickSort;

/**
 * Created by pranabdas on 6/6/16.
 */
public class SuffixArray {
    private String[] suffixes;
    private int N;

    public SuffixArray(String input){
        N = input.length();
        suffixes = new String[N];
        for(int i=0; i<N; i++){
            suffixes[i] = input.substring(i);
        }
        ThreeWayStringQuickSort.sort(suffixes);
    }

    public String select(int idx){
        return suffixes[idx];
    }

    public int index(int idx){
        return N - suffixes[idx].length();
    }

    public int length(){
        return N;
    }

    public int lcp(int idx){
        return lcp(suffixes[idx], suffixes[idx-1]);
    }

    public int lcp(String s1, String s2){
        int n = Math.min(s1.length(), s2.length());
        for(int i=0; i<n; i++){
            if(!s1.equals(s2)) return i;
        }
        return n;
    }

    public int rank(String key){
        int lo = 0;
        int hi = N-1;
        int mid;
        while(lo <= hi){
            mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(suffixes[mid]);
            if(cmp == 0) return mid;
            else if(cmp < 0) hi = mid-1;
            else lo = mid+1;
        }
        return lo;
    }

}
