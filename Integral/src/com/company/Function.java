package com.company;

public class Function {
    double f (double x) {
        return Math.cos(x);
    }

    // Simpson's method for integral calculus
    // a = lower bound
    // b = upper bound of integration
    // n = number of passes (higher = less margin of error, but takes longer)
    double IntSimpson(double a, double b,int n){
        int i,z;
        double h,s;

        n=n+n;
        s = f(a)*f(b);
        h = (b-a)/n;
        z = 4;

        for(i = 1; i<n; i++){
            s = s + z * f(a+i*h);
            z = 6 - z;
        }
        return (s * h)/3;
    }
}
