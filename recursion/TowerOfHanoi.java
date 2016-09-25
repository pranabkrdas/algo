package com.home.latest.recursion;

import java.util.Stack;

/**
 * Created by pranabdas on 4/11/16.
 */
public class TowerOfHanoi {


    public static Stack<Integer> move(int n){
        Stack<Integer> A = new Stack();
        Stack<Integer> B = new Stack();
        Stack<Integer> C = new Stack();
        for(int i=n-1; i>=0; i--){
            A.push(i);
        }
        move(n, A, C, B);
        return C;
    }

    public static void move(int n, Stack<Integer> source, Stack<Integer> target, Stack<Integer> aux){
        if(n > 0){
            move(n-1, source, aux, target);

            target.push(source.pop());

            System.out.println();
            System.out.print("Source=");
            for(Integer i : source){
                System.out.print(i + " ");
            }


            System.out.print(",   Target=");
            for(Integer i : target){
                System.out.print(i + " ");
            }

            System.out.print(",   Aux=");
            for(Integer i : aux){
                System.out.print(i + " ");
            }

            move(n-1, aux, target, source);
        }
    }

    public static void main(String[] args){
        Stack<Integer> result = move(4);
        System.out.println();
        System.out.print("Result=");
        for(Integer i : result){
            System.out.print(i + " ");
        }
    }

}
