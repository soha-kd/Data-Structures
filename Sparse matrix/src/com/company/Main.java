package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter row and column of first matrix : ");
        int r1 = input.nextInt();
        int c1 = input.nextInt();

        int [][]a = new int[r1][c1];
        while(true){
            System.out.println("please enter row of the position you want to add value :");
            int ro1 = input.nextInt();
            System.out.println("please enter column of the position you want to add value  :");
            int co1 = input.nextInt();
            System.out.println("value : ");
            int v1 = input.nextInt();
            a[ro1][co1] = v1;
            System.out.println("do you want to add more data?");
            System.out.println("y/n");
            String ans = scanner.next();
            switch (ans){
                case "y":
                    continue;

                case "n" :
                    break;
                default:
                    System.out.println("wrong value!");
            }
            break;
        }
        for (int i = 0; i < r1 ; i++) {
            for (int j = 0; j < c1; j++) {
                if(a[i][j] == 0)
                a[i][j] = 0;
            }
        }
        System.out.println("please enter row and column of second matrix : ");
        int r2 = input.nextInt();
        int c2 = input.nextInt();

        int [][]b = new int[r2][c2];
        while(true){
            System.out.println("please enter row of the position you want to add value  :");
            int ro2 = input.nextInt();
            System.out.println("please enter column of the position you want to add value  :");
            int co2 = input.nextInt();
            System.out.println("value : ");
            int v2 = input.nextInt();
            b[ro2][co2] = v2;
            System.out.println("do you want to add more data?");
            System.out.println("y/n");
            String ans = scanner.next();
            switch (ans){
                case "y":
                    continue;
                case "n" :
                    break;
                default:
                    System.out.println("wrong value!");
            }
            break;
        }
        for (int i = 0; i < r2 ; i++) {
            for (int j = 0; j < c2; j++) {
                if(b[i][j] == 0)
                b[i][j] = 0;
            }

        }
        System.out.println("first matrix : ");
        for (int i = 0; i < r1 ; i++) {
            for (int j = 0; j < c1; j++) {
                System.out.print(a[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");

        }
        System.out.println("second matrix : ");
        for (int i = 0; i < r2 ; i++) {
            for (int j = 0; j < c2; j++) {
                System.out.print(b[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");

        }
        System.out.println("-----------------sparse a-------------------");
        Sparse s = new Sparse();
        System.out.println(Arrays.deepToString(s.sparse_calc(a, r1, c1)));

        System.out.println("-----------------sparse b-------------------");
        System.out.println(Arrays.deepToString(s.sparse_calc(b, r2, c2)));

        System.out.println("---------------------add--------------------");
        s.add_matrix(a , b , r1 , c1, r2, c2);

        System.out.println("---------------------sub--------------------");
        s.sub_matrix(a , b , r1 , c1, r2, c2);

        System.out.println("-------------------multiply-----------------");
        s.mult_matrix(a, b, r1, c1, r2, c2);

        System.out.println("-----------------trnspose a-----------------");
        System.out.println("first matrix : ");
        s.trans_matrix(a, r1, c1);

        System.out.println("-----------------trnspose b-----------------");
        System.out.println("second matrix : ");
        s.trans_matrix(b, r2, c2);


    }
}
