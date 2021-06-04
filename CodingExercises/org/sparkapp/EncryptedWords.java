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

class EncryptedWords {

    // Add any helper functions you may need here
    String encryptBaseHelper(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 2) {
            StringBuilder r = new StringBuilder("");
            r.append(s.charAt(0));
            r.append(s.charAt(1));
            return r.toString();
        }
        int index = -1;
        if (s.length() % 2 == 0) {
            index = (s.length() / 2) - 1;
        } else {
            index = (int) Math.floor(s.length() / 2);
        }
        System.out.println("S=" + s + " Index=" + index);
        StringBuilder r = new StringBuilder("");
        r.append(s.charAt(index));
        System.out.println("Recursing over: " + s.substring(0, index));
        r.append(encryptBaseHelper(s.substring(0, index)));
        System.out.println("Recursing over: " + s.substring(index + 1, s.length()));
        r.append(encryptBaseHelper(s.substring(index + 1, s.length())));
        return r.toString();
    }

    String findEncryptedWord(String s) {
        // Write your code here
        return encryptBaseHelper(s);
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        String s_1 = "abc";
        String expected_1 = "bac";
        String output_1 = findEncryptedWord(s_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String expected_2 = "bacd";
        String output_2 = findEncryptedWord(s_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new EncryptedWords().run();
    }
}
