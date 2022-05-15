package com.example.demo.tree.ecs;


import java.util.Stack;

/**
 * Tree
 *
 * @author zouzhihao
 * @date 2021/6/21
 */
public class Tree {

    TreeNode root;

    public static void main(String[] args) {
        Tree tree = new Tree();
        for (int i = 0; i < 10; i++) {
            int x =( int)(Math.random()*100);
            TreeNode add = new TreeNode();
            add.val = x;
            tree.make(add);
        }
        System.out.println(tree.root);
    }

    public void make(TreeNode add){
        if(root == null){
            root = new TreeNode();
            root.val = 50;
        }
        if(add.val > root.val){
            if(root.right == null){
                root.right = add;
                return;
            }
            TreeNode right = root.right;
            while (right != null){
                //右
                if(add.val > right.val && right.right != null){
                    right = right.right;
                    continue;
                }else if(add.val > right.val && right.right == null){
                    right.right = add;
                    return ;
                }
                //左
                if(add.val < right.val && right.left != null){
                    right = right.left;
                    continue;
                }else if(add.val < right.val && right.left == null){
                    right.left = add;
                    return ;
                }
            }
        }
//        else if(add.val < root.val){
//            if(root.left == null){
//                root.left = add;
//            }
//            TreeNode left = root.left;
//            while (left != null){
//                //右
//                if(left.left != null){
//                    left = left.left;
//                    continue;
//                }else if(left.left == null){
//                    left.left = add;
//                    return ;
//                }
//            }
//        }
    }


}class TreeNode {
    public TreeNode left;

    public TreeNode right;

    public int val;

}
