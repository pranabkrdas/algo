package com.home.latest.sorting;

/**
 * Created by pranabdas on 12/10/15.
 */
public class MergeSort {


    private static final int CUT_OFF = 10;

    public void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi <= lo) return;
        if(hi < lo + CUT_OFF){
            InsertionSort.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        if(a[mid].compareTo(a[mid+1]) < 0) return;
        merge(a, aux, lo, mid, hi);
    }

    public void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        if(hi <= lo) return;
        int i = lo;
        int j = mid+1;
        for(int k=lo; k<=hi; k++){
            aux[k] = a[k];
        }

        for(int k = lo; k <= hi; k++){
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
