package com.home.latest.dp;

import com.home.latest.sorting.SortUtil;

import java.util.Arrays;

/**
 * Created by pranabdas on 12/16/15.
 */
public class WeightedIndependentSet {

    public static int wis(int[] a){
        int N = a.length;
        int[] count = new int[N+1];
        count[0] = 0;
        count[1] = a[0];
        for(int i=2; i<=N; i++){
            count[i] = Math.max(count[i-1], count[i-2]+a[i-1]);
        }
        //reconstruction
        System.out.println();
        for(int i=0; i<=N; i++){
            System.out.print(" " + count[i] + " ");
        }
        System.out.println();
        StringBuilder sb = new StringBuilder(3*N);
        int j = N-1;
        while(j>=1){
            sb.append(" ");
            if(a[j] + count[j-1] == count[j+1]){
                sb.append(a[j]);
                j -= 2;
            }
            else {
               j--;
            }
            sb.append(" ");
        }
        if(j == 0){
            sb.append(a[0]);
        }
        System.out.println(sb.toString());
        return count[N];
    }

    public static void main(String[] args){
        int[] inp = {2, 4, 6, 5, 2, 2, 6, 7};
        System.out.println("result1=" + wis(inp));
        System.out.println("result2=" + wis2(inp));
    }

    public static int wis2(int[] a){
        int N = a.length;
        if(N == 1) return a[0];
        if(N == 2) return a[1] > a[0] ? a[1] : a[0];
        int a0  = a[0];
        int a1 = a[1];
        int result = -1;
        for(int i=2; i<N; i++){
            result = Math.max((a0 + a[i]), a1);
            a0 = a1;
            a1 = result;
        }
        return result;
    }
}
