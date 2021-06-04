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

class ReverseToMakeEqual {

    // Add any helper functions you may need here
    boolean areTheyEqual(int[] array_a, int[] array_b) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        if (array_a.length != array_b.length) {
            return false;
        }
        for (int i = 0; i < array_a.length; i++) {
            if (hashMap.containsKey(array_a[i])) {
                int value = hashMap.get(array_a[i]);
                hashMap.put(array_a[i], value + 1);
            } else {
                hashMap.put(array_a[i], 1);
            }
        }

        HashMap<Integer, Integer> secondHashMap = new HashMap<>();

        for (int i = 0; i < array_b.length; i++) {
            if (hashMap.getOrDefault(array_b[i], -1) == -1) {
                return false;
            }
            if (secondHashMap.containsKey(array_b[i])) {
                int value = secondHashMap.get(array_b[i]);
                secondHashMap.put(array_b[i], value + 1);
            } else {
                secondHashMap.put(array_b[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> x : hashMap.entrySet()) {
            if (x.getValue() != secondHashMap.get(x.getKey())) {
                return false;
            }
        }

        return true;

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

    public void run() {
        int[] array_a_1 = {1, 2, 3, 4};
        int[] array_b_1 = {1, 4, 3, 2};
        boolean expected_1 = true;
        boolean output_1 = areTheyEqual(array_a_1, array_b_1);
        check(expected_1, output_1);

        int[] array_a_2 = {1, 2, 3, 4};
        int[] array_b_2 = {1, 4, 3, 3};
        boolean expected_2 = false;
        boolean output_2 = areTheyEqual(array_a_2, array_b_2);
        check(expected_2, output_2);
        // Add your own test cases here

    }

    public static void main(String[] args) {
        new ReverseToMakeEqual().run();
    }
}
