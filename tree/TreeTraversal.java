package com.home.latest.tree;


import java.util.*;
import java.util.Stack;

/**
 * Created by pranabdas on 11/30/15.
 */
public class TreeTraversal<Key extends Comparable<Key>, Value> {

    private class Tuple{
        public final Node<Integer, Integer> node;
        public final int hd;

        public Tuple(Node x, int horizontalDist){
            this.node = x;
            this.hd = horizontalDist;
        }
    }

    public void printHorizontalSpaces(int count){
        for(int i=0; i<count; i++){
            System.out.print(" ");
        }
    }

    public void printVerticalSpaces(int count){
        for(int i=0; i<count; i++){
            System.out.println();
        }
    }

    public static int maxLevel(Node x){
        if(x == null) return 0;
        return Math.max(maxLevel(x.left) , maxLevel(x.right)) + 1;
    }

    public void levelOrderTraversal(Node<Integer, Integer> x){
        Deque<Tuple> deq = new ArrayDeque<>();
        Deque<Tuple> auxDeq = new ArrayDeque<>();
        int maxLevel = maxLevel(x);
        deq.addLast(new Tuple(x, (int)Math.pow(2, maxLevel)));
        levelOrderTraversal(deq, auxDeq, maxLevel);
    }

    public void levelOrderTraversal(Deque<Tuple> q, Deque<Tuple> auxQ, int level){
        if(q.isEmpty()) return;
        int gap = 0;
        int count = 0;
        while(!q.isEmpty()){
            Tuple tup = q.removeFirst();
            printHorizontalSpaces(tup.hd - gap - count);
            gap = tup.hd;
            System.out.print(tup.node.key);
            if(count == 0) count++;
            if(tup.node.left != null) auxQ.addLast(new Tuple(tup.node.left, tup.hd - (int)Math.pow(2, level-1)));
            if(tup.node.right != null) auxQ.addLast(new Tuple(tup.node.right, tup.hd + (int)Math.pow(2, level-1)));
        }
        printVerticalSpaces(level+1);
        levelOrderTraversal(auxQ, q, --level);
    }

    public boolean checkPathSum(Node x, int sum){
        if(x == null) return false;
        int currentCount = sum - (Integer)x.key;
        if(x.left == null && x.right == null){
            if(currentCount == 0) return true;
            else return false;
        }
        if(checkPathSum(x.left, currentCount)) return true;
        if(checkPathSum(x.right, currentCount)) return true;
        return false;
    }

    public void inorder_R(Node x){
        if(x == null) return;
        inorder_R(x.left);
        System.out.print(x.key + " ");
        inorder_R(x.right);
    }

    public void inorder_iter(Node x){
        Stack<Node> stack = new Stack<Node>();
        Node curr = x;
        while(!stack.isEmpty() || null != curr){
            if(null != curr){
                stack.push(curr);
                curr = curr.left;
            }
            else{
                curr = stack.pop();
                System.out.print(curr.key + " ");
                curr = curr.right;
            }
        }
    }

    public void preorder_R(Node x){
        //levelOrderTraversal(x);
        if(x == null) return;
        System.out.print(x.key + " ");
        preorder_R(x.left);
        preorder_R(x.right);
    }

    public void preorder_iter(Node x){
        Node curr = x;
        Stack<Node> stack = new Stack<Node>();
        while(!stack.isEmpty() || null != curr){
            if(null != curr){
                System.out.print(curr.key + " ");
                stack.push(curr);
                curr = curr.left;
            }
            else{
                curr = stack.pop();
                curr = curr.right;
            }
        }
    }

