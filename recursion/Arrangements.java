package com.home.latest.recursion;

/**
 * Created by pranabdas on 9/8/16.
 */
public class Arrangements {

    // n!
    public static void printAllArrangements(String s){
        printAllArrangements("", s);
    }

    public static void printAllArrangements(String prefix, String s){
        if(s == null) System.out.println(prefix);
        int n = s.length();
        if(n == 0){
            System.out.println(prefix);
        }
        else{
            for(int i=0; i<n; i++){
                printAllArrangements(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n));
            }
        }
    }

    // 2^n
    public static void printAllSubsets(String s){
        printAllSubsets("" , s);
    }

    public static void printAllSubsets(String soFar, String rest){
        if( rest == null || rest.isEmpty()){
            System.out.println(soFar);
        }
        else{
            printAllSubsets(soFar+rest.charAt(0), rest.substring(1)); // include 1st char
            printAllSubsets(soFar, rest.substring(1)); // exclude 1st char
        }
    }

    public static void main(String[] args){
        //printAllArrangements("abcd");

        System.out.println("Subsets");
        printAllSubsets("rexz");
    }
}
