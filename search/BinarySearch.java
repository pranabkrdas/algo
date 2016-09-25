package com.home.latest.search;

/**
 * Created by pranabdas on 12/4/15.
 */
public class BinarySearch {

    public static int search(Comparable[] arr, Comparable item){
        int N = arr.length;
        assert isOrdered(arr);
        return search(arr, item, 0, N-1);
    }

    public static int search(Comparable[] a, Comparable item, int low, int high){
        if(low > high) return -1;
        int mid = low + (high-low)/2;
        int cmp = item.compareTo(a[mid]);
        if(cmp == 0) return mid;
        if(cmp > 0) return search(a, item, mid+1, high);
        else return search(a, item, low, mid-1);
    }

    public static int countOccurrences(Comparable[] arr, Comparable item){
        int leftBoundary = countOccurrences_Left(arr, item, 0, arr.length-1);
        int rightBoundary = countOccurrences_Right(arr, item, 0, arr.length-1);
        System.out.println("lb="+leftBoundary + ", rb="+rightBoundary);
        return (rightBoundary-1) - (leftBoundary+1) + 1;
    }

    public static int countOccurrences_Left(Comparable[] arr, Comparable item, int low, int high){
        if(low > high) return high;
        int mid = low + (high-low)/2;
        if(arr[mid].compareTo(item) < 0) return countOccurrences_Left(arr, item, mid+1, high);
        else return countOccurrences_Left(arr, item, low, mid-1);
    }

    public static int countOccurrences_Right(Comparable[] arr, Comparable item, int low, int high){
        if(low > high) return low;
        int mid = low + (high-low)/2;
        if(arr[mid].compareTo(item) > 0) return countOccurrences_Right(arr, item, low, mid-1);
        else return countOccurrences_Right(arr, item, mid+1, high);
    }

    public static boolean isOrdered(Comparable[] arr){
        int N = arr.length;
        for(int i=0; i<N-1; i++){
            int cmp = arr[i].compareTo(arr[i+1]);
            if(cmp > 0) return false;
        }
        return true;
    }

    public static void main(String[] args){
        Integer [] input = {1,2,3,4,5,5,6,7,8,9,15,25};
        System.out.println("index of item = " + BinarySearch.search(input, 3));

        Integer [] input1 = {1,2,3,3,3,3,4,5,5,6,7,8,9,9,9,9,9,15,25};
        System.out.println("count = " + BinarySearch.countOccurrences(input1, 9));

        int[] input2 = {1, 2, 15, 15, 16, 19, 23, 28, 38, 40, 49, 57};
        handleSorted2(input2, 25);
    }

    /**
     * Given a set of S containing n real numbers, and a real number x. We seek an algorithm to determine whether two elements of S exist whose sum is exactly x
     * Assume that S is unsorted. Give an O(nlogn) algorithm for the problem.
     * Assume that S is sorted. Give an O(n) algorithm for the problem.
     * @param a
     * @param x
     */
    public static void handleSorted1(int[] a, int x) {
        int n = a.length;
        for(int i=0, j=n-1; i<j;){
            int temp = a[i] - x;
            if(a[j] + temp == 0){
                System.out.println("elements are a=" + a[j] + ", b=" + a[i]);
                return;
            }
            if(a[j] + temp > 0) j--;
            else i++;
        }
        System.out.println("No such pair of elements found!");
    }

    public static void handleSorted2(int[] a, int x) {
        int n = a.length;
        for(int i=0, j=n-1; i<j;){
            if(a[i] + a[j] == x){
                System.out.println("element1=" + a[i] + ", element2=" + a[j]);
                return;
            }
            if(a[i] + a[j] > x) j--;
            else i++;
        }
        System.out.println("No such pair of elements found!");
    }
}
