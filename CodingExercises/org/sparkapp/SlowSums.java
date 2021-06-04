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
import java.util.*;
// Add any extra import statements you may need here
import java.util.Collections;

class SlowSums {

    // Add any helper functions you may need here
    int getTotalTime(int[] arr) {
        // Write your code here
        int penalty = 0;
        LinkedList<Integer> ll = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            ll.add(arr[i]);
        }
        ll.sort(Collections.reverseOrder());
        while (ll.size() >= 2) {
            int x = ll.pollFirst();
            int y = ll.pollFirst();
            penalty += x + y;
            ll.addFirst(x + y);
        }
        return penalty;
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

    public void run() {
        int[] arr_1 = {4, 2, 1, 3};
        int expected_1 = 26;
        int output_1 = getTotalTime(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {2, 3, 9, 8, 4};
        int expected_2 = 88;
        int output_2 = getTotalTime(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new SlowSums().run();
    }
}
