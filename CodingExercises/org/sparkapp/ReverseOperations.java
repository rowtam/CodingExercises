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
class ReverseOperations {

    class Node {

        int data;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }
    }

    // Add any helper functions you may need here
    Node reverse(Node head) {
        // Write your code here
        // Check for even numbers.   
        // Write your code here
        return reverse(head, null);
    }

//Recursive function to reverse the consecutive even nodes in the linked list
    private Node reverse(Node head, Node prev) {
        //Base case
        if (head == null) {
            return null;
        }

        Node temp;
        Node curr;
        curr = head;

// Reverses node until we reach odd node or all linked list is reversed
        while (curr != null && curr.data % 2 == 0) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        // if elements are reversed then we need to change the head
        if (curr != head) {
            head.next = curr;
            curr = reverse(curr, null);
            return prev;
        } else {
// otherwise we just move over the odd elements
            head.next = reverse(head.next, head);
            return head;
        }

    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void printLinkedList(Node head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
            if (head != null) {
                System.out.print(" ");
            }
        }
        System.out.print("]");
    }

    void check(Node expectedHead, Node outputHead) {
        boolean result = true;
        Node tempExpectedHead = expectedHead;
        Node tempOutputHead = outputHead;
        while (expectedHead != null && outputHead != null) {
            result &= (expectedHead.data == outputHead.data);
            expectedHead = expectedHead.next;
            outputHead = outputHead.next;
        }
        if (!(expectedHead == null && outputHead == null)) {
            result = false;
        }

        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printLinkedList(tempExpectedHead);
            System.out.print(" Your output: ");
            printLinkedList(tempOutputHead);
            System.out.println();
        }
        test_case_number++;
    }

    Node createLinkedList(int[] arr) {
        Node head = null;
        Node tempHead = head;
        for (int v : arr) {
            if (head == null) {
                head = new Node(v);
                tempHead = head;
            } else {
                head.next = new Node(v);
                head = head.next;
            }
        }
        return tempHead;
    }

    public void run() {

        int[] arr_1 = {1, 2, 8, 9, 12, 16};
        int[] expected1 = {1, 8, 2, 9, 16, 12};
        Node head_1 = createLinkedList(arr_1);
        Node expected_1 = createLinkedList(expected1);
        Node output_1 = reverse(head_1);
        check(expected_1, output_1);

        int[] arr_2 = {2, 18, 24, 3, 5, 7, 9, 6, 12};
        int[] expected2 = {24, 18, 2, 3, 5, 7, 9, 12, 6};
        Node head_2 = createLinkedList(arr_2);
        Node expected_2 = createLinkedList(expected2);
        Node output_2 = reverse(head_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new ReverseOperations().run();
    }
}
