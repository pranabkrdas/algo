package com.home.latest.dp;

/**
 * Created by pranabdas on 1/3/16.
 */
public class LongestCommonSubsequence {

    /**
     * Recurrence Relation: if x[i] = y[j] then lcs[i][j] = lcs[i-1][j-1] + 1
     *                      else when x[i] != y[j] then lcs[i][j] = max{ lcs[i-1][j], lcs[i][j-1] }
     * @param x
     * @param y
     */
    public static void lcs(char[] x, char[] y){
        int N = x.length+1;
        int M = y.length+1;
        int[][] lcs = new int[N][M];
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                if( x[i-1] == y[j-1] ){
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                }
                else{
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }
        System.out.println();
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                System.out.print(" " + lcs[i][j] + " ");
            }
            System.out.println();
        }
        StringBuilder sb = new StringBuilder(Math.max(N, M)*2);
        int j = M-1;
        int i = N-1;
        while(i>0 && j>0){
            if(lcs[i][j] == lcs[i-1][j]){
                i--;
            }
            else if(lcs[i][j] == lcs[i][j-1]){
                j--;
            }
            else if(lcs[i][j] == (lcs[i-1][j-1] + 1)){
                sb.append(x[i-1]).append(" ");
                i--;
                j--;
            }
        }
        sb.append(" = nlos");
        System.out.println(sb.reverse());
    }

    public static void main(String[] args){
        char[] X = {'A', 'B', 'A', 'C', 'E', 'B'};
        char[] Y = {'A', 'D', 'B', 'A', 'V', 'C', 'E', 'B'};
        lcs(X, Y);
        System.out.println();
        char[] X1 = {'A', 'P', 'B', 'C', 'A', 'D', 'C', 'Q', 'E', 'R'};
        char[] Y1 = {'R', 'A', 'S', 'B', 'T', 'A', 'U', 'C', 'V', 'E'};
        lcs(X1, Y1);
    }
}
