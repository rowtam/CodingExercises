/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rowtam
 */
package org.sparkapp;

class RotationalCipher {

    // Add any helper functions you may need here
    char rotateCharacter(char x, int rotationFactor) {
        char newCharacter = x;

        if ((int) x >= (int) 'A' && (int) x <= (int) 'Z') {
            int characterIndex = (int) x - (int) 'A';
            int n = ((int) 'A') + ((characterIndex + rotationFactor) % 26);
            newCharacter = (char) n;
        }
        if ((int) x >= (int) 'a' && (int) x <= (int) 'z') {
            int characterIndex = (int) x - (int) 'a';
            int n = ((int) 'a') + ((characterIndex + rotationFactor) % 26);
            newCharacter = (char) n;
        }
        if ((int) x >= (int) '0' && (int) x <= (int) '9') {
            int characterIndex = (int) x - (int) '0';
            int n = ((int) '0') + ((characterIndex + rotationFactor) % 10);
            newCharacter = (char) n;
        }
        return newCharacter;
    }

    String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        char[] temp = input.toCharArray();
        int length = temp.length;
        char[] newChar = new char[length];
        for (int i = 0; i < length; i++) {
            char n = rotateCharacter(temp[i], rotationFactor);
            newChar[i] = n;
        }

        String s = new String(newChar);
        return s;
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
        String input_1 = "All-convoYs-9-be:Alert1.";
        int rotationFactor_1 = 4;
        String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
        String output_1 = rotationalCipher(input_1, rotationFactor_1);
        check(expected_1, output_1);

        String input_2 = "abcdZXYzxy-999.@";
        int rotationFactor_2 = 200;
        String expected_2 = "stuvRPQrpq-999.@";
        String output_2 = rotationalCipher(input_2, rotationFactor_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new RotationalCipher().run();
    }
}
