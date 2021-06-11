/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sparkapp;

import java.util.Arrays;

/**
 *
 * @author rowtam
 */
public class isPermutationCheck {
    
    // checks if [array] is a permutation of [0 1 2 3 4... n-1]
    public boolean isPermutation(int[] arr)
    {
        Boolean zeroFound = false;
        for(int i=0; i<arr.length; i++)
        {
            int value = 0;
            // "in-place" method of doing is to verify the value at the index is
            // in-range and then make it negative.  The negative part basically is a marker to say
            // this index was visited.  When we visit a negative number, we have to revert it by taking the absolute
            // value and subtracting 1.  The reasone we have to add 1 is that we cannot 
            // take the negative of 0.
            // if value is negative, it was previously marked
            if(arr[i] < 0)
            {
                value = (0 - arr[i]) - 1;
            } else
            {
                value = arr[i];
            }
            if(value >= 0 && value < arr.length && arr[value] >= 0)
            {
                // can't have a negative 0 marker for having visited a zero
                arr[value] = 0 - (arr[value] + 1);
            } else
            {
                return false;
            }
        }
        return true;
    }
    
    public void run()
    {
        int[] test1 = new int[]{0, 1, 2, 3, 4};
        int[] test2 = new int[]{0, 3, 2, 1, 4};
        int[] test3 = new int[]{6, 1, 1, 3, 1};
        int[] test4 = new int[]{0, 0, 0, 0, 0};
        
        System.out.println(Arrays.toString(test1)+":"+isPermutation(test1));
        System.out.println(Arrays.toString(test2)+":"+isPermutation(test2));
        System.out.println(Arrays.toString(test3)+":"+isPermutation(test3));
        System.out.println(Arrays.toString(test4)+":"+isPermutation(test4));
    }
    
    public static void main(String[] args)
    {
        new isPermutationCheck().run();
    }
}
