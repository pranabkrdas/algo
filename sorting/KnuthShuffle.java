package com.home.latest.sorting;

import java.util.Random;

/**
 * Created by pranabdas on 12/10/15.
 */
public class KnuthShuffle {

    public static void shuffle(Comparable[] a){
        Random random = new Random();
        for(int i=0; i<a.length; i++){
            SortUtil.exch(a, i, random.nextInt(i+1));
        }
    }
}
