package com.home.latest.tree;


import java.util.*;

/**
 * Created by pranabdas on 8/21/16.
 */
public class PracticePbms{

    // lca in a bst
    public <Key extends Comparable<Key>,Value> Node lowestCommonAncestor_BST(Node<Key, Value> root, Key num1, Key num2){
        Key key = root.key;
        if(null == root) return null;
        int cmp1 = root.key.compareTo(num1);
        int cmp2 = root.key.compareTo(num2);
        int sum = cmp1 + cmp2;
        if(sum < 2 || sum > -2 ){
            return root;
        }
        else if(sum == -2){
            return lowestCommonAncestor_BST(root.right, num1, num2);
        }
        else{
            return lowestCommonAncestor_BST(root.left, num1, num2);
        }
    }

    // lca in a binary tree
    // assumption: both the keys are present in the tree
    public static Integer lowestCommonAncestor_BT(Node<Integer, Integer> root, int num1, int num2 ){
        if(null == root) return null;
        if(root.key == num1 || root.key == num2) return root.key;
        Integer leftSubTree = lowestCommonAncestor_BT(root.left, num1, num2);
        Integer rightSubTree = lowestCommonAncestor_BT(root.right, num1, num2);
        if(leftSubTree != null && rightSubTree != null){
            return root.key;
        }
        else if(leftSubTree == null && rightSubTree == null){
            return null;
        }
        else if(leftSubTree != null){
            return leftSubTree;
        }
        else return rightSubTree;
    }

    /**
     * Given a binary tree, return all root-to-leaf paths.

     For example, given the following binary tree:

       1
     /   \
     2     3
     \
     5
     All root-to-leaf paths are:

     ["1->2->5", "1->3"]
     * @param root
     */
    public static List<String> rootLeafPaths(Node<Integer, Integer> root){
        if(root == null) return null;
        List<String> result = new ArrayList();
        StringBuilder sb = new StringBuilder();
        return rootLeafPaths(root, sb, result);
    }

    public static List<String> rootLeafPaths(Node<Integer, Integer> x, StringBuilder sb, List<String> result){
        if(x == null) return result;
        if(x.left == null && x.right == null){
            result.add(sb.toString() + x.key);
            return result;
        }
        if(x != null){
            sb.append(x.key).append("->");
        }
        result = rootLeafPaths(x.left, sb, result);
        result = rootLeafPaths(x.right, sb, result);
        return result;
    }

    public static boolean sameBinaryTree(Node<Integer, Integer> tree1, Node<Integer, Integer> tree2){
        if(tree1 == null && tree2 == null) return true;
        if((tree1 == null && tree2 != null) || (tree1 != null && tree2 == null)) return false;
        if(tree1.key != tree2.key) return false;
        boolean result = true;
        result &= sameBinaryTree(tree1.left, tree2.left);
        if(!result) return result;
        result &= sameBinaryTree(tree1.right, tree2.right);
        return result;
    }

    public static List<Node> spiralTraversal(Node<Integer, Integer> root){
        if(root == null) return null;
        Stack<Node> s1 = new Stack();
        Stack<Node> s2 = new Stack();
        List<Node> result = new ArrayList();
        s1.push(root);
        Stack<Node> s3 = s1;
        while(!s3.isEmpty()){
            Node curr = s3.pop();
            result.add(curr);
            if(s3 == s1){
                if(curr.left != null) s2.push(curr.left);
                if(curr.right != null) s2.push(curr.right);
            }
            else if(s3 == s2){
                if(curr.right != null) s1.push(curr.right);
                if(curr.left != null) s1.push(curr.left);
            }
            if(s3.isEmpty()) {
                if(s3 == s1) s3 = s2;
                else if(s3 == s2) s3 = s1;
            }
        }
        return result;
    }

    public static Deque<Node> reverseLevelOrderTraversal(Node<Integer, Integer> root){
        if(root == null) return null;
        List<Node> result = new ArrayList();
        Deque<Node> stack = new ArrayDeque();
        Deque<Node> queue = new ArrayDeque();
        queue.addFirst(root);
        while(!queue.isEmpty()){
            Node curr = queue.removeLast();
            if(curr != null) stack.addFirst(curr);
            if(curr.right != null) queue.addFirst(curr.right);
            if(curr.left != null) queue.addFirst(curr.left);
        }
        return stack;
    }

    public static void main(String[] args){
        BT<Integer, Integer> bt = new BT(3, null);
        bt.addLeft(3, 6, null);
        bt.addLeft(6, 2, null);
        bt.addRight(6, 11, null);
        bt.addLeft(11, 9, null);
        bt.addRight(11, 5, null);
        bt.addRight(3, 8, null);
        bt.addRight(8, 13, null);
        bt.addLeft(13, 7, null);
        TreeTraversal tt = new TreeTraversal();
        tt.levelOrderTraversal(bt.get(3));
        System.out.println(lowestCommonAncestor_BT(bt.get(3), 2, 5));
        System.out.println(lowestCommonAncestor_BT(bt.get(3), 11, 8));
        System.out.println(lowestCommonAncestor_BT(bt.get(3), 8, 7));

        BT<Integer, Integer> bt1 = new BT(11, null);
        bt1.addLeft(11, 15, null);
        bt1.addRight(11, 18, null);
        bt1.addLeft(18, 21, null);

        BT<Integer, Integer> bt2 = new BT(11, null);
        bt2.addLeft(11, 15, null);
        bt2.addRight(11, 18, null);
        bt2.addLeft(18, 21, null);

        System.out.println(sameBinaryTree(bt1.get(11), bt2.get(11)));

        List<Node> result = spiralTraversal(bt.get(3));
        StringBuilder sb = new StringBuilder();
        for(Node node : result){
            sb.append(node.key).append( " ");
        }
        System.out.println(sb.toString());

        Deque<Node> result2 = reverseLevelOrderTraversal(bt.get(3));
        StringBuilder sb2 = new StringBuilder();
        for(Node node : result2){
            sb2.append(node.key).append( " ");
        }
        System.out.println(sb2.toString());

        for(String path : rootLeafPaths(bt.get(3))){
            System.out.println(path);
        }
    }

}

