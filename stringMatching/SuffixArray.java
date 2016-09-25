package com.home.latest.stringMatching;

import com.home.latest.sorting.ThreeWayStringQuickSort;

/**
 * Given a text of N characters, preprocess it to enable fast substring search
 * Find all occurrences of query string
 * Created by pranabdas on 12/15/15.
 */
public class SuffixArray {

    private final String[] suffixes;
    private final int N;

    public SuffixArray(String s){
        N = s.length();
        suffixes = new String[N];
        for(int i=0; i<N; i++){
            suffixes[i] = s.substring(i, N);
        }
        ThreeWayStringQuickSort.sort(suffixes);
        for(int i=0; i<N; i++){
            System.out.println(suffixes[i]);
        }
    }

    /**
     * Keyword search(Suffix sorting soln)
     * Preprocess - Suffix sort the text
     * Binary search for query, scan until mismatch
     * @param query
     * @return
     */
    public void search(String query){
        int index = rank(query);
        if(index < 0) {
            System.out.println("****No match*****");
            return;
        }
        System.out.println("******Search started******");
        for(int i=index; i<N; i++){
            if(suffixes[i].startsWith(query)){
                System.out.println(suffixes[i]);
            }
            else{
                break;
            }
        }
    }

    public int rank(String key){
        int lo = 0;
        int hi = N-1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            int cmp = key.compareTo(suffixes[mid].substring(0, key.length()));
            if(cmp < 0) hi = mid - 1;
            else if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public int charAt(String s, int d){
        if(d < s.length()) return s.charAt(d);
        return -1;
    }

    /**
     * Longest repeating substring
     */
    public String lrs(String s){
        String result = "";
        for(int i=0; i<N-1; i++){
            int max = lcp(suffixes[i], suffixes[i+1]);
            if(max > result.length()) result = suffixes[i].substring(0, max);
        }
        return result;
    }

    public static int lcp(String s, String t){
        int N = Math.min(s.length(), t.length());
        for(int i=0; i<N; i++){
            if(s.charAt(i) != t.charAt(i)) return i;
        }
        return N;
    }

    public static void main(String[] args){
        String inp = "aacaagtttacaagc";
        //SuffixArray sa = new SuffixArray(inp);
        //System.out.println("lrs=" + sa.lrs(inp));
        SuffixArray sa1 = new SuffixArray("this is the latest story of them");
        sa1.search("the");
    }
}
