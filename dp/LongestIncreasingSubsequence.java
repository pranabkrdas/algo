package com.home.latest.dp;

import com.home.latest.ds.Stack;
import com.home.latest.ds.StackImpl;

/**
 * Created by pranabdas on 1/3/16.
 */
public class LongestIncreasingSubsequence {

    /**
     * Recurrence relation: for i 1 to n  if lis(n) > lis(i) then lis(n) = max[lis(i) + 1]
     * @param lis
     */
    public static void lis(int[] lis){
        int N = lis.length;
        int[] m = new int[N];
        m[0] = 1;
        int q;
        for(int i=1; i<N; i++){
            q = 0;
            for(int j=0; j<i; j++){
                if(lis[i] > lis[j]){
                    q = m[j] + 1;
                    if(q > m[i]){
                        m[i] = q;
                    }
                }
                else{
                    if(m[i] == 0)m[i] = 1;
                }
            }
        }
        System.out.println();
        for(int k=0; k<N; k++){
            System.out.print(" " + m[k] + " ");
        }
        Stack<Integer> stack = new StackImpl<Integer>();
        stack.push(lis[N-1]);
        for(int k=N-2; k>=0; k--){
            if(m[k] < m[k+1]){
                stack.push(lis[k]);
            }
        }
        System.out.println();
        for(Integer out : stack){
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args){
        int[] inp = {2, 7, 3, 4, 9, 8, 12};
        lis(inp);
    }
}
