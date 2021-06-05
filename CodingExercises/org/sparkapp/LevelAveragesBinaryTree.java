/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sparkapp;

/**
 *
 * @author rowtam
 */
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

// formerly called "rank"
class LevelAveragesBinaryTree {

    class Node {

        int data;
        Node left;
        Node right;

        Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Add any helper functions you may need here
    void getAverageBFS(Node node, ArrayList<Double> averageList) {
        Queue<Node> nodeQueue= new LinkedList<>();
        nodeQueue.offer(node);
        while(!nodeQueue.isEmpty())
        {
            int childrenCount = 0;
            int sum = 0;
            Queue<Node> childrenQueue = new LinkedList<>();
            while(!nodeQueue.isEmpty())
            {
                Node n = nodeQueue.remove();
                sum += n.data;
                childrenCount += 1;
                if(n.left != null)
                {
                    childrenQueue.offer(n.left);
                }
                if(n.right != null)
                {
                    childrenQueue.offer(n.right);
                }       
            }
            nodeQueue = childrenQueue;
            averageList.add(sum*1.0/childrenCount);
            
        }
        
    }
    
    void getAverageDFSHelper(Node n, ArrayList<Integer> sumList, ArrayList<Integer> countList, int level)
    {
        if(n == null)
            return;
        else
        {
            if(level >= sumList.size())
            {
                sumList.add(0);
            }
            if(level >= countList.size())
            {
                countList.add(0);
            }
            sumList.set(level, sumList.get(level) + n.data);
            countList.set(level, countList.get(level) + 1);
            if(n.left != null)
            {
                getAverageDFSHelper(n.left, sumList, countList, level+1);
            }
            if(n.right != null)
            {
                getAverageDFSHelper(n.right, sumList, countList, level+1);
            }
        }
    }
    
    void getAverageDFS(Node n, ArrayList<Double> averageList)
    {
        ArrayList<Integer> sumList = new ArrayList<>();
        ArrayList<Integer> countList = new ArrayList<>();
        this.getAverageDFSHelper(n, sumList, countList, 0);
        for(int i = 0; i < sumList.size(); i++)
        {
            averageList.add(sumList.get(i)*1.0/countList.get(i));
        }
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() throws IOException {

        Node root_1 = new Node(4);
        root_1.left = new Node(7);
        root_1.right = new Node(9);
        root_1.left.left = new Node(10);
        root_1.left.right = new Node(2);
        root_1.right.right = new Node(6);
        root_1.left.right.right = new Node(6);
        root_1.left.right.right.left = new Node(2);
        
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(4.0);
        expected.add(8.0);
        expected.add(6.0);
        expected.add(6.0);
        expected.add(2.0);
        ArrayList<Double> actualBFS = new ArrayList<>();
        ArrayList<Double> actualDFS = new ArrayList<>();
        this.getAverageBFS(root_1, actualBFS);
        System.out.println("Expected: "+expected+" Actual: "+actualBFS);
        this.getAverageDFS(root_1, actualDFS);
        System.out.println("Expected: "+expected+" Actual: "+actualDFS);
       
        
        // Add your own test cases here
    }

    public static void main(String[] args) throws IOException {
        new LevelAveragesBinaryTree().run();
    }
}
