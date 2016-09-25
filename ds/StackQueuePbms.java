package com.home.latest.ds;

/**
 * Created by pranabdas on 5/12/16.
 */
public class StackQueuePbms {

    public enum Symbol{
        OPEN_PARENTHESES('('), OPEN_BRACKETS('['), OPEN_BRACES('{'), CLOSE_PARENTHESES(')'), CLOSE_BRACKETS(']'), CLOSE_BRACES('}');
        private char value;

        Symbol(char val){
            this.value = val;
        }

        public char getValue(){
            return value;
        }
    }

    /**
     * Skiena, Pg 98
     * A common problem for compilers and text editors is determining whether the parentheses in a string are balanced and properly nested.
     * For example, the string ((())())() contains properly nested pairs of parentheses, which the strings )()( and ()) do not.
     * Give an algorithm that returns true if a string contains properly nested and balanced parentheses, and false if otherwise.
     * For full credit, identify the position of the first offending parenthesis if the string is not properly nested and balanced.
     */
    public static boolean isParenthesesBalanced(String input){
        Stack<Character> stack = new StackImpl<>();
        int pos = 1;
        for(char c : input.toCharArray()){
            if(isOpenSymbol(c)){
                stack.push(c);
            }
            else{
                if(stack.isEmpty()){
                    System.out.println("1:Symbols not balance at pos=" + pos);
                    return false;
                }
                char openSym = stack.pop();
                if(!isMatching(openSym, c)){
                    System.out.println("2:Symbols not balance at pos=" + pos);
                    return false;
                }
            }
            pos++;
        }
        if(!stack.isEmpty()){
            System.out.println("3:Symbols not balance at pos=" + pos);
            return false;
        }
        System.out.println("Symbols are balanced!");
        return true;
    }

    public static boolean isOpenSymbol(char c){
        boolean result;
        switch(c){
            case '(':
                result = true;
                break;
            case '[':
                result = true;
                break;
            case '{':
                result = true;
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    public static boolean isMatching(char c1, char c2){
        if(c1 == '(' && c2 == ')') return true;
        if(c1 == '[' && c2 == ']') return true;
        if(c1 == '{' && c2 == '}') return true;
        return false;
    }

    public static void main(String[] args){
        String input = "((())((())())}";
        isParenthesesBalanced(input);
    }
}
