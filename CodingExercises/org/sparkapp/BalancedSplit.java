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

class BalancedSplit {

    // Add any helper functions you may need here
    int sumElements(List<Integer> arr) {
        System.out.println("sumElements:" + arr);
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return sum;
    }

    boolean balancedSplitExists(int[] arr) {
        // Write your code here
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
        int pivot = 0;
        for (pivot = 1; pivot < list.size() - 1; pivot++) {
            // sorted but verify the numbers at the pviot points do not match or the other array is not less.
            if (list.get(pivot) != list.get(pivot - 1) && sumElements(list.subList(0, pivot)) == sumElements(list.subList(pivot, list.size()))) {
                return true;
            }
        }
        return false;

    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        int arr_1[] = {2, 1, 2, 5};
        boolean expected_1 = true;
        boolean output_1 = balancedSplitExists(arr_1);
        check(expected_1, output_1);

        int arr_2[] = {3, 6, 3, 4, 4};
        boolean expected_2 = false;
        boolean output_2 = balancedSplitExists(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new BalancedSplit().run();
    }
}
