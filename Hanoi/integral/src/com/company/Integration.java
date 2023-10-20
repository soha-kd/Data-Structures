package com.company;

public class Integration {
    // Class result: calculates the integral and displays the result.
    public static void main(String args[]){
        // Call class function
        Function function;
        function = new Function();

        // ENTER the desired values of a, b and n !!!
        double a = 2 ;
        double b = 10 ;
        int n = 3 ;
        // Applies simpson method to function
        double result = function.IntSimpson(a,b,n);

        // Show results
        System.out.println("Integral is: " + result);
    }
}
