package com.home.latest.dp;

/**
 * Created by pranabdas on 1/6/16.
 */
public class EditDistance {

    /**
     * Recurrence Relation: E[i][j] = min{ E[i-1][j-1] + diff(i, j), E[i-1][j] + 1, E[i][j-1] + 1 }
     * diff(i, j) = 0 if x[i] == y[j]
     * diff(i, j) = 1 if x[i] != y[j]
     * @param s1
     * @param s2
     */
    public static void edit(String s1, String s2){
        char[] x = s1.toCharArray();
        char[] y = s2.toCharArray();
        int N = x.length + 1;
        int M = y.length + 1;
        int[][] E = new int[N][M];
        for(int i=1; i<N; i++){
            E[i][0] = i;
        }
        for(int j=1; j<M; j++){
            E[0][j] = j;
        }
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                int diff = x[i-1] == y[j-1] ? 0 : 1;
                E[i][j] = Math.min(E[i-1][j] + 1, Math.min(E[i][j-1] + 1, E[i-1][j-1] + diff));
            }
        }
        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(" " + E[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        String s1 = "ztri";
        String s2 = "tria";
        //edit(s1, s2);
        editDist(s1, s2);
    }

    public static double editDist(String s1, String s2){
        char[] x = s1.toCharArray();
        char[] y = s2.toCharArray();
        int N = x.length+1;
        int M = y.length+1;
        double[][] dp = new double[N][M];
        double penalty_gap = 0.75; // same for insert/replace
        double penalty_replace = 1.0;
        for(int i=0; i<N; i++){
            dp[i][0] = i*penalty_gap;
        }
        for(int j=0; j<M; j++){
            dp[0][j] = j*penalty_gap;
        }
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                penalty_replace = x[i-1] == y[j-1] ? 0 : 1.0;
                dp[i][j] = Math.min(dp[i][j-1] + penalty_gap, Math.min(dp[i-1][j] + penalty_gap, dp[i-1][j-1] + penalty_replace));
            }
        }

        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(" " + dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("reconstructing solution!");
        StringBuilder sb = new StringBuilder(100);
        int i=N-1, j=M-1;
        sb.append("DP[" + i+"]["+j+"]").append("  ");
        while(i >0 && j>0){
            if(dp[i-1][j-1] < dp[i-1][j] && dp[i-1][j-1] < dp[i][j-1]){
                i--;
                j--;
            }
            else if(dp[i-1][j] < dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
            sb.append("DP[" + i+"]["+j+"]");
            sb.append("  ");
        }
        System.out.println(sb.toString());

        return dp[N-1][M-1];
    }

}
