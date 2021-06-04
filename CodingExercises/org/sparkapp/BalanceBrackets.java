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


class BalanceBrackets {
  // Add any helper functions you may need here
  boolean isOpeningBracket(char c)
  {
    return c == '{' || c == '(' || c == '[';
  }
  
  boolean isClosingBracket(char c) {
    return c == '}' || c == ')' || c == ']';
  }
  
  
  
  boolean isBalanced(String s) {
    // Write your code here
    HashMap<Character, Character> hm = new HashMap<>();
    hm.put('{','}');
    hm.put('(',')');
    hm.put('[',']');
    hm.put('}','{');
    hm.put(')','(');
    hm.put(']','[');
    Stack<Character> stackP = new Stack<>();
    
    for(int i = 0; i < s.length(); i++)
    {
      if(isOpeningBracket(s.charAt(i)))
      {
        stackP.push(s.charAt(i));
      } else if(isClosingBracket(s.charAt(i)))
      {
        // check for corresponding bracket
        if(stackP.peek() != hm.get(s.charAt(i)))
        {
          return false;
        } else
        {
          stackP.pop();
        }
      }
    }
    if(stackP.isEmpty())
      return true;
    else
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
    }
    else {
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
    String s_1 = "{[(])}";
    boolean expected_1 = false;
    boolean output_1 = isBalanced(s_1);
    check(expected_1, output_1);

    String s_2 = "{{[[(())]]}}";
    boolean expected_2 = true;
    boolean output_2 = isBalanced(s_2);
    check(expected_2, output_2);

    // Add your own test cases here
        
  }
  
  public static void main(String[] args) {
    new BalanceBrackets().run();
  }
}
