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

class QueueRemovals {

    // Add any helper functions you may need here  
    private class Pair implements Comparable<Pair> {

        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int compareTo(Pair o) {
            return o.value - this.value;
        }

        @Override
        public String toString() {
            return "[" + value + "," + index + "]";
        }
    }

    int[] findPositions(int[] arr, int x) {
        // Write your code here
        Queue<Pair> q = new LinkedList<>();
        ArrayList<Integer> output = new ArrayList<>();
        ArrayList<Pair> originalArray = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            originalArray.add(i, new Pair(arr[i], i));
        }
        int iterations = 0;
        while (iterations < x) {
            // load it into the queue
            q = new LinkedList<>();
            for (int i = 0; i < originalArray.size(); i++) {
                q.offer(originalArray.get(i));
            }

            ArrayList<Pair> al = new ArrayList<Pair>();
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            int popLength = Math.min(originalArray.size(), x);
            ArrayList<Integer> ValuesVisited = new ArrayList<>();
            for (int i = 0; i < popLength; i++) {
                Pair value = q.poll();
                // only take the earliest value of any duplicates
                if (!ValuesVisited.contains(value.value)) {
                    ValuesVisited.add(value.value);
                    pq.add(value);
                }
                al.add(value);

            }

            int greatestValue = pq.peek().value;
            int greatestValueIndex = pq.peek().index;
            int found = -1;
            boolean isFound = false;
            for (int i = 0; i < al.size() && !isFound; i++) {
                if (al.get(i).value == greatestValue
                        && al.get(i).index == greatestValueIndex) {
                    isFound = true;
                    found = al.get(i).index + 1;
                    output.add(found);
                    al.remove(i);
                }
            }

            for (int i = 0; i < al.size(); i++) {
                if (al.get(i).value > 0) {
                    Pair p = al.get(i);
                    p.value = p.value - 1;
                    al.set(i, p);
                }
                q.offer(al.get(i));
            }
            ArrayList<Pair> arrayList = new ArrayList<>();
            while (q.peek() != null) {
                arrayList.add(q.poll());
            }

            originalArray = arrayList;
            System.out.println(output);
            System.out.println(originalArray);
            //System.out.println(al);
            System.out.println(Arrays.toString(arr));
            iterations++;
        }
        int[] outputArray = new int[output.size()];
        for (int i = 0; i < outputArray.length; i++) {
            outputArray[i] = output.get(i);
        }

        return outputArray;
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
        int n_1 = 6;
        int x_1 = 5;
        int[] arr_1 = {1, 2, 2, 3, 4, 5};
        int[] expected_1 = {5, 6, 4, 1, 2};
        int[] output_1 = findPositions(arr_1, x_1);
        check(expected_1, output_1);

        int n_2 = 13;
        int x_2 = 4;
        int[] arr_2 = {2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4};
        int[] expected_2 = {2, 5, 10, 13};
        int[] output_2 = findPositions(arr_2, x_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new QueueRemovals().run();
    }
}
