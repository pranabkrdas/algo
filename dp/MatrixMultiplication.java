package com.home.latest.dp;

/**
 * Created by pranabdas on 12/31/15.
 */
public class MatrixMultiplication {

    /**
     * m(i, j) = i<=k<j min{ m(i, k) + m(k+1, j) + p(i-1)p(k)p(j) }
     * @param p
     */
    public static void multiply(int[] p){
        int N = p.length;
        int[][] m = new int[N][N];
        int[][] s = new int[N][N];
        int q;
        for(int l=1; l<N-1; l++){
            for(int i=1; i<N-1; i++){
                for(int k=i, j=i+l; k<j && j< N; k++){
                    q = 0;
                    if (m[i][j] == 0) {
                        m[i][j] = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                        s[i][j] = k;
                    } else {
                        q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                        if(q < m[i][j]){
                            m[i][j] = q;
                            s[i][j] = k;
                        }
                    }
                }
            }
        }
        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(" " + m[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(" " + s[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        int[] inp = {4, 10, 3, 12, 20, 7};
        //multiply(inp);
        matrixChain(inp);
    }

    /**
     * M[i,j] = if i==j M[i,j]=0 ;  M[i,j] = min{M[i,k] + M[k+1,j] + d(i-1)d(k)d(j)}
     * @param d
     */
    public static void matrixChain(int[] d){
        int N = d.length-1;
        int[][] M = new int[N][N];
        int[][] B = new int[N][N];
        //base case
        for(int i=0; i<N; i++){
            M[i][i] = 0;
        }
        int temp = 0;
        for(int gap=1; gap<N; gap++){
            for(int i=0; i+gap<N; i++){
                for(int k=i; k<i+gap; k++){
                    temp = M[i][k] + M[k+1][i+gap] + d[i]*d[k+1]*d[i+gap+1];
                    if(M[i][i+gap] == 0 || M[i][i+gap] > temp) {
                        M[i][i+gap] = temp;
                        B[i][i+gap] = k;
                    }
                }
            }
        }
        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(M[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(B[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println(findChain(B));
    }

    public static String findChain(int[][] B){
        int k = B[0][B.length-1];
        StringBuilder sb = new StringBuilder();
        sb.append(findChain(0, k-1, B)).append(k).append(findChain(k+1, B.length-1, B));
        return sb.toString();
    }

    public static String findChain(int i, int j, int[][] B){
        if(i >=j) return "";
        int k = B[i][j];
        if(k == i+1) return " "+k;
        if(k == j-1) return " "+k;
        StringBuilder sb = new StringBuilder();
        sb.append(findChain(i, k-1, B)).append(k).append(findChain(k+1, j, B));
        return sb.toString();
    }

}
