package com.home.latest.dp;

/**
 * Created by pranabdas on 1/4/16.
 */
public class MaximumSubsequenceSum {

    /**
     * Maximum contiguous sub-sequence sum
     * Recurrence relation: mss(i) = max{mss(i-1)+a[i], a[i]}
     * @param a
     */
    public static void mss(int[] a){
        int N = a.length;
        int[] mss = new int[N+1];
        mss[0] = 0;
        for(int i=1; i<N+1; i++){
            mss[i] = Math.max(mss[i-1] + a[i-1], a[i-1]);
        }
        System.out.println();
        for(int k=1; k<N+1; k++){
            System.out.print(" " + mss[k] + " ");
        }
    }

    public static void main(String[] args){
        int[] inp = {-2, -3, 4, -1, -2, 1, 5, -3};
        mss(inp);
    }
}
