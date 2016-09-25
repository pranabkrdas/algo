package com.home.latest.tournaments.hackerrank.weekofcode23;

import java.util.*;

/**
 * Created by pranabdas on 9/13/16.
 */
public class Solution {

    static final String YES = "YES";
    static final String NO = "NO";

    static String turnPossible(int n){
        if((n % 2) == 0){
            return YES;
        }
        else{
            return NO;
        }
    }

    public static void main(String[] args) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //Scanner in = new Scanner(System.in);
        /*in.nextInt();
        int q = 2;
        if(q > 0){
            for(int i=0; i<q; i++){
                int n = 3;
                if(n <= 0){
                    continue;
                }
                System.out.println(turnPossible(n));
            }
        }*/
        //int n = in.nextInt();
        int n = Integer.parseInt(args[0]);
        int[] dotCount = new int[n];
        for(int i=0; i<n; i++){
            //char[] line = in.nextLine().toCharArray();
            String line = args[i+1];
            int curr_Count = 0;
            int temp = 0;
            char[] curr_line = line.toCharArray();
            for(char c : curr_line){
                if(c == '.') {
                    temp++;
                }
                else{
                    if(temp > curr_Count){
                        curr_Count = temp;
                    }
                    temp = 0;
                }
            }
            if(temp > curr_Count){
                curr_Count = temp;
            }
            dotCount[i] = curr_Count;
        }
        int maxCount = 0;
        for(int count : dotCount){
            if(maxCount < count){
                maxCount = count;
            }
        }
        System.out.println(lightHouse(maxCount));
    }

    static int lightHouse(int count){
        return (count-1)/2;
    }
}
