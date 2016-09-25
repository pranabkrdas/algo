package com.home.latest.dp;

/**
 * Created by pranabdas on 12/27/15.
 */
public class Knapsack {

    /**
     * Recurrence relation max{A[i-1][x], A[i-1][x-w[i]] +v[i]}
     * @param v
     * @param w
     * @param W
     */
    public static void sack(int[] v, int[] w, int W){
        int N = v.length;
        int[][] A = new int[N][W+1];
        // basic cases A[0,x] = 0 and A[i,0] = 0;
        for(int i=0; i<W+1; i++){
            A[0][i] = 0;
        }
        for(int i=0; i<N; i++){
            A[i][0] = 0;
        }
        for(int i=1; i<N; i++){
            for(int x=1; x<W+1; x++){
                if(x-w[i] < 0){
                    A[i][x] = A[i-1][x];
                }
                else{
                    A[i][x] = Math.max(A[i-1][x], A[i-1][x-w[i]] + v[i]);
                }
            }
        }
        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<W+1; j++){
                System.out.print(" " + A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("result = " + A[N-1][W]);
        int i = N-1;
        int j = W;
        StringBuilder sb = new StringBuilder(N*3);
        while(i > 0 && j > 0){
            if(j-w[i] >= 0 && A[i][j] == A[i-1][j-w[i]] + v[i]){
                sb.append(i).append(" ");
                j -= w[i];
                i--;
            }
            else{
                i--;
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        int[]  v = {0, 3, 2 , 4, 4};
        int[]  w = {0, 4, 3 , 2, 3};
        sack(v, w, 6);
    }
}
