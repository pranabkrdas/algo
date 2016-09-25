package com.home.latest.dp;

import java.util.Arrays;

/**
 * Created by pranabdas on 1/3/16.
 */
public class CoinChangeProblem {

    /**
     * Recurrence relation A = amt. C(A) = min[C(A-v(i))] + 1 for i 1 to n where v1<v2<....<vn are coin denominations
     * @param den
     * @param amt
     */
    public static void coinChange(int[] den, int amt){
        int[] c = new int[amt+1];
        int[] aux = new int[amt+1];
        int q;
        Arrays.fill(c, Integer.MAX_VALUE);
        c[0] = 0;
        for(int i=1; i<amt+1; i++){
            q = Integer.MAX_VALUE;
            for(int j=0; j<den.length; j++){
                if(i - den[j] >= 0){
                    q = c[i-den[j]] + 1;
                    if(q < c[i]){
                        c[i] = q;
                        aux[i] = den[j];
                    }
                }
            }
        }
        System.out.println();
        for(int k=1; k<amt+1; k++){
            System.out.print(" " + c[k] + " ");
        }
        System.out.println();
        for(int k=1; k<amt+1; k++){
            System.out.print(" " + aux[k] + " ");
        }
        int a = amt;
        StringBuilder sb = new StringBuilder(amt*3 + 1);
        sb.append("soln = ");
        while(a > 0){
            sb.append(aux[a]).append(" ");
            a -= aux[a];
        }
        System.out.println(sb.toString());

    }


    public static void main(String[] args){
        int[] den = {1, 5, 10, 20, 50, 100};
        coinChange(den, 38);
    }
}
