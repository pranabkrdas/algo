package com.home.latest.array;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by pranabdas on 7/16/16.
 */
public class ArrayPbms {

    /**
     * Linear time Majority Vote Algorithm
     * given a list of n elements, finds all the elements that appear more than n/2 times in the list
     *
     */
    public static List<Comparable> frequencyChecker(Comparable[] a){
        if(a == null || a.length == 0) return null;
        Comparable curr_elem = a[0];
        int curr_count = 0;
        for(Comparable elem : a){
            if(curr_count == 0){
                curr_count++;
                curr_elem = elem;
            }
            else if(curr_elem.compareTo(elem) == 0){
                curr_count++;
            }
            else{
                curr_count--;
            }
        }
        curr_count = 0;
        for(Comparable elem : a){
            if(curr_elem.compareTo(elem) == 0) curr_count++;
        }
        if(curr_count > a.length/2){
            List<Comparable> result = new ArrayList<Comparable>();
            result.add(curr_elem);
            return result;
        }
        return null;
    }

    public static void main(String[] args){

    }
}
