package com.home.latest.tree;

import com.home.latest.ds.Queue;
import com.home.latest.ds.QueueImpl;
import com.home.latest.ds.Stack;
import com.home.latest.ds.StackImpl;

/**
 * Created by pranabdas on 8/2/16.
 */
public class TreePbms {

    public static void main(String[] args) {
        System.out.println(findNumber(0, 33));
    }

    class BuildBinaryTree {
        private int preIdx;
        private int postIdx;
        private Node pre_root;
        private Node post_root;

        /**
         * Reconstruct tree from in-order and pre-order traversal
         */
        public void reconstructTree_In_Pre(Node[] inOrder, Node[] preOrder) {
            if (inOrder == null || preOrder == null || inOrder.length == 0 || preOrder.length == 0 || inOrder.length != preOrder.length)
                return;
            pre_root = reconstructTree_In_Pre(inOrder, preOrder, 0, inOrder.length-1);
        }

        public Node reconstructTree_In_Pre(Node[] inOrder, Node[] preOrder, int inStart, int inEnd) {
            if(inEnd > inStart) return null;

            Node curr_Node = preOrder[preIdx++];

            if(inStart == inEnd) return curr_Node;

            int inIdx = in_Pre_OrderIndex(inOrder, curr_Node);
            curr_Node.left = reconstructTree_In_Pre(inOrder, preOrder, inStart, inIdx-1);
            curr_Node.right = reconstructTree_In_Pre(inOrder, preOrder, inIdx+1, inEnd);
            return curr_Node;
        }

        /**
         * Reconstruct tree from in-order and post-order traversal
         */
        public void reconstructTree_In_Post(Node[] inOrder, Node[] postOrder) {
            if (inOrder == null || postOrder == null || inOrder.length == 0 || postOrder.length == 0 || inOrder.length != postOrder.length)
                return;
            post_root = reconstructTree_In_Post(inOrder, postOrder, 0, inOrder.length-1);
        }

        public Node reconstructTree_In_Post(Node[] inOrder, Node[] postOrder, int inStart, int inEnd) {
            if(inEnd > inStart) return null;

            Node curr_Node = postOrder[postIdx++];

            if(inStart == inEnd) return curr_Node;

            int inIdx = in_Post_OrderIndex(inOrder, curr_Node);
            curr_Node.left = reconstructTree_In_Post(inOrder, postOrder, inStart, inIdx-1);
            curr_Node.right = reconstructTree_In_Post(inOrder, postOrder, inIdx+1, inEnd);
            return curr_Node;
        }

        public int in_Pre_OrderIndex(Node[] inOrder, Node preOrderNode){
            for(int i=0; i<inOrder.length; i++){
                if(inOrder[i] == preOrderNode) return i;
            }
            System.out.println("PreOrder key not found! Error condition!");
            return -1;
        }

        public int in_Post_OrderIndex(Node[] inOrder, Node postOrderNode){
            for(int i=0; i<inOrder.length; i++){
                if(inOrder[i] == postOrderNode) return i;
            }
            System.out.println("PostOrder key not found! Error condition!");
            return -1;
        }
    }

    public static void printLevelOrderTree(Node root) {
        if (root == null) return;
        Queue<Node> queue = new QueueImpl();
        queue.enqueue(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node curr = queue.dequeue();
            sb.append(curr.toString());
            if (curr.left != null) queue.enqueue(curr.left);
            if (curr.right != null) queue.enqueue(curr.right);
        }
        System.out.println(sb.toString());
    }

    /**
     * http://www.geeksforgeeks.org/find-a-number-in-minimum-steps/
     */
    public static int findNumber(int startingNum, int target){
        int inc = 1;
        int levelCount = 1;
        int count = 1;
        Queue<Integer> q = new QueueImpl<>();
        q.enqueue(startingNum);
        while(!q.isEmpty()){
            count--;
            int curr = q.dequeue();
            if(curr == target) return inc-1;
            q.enqueue(curr - inc);
            q.enqueue(curr + inc);
            if(count == 0) {
                levelCount *=2;
                count = levelCount;
                inc++;
            }
        }
        return -1;
    }

    public static int evaluatingBinaryExpressionTree(Node root){
        Stack<String> stack = new StackImpl<>();
        return -1;
    }


}
