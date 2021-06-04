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
import java.util.*;
// Add any extra import statements you may need here

class MinimumLengthSubstrings {

    // Add any helper functions you may need here
    boolean containsCharSet(String s, String t) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int value = hm.getOrDefault(t.charAt(i), 0);
            hm.put(t.charAt(i), value + 1);
        }

        HashMap<Character, Integer> hm2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int value = hm2.getOrDefault(s.charAt(i), 0);
            hm2.put(s.charAt(i), value + 1);
        }

        for (Map.Entry<Character, Integer> m : hm.entrySet()) {
            if (!hm2.containsKey(m.getKey())) {
                return false;
            }
            if (hm2.get(m.getKey()) < (int) m.getValue()) {
                return false;
            }
        }

        return true;

    }

    int minLengthSubstring(String s, String t) {
        // Write your code here
        int left = 0;
        int right = 0;
        char[] s_char = s.toCharArray();
        char[] t_char = t.toCharArray();

        boolean rightFound = false;
        while (right < s_char.length && !rightFound) {
            if (containsCharSet(s.substring(left, right), t)) {
                rightFound = true;

                System.out.println("Right Found: " + right);
            }
            right++;
        }
        right--;  // decrement to undo the last increment  
        // right is still at array index + 1, one character past the last inclusive character.
        System.out.println("Left: " + left + " Right: " + right);
        if (rightFound == false) {
            return -1;
        }

        boolean noLongerContains = false;
        while (left < right && noLongerContains == false) {
            if (!containsCharSet(s.substring(left, right), t)) {
                noLongerContains = true;
                System.out.println("Left Found: " + left);
            }
            left++;
        }

        // subtract 1 because the index of left failed the contains test and then incremented again, so -2
        left -= 2;
        System.out.println("Left: " + left + " Right: " + right);

        return right - left;

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
        String s_1 = "dcbefebce";
        String t_1 = "fd";
        int expected_1 = 5;
        int output_1 = minLengthSubstring(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf";
        String t_2 = "cbccfafebccdccebdd";
        int expected_2 = -1;
        int output_2 = minLengthSubstring(s_2, t_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) throws IOException {
        new MinimumLengthSubstrings().run();
    }
}
