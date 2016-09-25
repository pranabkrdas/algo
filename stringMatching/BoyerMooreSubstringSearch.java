package com.home.latest.stringMatching;

import java.util.Arrays;

/**
 * Created by pranabdas on 12/16/15.
 */
public class BoyerMooreSubstringSearch {

    public static final int R = 256;

    public static int substringSearch(String text, String pattern){
        System.out.println(text);
        System.out.println(pattern);
        int[] right = new int[R];
        Arrays.fill(right, -1);
        int N = text.length();
        int M = pattern.length();
        for(int i=0; i<M; i++){
            right[pattern.charAt(i)] = i;
        }
        int skip;
        for(int i=0; i<=N-M; i+=skip){
            skip = 0;
            for(int j=M-1; j>=0; j--){
                if(pattern.charAt(j) != text.charAt(i+j)){
                    skip = Math.max(1, j - right[text.charAt(i+j)]);
                    break;
                }
            }
            if(skip == 0){
                System.out.println("Pattern found = " + i);
                return i;
            }
        }
        return N;
    }

    public static void main(String[] args){
        String text = "NEEDINAHAYSTACKISNEEDLEINAHAYSTACK";
        String pattern = "NEEDLE";
        substringSearch(text, pattern);
    }
}
