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

class ElementSwapping {
    // Add any helper functions you may need here
    private static class Pair<T0, T1> {

        T0 object1;
        T1 object2;
        
        public Pair(T0 object1, T1 object2) {
            this.object1 = object1;
            this.object2 = object2;
        }
        
        public T0 getKey()
        {
            return this.object1;
        }
        
        public T1 getValue()
        {
            return this.object2;
        }
    }
    
    int[] findIndexLargestDiff(int[] arr) {
        // sort the list by value, largest to smallest. Grab the indices
        ArrayList<Pair<Integer, Integer>> valueToIndexList = new ArrayList<>();
        if (arr.length < 2) {
            return null;
        }
        for (int i = 0; i < arr.length; i++) {
            valueToIndexList.add(new Pair(arr[i], i));
        }
        // Sort the pair list
        Collections.sort(valueToIndexList, new Comparator<Pair<Integer, Integer>>() {

            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey()); //To change body of generated methods, choose Tools | Templates.
            }
        });
        int[] set = new int[2];
        set[0] = valueToIndexList.get(0).getValue();
        set[1] = valueToIndexList.get(valueToIndexList.size() - 1).getValue();
        return set;
    }

    int[] findLargestDiffPair(int[] arr) {
        int largest = 0;
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i] - arr[i + 1]) > largest) {
                largest = arr[i] - arr[i + 1];
                index1 = i;
                index2 = i + 1;
            }
        }
        return new int[]{index1, index2};
    }

    int findSmallestValueInHighestIndex(int[] arr) {
        int[] newArray = new int[arr.length];
        int largestDiff = 0;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = i - arr[i];
            if (newArray[i] > largestDiff) {
                largestDiff = newArray[i];
                index = i;
            }
        }
        return index;
    }

    private int findMinAtDistanceK(int[] arr, int start, int k) {
        int index = 0, min = Integer.MAX_VALUE;
        // find minimum element at distance k from start
        int limit = Math.min(arr.length, start + k + 1);
        for (int i = start; i < limit; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }
        return index;
    }

    private void swap(int[] arr, int start, int end) {
        //move element at position end to start
        while (end > start) {
            int temp = arr[end];
            arr[end] = arr[end - 1];
            arr[end - 1] = temp;
            end--;
        }
    }

    int[] findMinArray(int[] arr, int k) {
        // Write your code here
        for (int i = 0; i < arr.length && k > 0; i++) {

            // find the minimum index
            int minIndex = findMinAtDistanceK(arr, i, k);
            //if minimum element is already at position i, nothing to do
            if (minIndex == i) {
                continue;
            }
            swap(arr, i, minIndex);
            // we have used up minindex-i swaps
            k -= minIndex - i;
        }
        return arr;
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int n_1 = 3, k_1 = 2;
        int[] arr_1 = {5, 3, 1};
        int[] expected_1 = {1, 5, 3};
        int[] output_1 = findMinArray(arr_1, k_1);
        check(expected_1, output_1);

        int n_2 = 5, k_2 = 3;
        int[] arr_2 = {8, 9, 11, 2, 1};
        int[] expected_2 = {2, 8, 9, 11, 1};
        int[] output_2 = findMinArray(arr_2, k_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new ElementSwapping().run();
    }

}
