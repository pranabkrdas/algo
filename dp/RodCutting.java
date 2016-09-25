package com.home.latest.dp;

import java.util.Arrays;

/**
 * Created by pranabdas on 12/27/15.
 */
public class RodCutting {

    /**
     * Recurrence relation  1<=i<N 1<=j<=i max(p(i), p(j) + r(j-i))
     * @param p
     * @param n
     * @return
     */
    public static int cutRod(int[] p, int n){
        int N = p.length;
        if(n > N) return -1;
        int[] r = new int[N];
        Arrays.fill(r, -1);
        int[] s = new int[N];
        Arrays.fill(s, 0);
        r[0] = 0;
        int q = -1;
        for(int i=1; i<N; i++){
            q = -1;
            for(int j=1; j<=i; j++){
                if(q < p[j]+r[i-j]){
                    q = p[j] + r[i-j];
                    s[i] = j;
                }
            }
            r[i] = q;
        }
        System.out.println("max value = " + r[n]);
        for(int i=0; i<r.length; i++){
            System.out.print(" " + r[i] + " ");
        }
        System.out.println();
        for(int i=0; i<s.length; i++){
            System.out.print(" " + s[i] + " ");
        }
        System.out.println();
        while(n > 0 && s[n] != 0){
            System.out.print(" " + s[n] + " ");
            n -= s[n];
        }
        return r[n];
    }

    public static int cutRod2(int[] c, int n){
        int N = c.length;
        if(n > N) return -1;
        int[] l = new int[N];
        for(int k=0; k<N; k++){
            l[k] = c[k];
        }
        int[] s = new int[N];
        Arrays.fill(s, 0);
        s[1] = 1;
        for(int i=2; i<N; i++){
            for(int j=1; j<=i/2; j++){
                if(l[i] < l[j]+l[i-j]){
                    l[i] = l[j] + l[i-j];
                    s[i] = j;
                }
            }
        }
        System.out.println("max value = " + l[n]);
        for(int i=0; i<l.length; i++){
            System.out.print(" " + l[i] + " ");
        }
        System.out.println();
        for(int i=0; i<s.length; i++){
            System.out.print(" " + s[i] + " ");
        }
        System.out.println();
        while(n > 0 && s[n] != 0){
            System.out.print(" " + s[n] + " ");
            n -= s[n];
        }
        return l[n];
    }

    public static int cutRod3(int[] p, int n, int K){
        int N = p.length;
        if(n > N) return -1;
        int[][] r = new int[N][K+1];
        Arrays.fill(r, -1);
        int[] s = new int[N];
        Arrays.fill(s, 0);
        r[0][0] = 0;
        int q = -1;
        for(int i=1; i<N; i++){
            q = -1;
            for(int j=1; j<=i; j++){
                for(int k=1; k<=K; k++){
                    if(q < p[j]+r[i-j][k]){
                        q = p[j] + r[i-j][k];
                        s[i] = j;
                    }
                }
            }
            r[i][1] = q;
        }
        System.out.println("max value = " + r[n]);
        for(int i=0; i<r.length; i++){
            System.out.print(" " + r[i] + " ");
        }
        System.out.println();
        for(int i=0; i<s.length; i++){
            System.out.print(" " + s[i] + " ");
        }
        System.out.println();
        while(n > 0 && s[n] != 0){
            System.out.print(" " + s[n] + " ");
            n -= s[n];
        }
        return r[n][1];
    }

    public static void main(String[] args){
        int[] p1 = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        cutRod(p1, 8);

        int[] p2 = {0, 2, 3, 5, 7, 10, 17, 17, 20, 24, 30};
        //cutRod2(p2, 3);

        int[] p3 = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        //cutRod3(p3, 5, 11);

        int[] p4 = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        //cutRod4(p4, 5, 1);
    }
}
