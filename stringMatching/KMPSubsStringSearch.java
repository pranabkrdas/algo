package com.home.latest.stringMatching;

/**
 * Created by pranabdas on 9/1/16.
 */
public class KMPSubsStringSearch {

    /**
     * Basic idea is to not backtrack in text. To find a suffix which is a prefix in pattern.
     * pattern = abcdabx -> here ab is a suffix which is a prefix also. So we can build up a lookup table
     * to start from which char in pattern and not backup the text.
     * @param text
     * @param pattern
     * @return
     */
    public static int subStringSearch(String text, String pattern){
        int N = text.length();
        int M = pattern.length();
        int[] lookup = new int[M];

        // building the lookup table
        int i=0, j=1;
        while(j < M){
            char prev = pattern.charAt(i);
            char curr = pattern.charAt(j);
            if(prev != curr){
                if(i == 0) {
                    lookup[j] = 0;
                    j++;
                }
                else{
                    i = lookup[i-1];
                }
            }
            else if(prev == curr){
                lookup[j] = i+1;
                i++;
                j++;
            }
        }
        System.out.println();
        for(int item : lookup){
            System.out.print(item + " ");
        }

        // Going through the text looking for pattern.
        int m = 0;
        int n = 0;
        boolean found = false;
        while(n<N){
            if(text.charAt(n) == pattern.charAt(m)){
                m++;
                n++;
            }
            else{
                if( m > 0){
                    m = lookup[m-1];
                }
                else{
                    n++;
                }
            }
            if(m >= M) {
                found = true;
                break;
            }
        }
        int result = found ? n-m+1 : -1;
        System.out.println("result=" + result);
        return result;
    }

    public static void main(String[] args){
        subStringSearch("abxabcabcaby", "abcaby");

        subStringSearch("adf", "abcdabca");
        subStringSearch("adf", "aabaabaaa");
    }

}
