package cn.bj.brook.algorithm.tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRecoverBST {
    private RecoverBST tree;

    public  TestRecoverBST(){
        tree = new RecoverBST();
    }

    private boolean delayed = true;

    @After
    public void tearDown(){
        if(delayed) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void setUp(){
        delayed = true;
    }

    @Test
    public void test1(){
        TreeNode root = new TreeNode(1);
        tree.scanTreeWithMultiThread(root);
    }

    @Test
    public void test1_1(){
        TreeNode root = new TreeNode(1);
        ExecutorService es = Executors.newFixedThreadPool(1);
        tree.scanTreeWithExecutors(es, root);
    }

    @Test
    public void test1_2(){
        TreeNode root = new TreeNode(1);
        Stack<Snake> stack = new Stack<>();
        this.delayed = false;
        tree.scanTree(stack, root);
    }

    @Test
    public void test2(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        tree.scanTreeWithMultiThread(root);
    }

    @Test
    public void test2_1(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        ExecutorService es = Executors.newFixedThreadPool(1);
        tree.scanTreeWithExecutors(es, root);
    }

    @Test
    public void test2_2(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        Stack<Snake> stack = new Stack<>();
        this.delayed = false;
        tree.scanTree(stack, root);
    }

    @Test
    public void test3(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        tree.scanTreeWithMultiThread(root);
    }

    @Test
    public void test3_1(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        ExecutorService es = Executors.newFixedThreadPool(1);
        tree.scanTreeWithExecutors(es, root);
    }

    @Test
    public void test4(){
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.right = right;
        tree.scanTreeWithMultiThread(root);
    }

    @Test
    public void test4_1(){
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.right = right;
        ExecutorService es = Executors.newFixedThreadPool(1);
        tree.scanTreeWithExecutors(es, root);
    }

    @Test
    public void test5(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);
        right.right = new TreeNode(6);
        tree.scanTreeWithMultiThread(root);
    }

    @Test
    public void test5_1(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);
        right.right = new TreeNode(6);
        ExecutorService es = Executors.newFixedThreadPool(1);
        tree.scanTreeWithExecutors(es, root);
    }

    @Test
    public void test5_2(){
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);
        right.right = new TreeNode(6);
        Stack<Snake> stack = new Stack<>();
        this.delayed = false;
        tree.scanTree(stack, root);
    }

    @Test
    public void test6(){
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
        right.left = new TreeNode(2);
        tree.scanTreeWithMultiThread(root);
    }

    @Test
    public void test6_1(){
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
        right.left = new TreeNode(2);
        ExecutorService es = Executors.newFixedThreadPool(1);
        tree.scanTreeWithExecutors(es, root);
    }
}
