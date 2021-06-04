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

class MatchingPairs {

    // Add any helper functions you may need here
    int findMatchingPairs(char[] s, char[] t) {
        int matchingPairs = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == t[i]) {
                matchingPairs++;
            }
        }
        return matchingPairs;
    }

    int matchingPairs(String s, String t) {
        // Write your code here
        // all possible swaps are
        // brute force
        char[] s_char = s.toCharArray();
        char[] t_char = t.toCharArray();
        int maxMatchingPairs = 0;
        for (int i = 0; i < s_char.length; i++) {
            // lazy, just reset
            s_char = s.toCharArray();
            t_char = t.toCharArray();
            for (int j = i + 1; j < s_char.length; j++) {
                s_char = s.toCharArray();
                t_char = t.toCharArray();
                char temp = s_char[i];
                s_char[i] = s_char[j];
                s_char[j] = temp;

                int pairsFound = findMatchingPairs(s_char, t_char);
                System.out.println("Swapping: " + i + "," + j);
                System.out.println("S: " + new String(s_char));
                System.out.println("T: " + new String(t_char));
                System.out.println("Pairs Found: " + pairsFound);
                if (pairsFound > maxMatchingPairs) {
                    maxMatchingPairs = pairsFound;
                }
            }
        }
        return maxMatchingPairs;
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
        String s_1 = "abcde";
        String t_1 = "adcbe";
        int expected_1 = 5;
        int output_1 = matchingPairs(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String t_2 = "abcd";
        int expected_2 = 2;
        int output_2 = matchingPairs(s_2, t_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new MatchingPairs().run();
    }
}
