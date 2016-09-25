package com.home.latest.sorting;

/**
 * Do 3-way partitioning on the dth character.
 * Does not re-examine character equal to the partitioning char
 * Re-examine the characters not equal to the partitioning char
 * Short inner loop than MSD.
 * Cache friendly
 * Is In-place
 * Uses ~2NlnN character compares on avg for random strings
 * Avoid re-comparing long common prefixes
 * Not a stable sort
 * Created by pranabdas on 12/14/15.
 */
public class ThreeWayStringQuickSort {

    public static void sort(String[] a){
        KnuthShuffle.shuffle(a);
        SortUtil.printInput(a);
        //System.out.println("Shuffle done!");
        sort(a, 0, a.length-1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d){
        if(lo >= hi) return;
        int lt = lo;
        int gt = hi;
        int i = lo;
        int v = charAt(a[lo], d);
        while(i <= gt){
            int w = charAt(a[i], d);
            if(w < v){
                SortUtil.exch(a, lt++, i++);
            }
            else if(w > v){
                SortUtil.exch(a, i, gt--);
            }
            else{
                i++;
            }
        }
        //System.out.println("lt=" + lt + ", gt=" + gt);
        //SortUtil.printInput(a);
        sort(a, lo, lt-1, d);
        if(v >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }

    public static int charAt(String s, int d){
        if(d < s.length()){
            return s.charAt(d);
        }
        return -1;
    }

    public static void main(String[] args){
        String[] inp = {"z", "abc", "usdfdfa", "usdfdfb", "abb", "zx"};
        sort(inp);
        SortUtil.printInput(inp);
    }

}
