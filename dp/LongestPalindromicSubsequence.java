package com.home.latest.dp;

/**
 * Created by pranabdas on 1/4/16.
 */
public class LongestPalindromicSubsequence {

    /**
     * Recurrence relation: lps(i, j) = 1 if i == j
     *                                  lps(i+1, j-1) +2 if x[i] = x[j]
     *                                  max{lps(i+1, j), lps(i, j-1)} if x[i] != x[j]
     * @param x
     */
    public static void lps(char[] x){
        int N = x.length;
        int[][] lps = new int[N+1][N+1];
        int[] soln = new int[N];
        int j;
        for(int i=1; i<N+1; i++){
            for(int k=1; k<N+1; k++){
                lps[i][k] = 1;
            }
        }
        for(int gap=1; gap<N; gap++){
            for(int i=1; i<N-gap+1; i++){
                j = i+gap;
                System.out.println("i=" + i + ", j=" + j);
                if(x[i-1] == x[j-1]){
                    lps[i][j] = lps[i+1][j-1] + 2;
                    soln[i-1] = 1;
                    soln[j-1] = 1;
                }
                else{
                    lps[i][j] = Math.max(lps[i+1][j], lps[i][j-1]);
                }
            }
        }
        System.out.println();
        for(int m=1; m<N+1; m++){
            for(int n=1; n<N+1; n++){
                System.out.print(" " + lps[m][n] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int m=0; m<N; m++){
            System.out.print(" " + soln[m] + " ");
        }
    }

    public static void main(String[] args){
        char[] inp = {'X', 'A', 'Y', 'B', 'Z', 'B', 'A'};
        lps(inp);
    }
}
