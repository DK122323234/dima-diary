package com.example.dimadiary;

import java.util.Arrays;

public class ComplianceCheck {
    private int[] b;
    boolean result;


    public boolean check(String date){
        String[] a = date.split("-");
       int ss = Integer.parseInt(a[2]);

        if (a[1].equals("01") || a[1].equals("03") || a[1].equals("05") || a[1].equals("07") || a[1].equals("08") || a[1].equals("10") || a[1].equals("12")){
            if (ss <= 31){
                result = true;
            }
            else {
                result = false;
            }
        }
       else if (a[1].equals("04") || a[1].equals("06") || a[1].equals("09") || a[1].equals("11")){
            if (ss <= 30){
                result = true;
            }
            else {
                result = false;
            }
        }
       else if (a[1].equals("02")){
            if (Integer.parseInt(a[0]) % 4 == 0) {
                if (ss <= 29) {
                    result = true;
                }
                else {
                    result = false;
                }
            }
                else {
                    if (ss <= 28){
                        result = true;
                }
                    else {
                        result = false;
                    }
            }
        }
       else {
                      result = false;
        }
          return result;
    }
}
