package com.home.latest.dp;

/**
 * Created by pranabdas on 8/15/16.
 */
public class Fibonacci {

    public static int calculate(int n){
        int f0 = 0;
        int f1 = 1;
        int f = -1;
        for(int i=2; i<=n; i++){
            f = f0 + f1;
            f0 = f1;
            f1 = f;
        }
        return f;
    }

    public static void main(String[] args){
        System.out.println(calculate(6));
    }

}
