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
public class getExcelColumnNumbers {
    
    
    // A = 1
    // A->Z,AA->AZ,BA->BZ,CA->CZ... ZA->ZZ  [0..26][27..52]... 26*26
    // AAB 2+ 1*(26) + 1*(26*26)
    // AZ = 26+26
    // ABC = [[0..25]*1][[0..25]*2][0..2]
    public int getColumnNumber(String s)
    {
        int sum = 0;
        int radix = 0;
        for(int i = s.length()-1; i >= 0; i--)
        {
            sum += Math.pow(26, radix) * (1+ (s.charAt(i) - 'A'));
            radix++;
        }
        return sum;
    }
    
    public String getColumnName(int n)
    {
        StringBuilder sb = new StringBuilder("");
        while(n > 26)
        {
            if(n%26 > 0)
            {
                sb.append((char) ((int)(n%26) + ('A'-1)));
                n = n/26;
            } else
            {
                sb.append((char)('Z'));
                n = n/26;
                n--;
            }            
        }
        sb.append((char)((int)n + ('A'-1)));
        sb.reverse();
        return sb.toString();
    }
    
    public void run()
    {
        System.out.println("AZ 52 : " + getColumnNumber("AZ"));
        System.out.println("ZY 701 : " + getColumnNumber("ZY"));
        System.out.println("FXSHRXW 2147483647 : " + getColumnNumber("FXSHRXW"));
        System.out.println("AZ 52 : " + getColumnName(52));
        System.out.println("ZY 701 : " + getColumnName(701));
        System.out.println("FXSHRXW 2147483647 : " + getColumnName(2147483647));
                
    }
    
    public static void main(String[] args)
    {
        new getExcelColumnNumbers().run();
    }
    
}
