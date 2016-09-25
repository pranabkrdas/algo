package com.home.latest.sorting;

/**
 * Created by pranabdas on 6/16/16.
 */
public class MergeSortBU {

    public void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        for(int sz=2; sz<a.length; sz=sz+sz){
            for(int i=0; i+sz<a.length; i=i+sz){
                int mid = i + (sz-1)/2;
                merge(a, aux, i, mid, Math.min(i+sz-1, a.length-1));
            }
        }
    }

    public void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        if(hi <= lo) return;
        for(int k=lo; k<=hi; k++){
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid+1;
        for(int k=lo; k<=hi; k++){
            if(i > mid){
                a[k] = aux[j++];
            }
            else if(j > hi){
                a[k] = aux[i++];
            }
            else if(aux[i].compareTo(aux[j]) < 0){
                a[k] = aux[i++];
            }
            else{
                a[k] = aux[j++];
            }
        }
    }
}