    public void preorder_I(Node<Integer, Integer> root){
        System.out.println();
        System.out.println("************PreOrder_Iteratively**********");
        levelOrderTraversal(root);
        Stack<Node> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){
            Node x = stack.pop();
            System.out.print(x.key + " ");
            if(x.right != null) stack.push(x.right);
            if(x.left != null) stack.push(x.left);
        }
    }

    public void postorder_R(Node x){
        if(x == null) return;
        postorder_R(x.left);
        postorder_R(x.right);
        System.out.print(x.key + " ");
    }

    public void postorder_I(Node<Integer, Integer> root){
        System.out.println();
        System.out.println("************PostOrder_Iteratively**********");
        Stack<Node> stack = new Stack<>();
        if(root == null) return;
        stack.push(root);
        Node prev = null;
        while(!stack.isEmpty()){
            Node curr = stack.peek();
            if(prev == null || prev.left == curr || prev.right == curr){
                if(curr.left != null){
                    stack.push(curr.left);
                }
                else if(curr.right != null){
                    stack.push(curr.right);
                }
                else{
                    System.out.print(stack.pop().key + " ");
                }
            }
            else if(curr.left == prev){
                if(curr.right != null){
                    stack.push(curr.right);
                }
                else{
                    System.out.print(stack.pop().key + " ");
                }
            }
            else if(curr.right == prev){
                System.out.print(stack.pop().key + " ");
            }

            prev = curr;
        }
    }

    public void binaryTreeToBST(Node root){
        levelOrderTraversal(root);
        List treeNodes = inorder_X(root);
        Collections.sort(treeNodes);
        inorder_Y(root, treeNodes);
        assert isBST(root);
        levelOrderTraversal(root);
    }

    public void inorder_Y(Node x, List treeNodes){
        if(x == null) return;
        inorder_Y(x.left, treeNodes);
        x.key = (Key)treeNodes.remove(0);
        inorder_Y(x.right, treeNodes);
    }

    public List inorder_X(Node root){
        List<Key> treeNodes = new ArrayList();
        inorder_X(root, treeNodes);
        return treeNodes;
    }

    public void inorder_X(Node x, List list){
        if(x == null) return;
        inorder_X(x.left, list);
        list.add(x.key);
        inorder_X(x.right, list);
    }

    public boolean isBST(Node<Integer, Integer> root){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isBST(Node<Integer, Integer> x, int MIN, int MAX){
        if(x == null) return true;
        if(x.key > MIN && x.key < MAX && isBST(x.left, MIN, Math.min(x.key, MAX)) && isBST(x.right, Math.max(x.key, MIN), MAX)) return true;
        else return false;
    }

    public void sortedArrayToBST(int[] arr){
        assert isOrdered(arr);
        int N = arr.length;
        Node root = sortedArrayToBST(arr, 0, N-1);
        assert isBST(root);
        levelOrderTraversal(root);
    }

    public Node<Integer, Integer> sortedArrayToBST(int[] inp, int low, int high){
        if(low > high) return null;
        int mid = low + (high-low)/2;
        Node x = new Node(inp[mid], 0, 0);
        x.left = sortedArrayToBST(inp, low, mid-1);
        x.right = sortedArrayToBST(inp, mid+1, high);
        return x;
    }

    public static boolean isOrdered(int[] arr){
        int N = arr.length;
        int lastElem = Integer.MIN_VALUE;
        for(int i=0; i<N; i++){
            if(lastElem > arr[i]) return false;
            lastElem = arr[i];
        }
        return true;
    }

    public static boolean isBinaryTreeBalanced(Node root){
        if(root == null) return true;
        if(maxLevel(root) - minLevel(root) > 0) return false;
        else return true;
    }

    public static int minLevel(Node x){
        if(x == null) return 0;
        return Math.min(minLevel(x.left), minLevel(x.right)) + 1;
    }

    public static void main(String[] args){
        BT<Integer, Integer> bt = new BT<>(8, 0);
        bt.addLeft(8, 12, 0);
        bt.addRight(8, 10, 0);
        bt.addLeft(12, 18, 0);
        bt.addRight(12, 13, 0);
        bt.addLeft(10, 11, 0);
        bt.addRight(10, 21, 0);
        bt.addLeft(18, 2, 0);
        bt.addRight(18, 5, 0);
        bt.addLeft(13, 3, 0);
        bt.addRight(13, 15, 0);
        bt.addLeft(11, 34, 0);
        bt.addRight(11, 35, 0);
        bt.addLeft(2, 1, 0);
        bt.addLeft(21, -39, 0);
        TreeTraversal tt = new TreeTraversal();
        tt.levelOrderTraversal(bt.get(8));
        //System.out.println("result="+tt.checkPathSum(bt.get(8), 101));
        int[] inp = {1, 2, 4, 9, 12, 16, 17, 25, 38};
        //tt.sortedArrayToBST(inp);
        //tt.preorder_I(bt.get(8));
        //tt.postorder_I(bt.get(8));
        System.out.println("Inorder iterative");
        tt.inorder_iter(bt.get(8));
        System.out.println();
        System.out.println("Preorder recursive");
        tt.preorder_R(bt.get(8));
        System.out.println();
        System.out.println("Preorder iterative");
        tt.preorder_iter(bt.get(8));
        System.out.println("Postorder iterative");
        tt.postorder_R(bt.get(8));
        //tt.binaryTreeToBST(bt.get(8));
    }

}
