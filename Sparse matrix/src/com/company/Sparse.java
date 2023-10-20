package com.company;


import java.util.Arrays;

public class Sparse {

    public int[][] sparse_calc(int[][] a, int r, int c) {
        int size = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i][j] != 0) {
                    size++;
                }
            }
        }

        int[][] matrix = new int[3][size];
        int l = 0;
        for (int k = 0; k < 3 ; k++) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (a[i][j] != 0 && l < size) {
                        matrix[0][l] = i;
                        matrix[1][l] = j;
                        matrix[2][l] = a[i][j];
                        l++;
                    }

                }

            }
        }
        return matrix;
    }
    public void mult_matrix(int[][] a, int[][] b, int row1, int col1, int row2, int col2){
        if(col1 != row2){
            System.out.println("can not calculate!");
            return;
        }

       int[][] smatrix1 = sparse_calc(a , row1 , col1);
       int[][] smatrix2 = sparse_calc(b , row2 , col2);
       int s1 = 0;
       int s2 = 0;
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if(a[i][j] != 0){
                    s1++;
                }

            }
        }

        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                if(b[i][j] != 0){
                    s2++;
                }

            }
        }
        int [][] result = new int[row1][col2];
        int p = 1;
        for (int i = 0; i <s1 ; i++) {
            for (int j = 0; j <s2 ; j++) {
                if(smatrix1[1][i] == smatrix2[0][j]){
                    p = smatrix1[2][i] * smatrix2[2][j];
                    if(result[smatrix1[0][i]][smatrix2[1][j]] != 0){
                        result[smatrix1[0][i]][smatrix2[1][j]] = result[smatrix1[0][i]][smatrix2[1][j]] + p;
                    }else{
                        result[smatrix1[0][i]][smatrix2[1][j]] = p;
                    }
                }

            }

        }

        int s3 = 0;
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                if(result[i][j] != 0){
                    s3++;
                }
            }

        }
        int[][] finall =sparse_calc(result , row1 , col2);
        for (int j =0; j<s3; j++){
            System.out.println( "i : "+ finall[0][j]+ "  j :  " + finall[1][j] + " value :  " + finall[2][j]);
        }

    }
    public void add_matrix(int[][] a, int[][] b, int row1, int col1, int row2, int col2){
        if(row1 != row2 || col1 != col2){
            System.out.println("can not calculate!");
            return;
        }
        int[][] smatrix1 = sparse_calc(a, row1, col1);
        int[][] smatrix2 = sparse_calc(b, row1, col1);
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (a[i][j] != 0) {
                    s1++;
                }

            }
        }

        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (b[i][j] != 0) {
                    s2++;
                }

            }
        }
        int counter = 0, k;
        int[][] result = new int[row1][col1];
        for (int i = 0; i < s1; i++) {
            k = 0;
            if (counter == col1 * row1) {
                break;
            }
            int j;
            for (j = 0; j < s2; j++) {
                if (smatrix1[0][i] == smatrix2[0][j] && smatrix1[1][i] == smatrix2[1][j]) {
                    counter++;
                    k++;
                    result[smatrix1[0][i]][smatrix2[1][j]] = smatrix1[2][i] + smatrix2[2][j];
                    break;
                }
            }
            if (k == 0 && counter < col1 * row1) {
                if (smatrix1[0][i] == smatrix2[0][j-1] && smatrix1[1][i] == smatrix2[1][j-1]) {
                    counter++;
                    result[smatrix1[0][i]][smatrix2[1][j-1]] = smatrix1[2][i] + smatrix2[2][j-1];

                } else {
                    if (smatrix1[0][i] < smatrix2[0][j-1]) {
                        counter++;
                        result[smatrix1[0][i]][smatrix1[1][i]] = smatrix1[2][i];

                    }
                    if (smatrix1[0][i] > smatrix2[0][j-1]) {
                        counter++;
                        result[smatrix2[0][j-1]][smatrix2[1][j-1]] = smatrix2[2][j-1];

                    }
                    if (smatrix1[0][i] == smatrix2[0][j-1]) {
                        if (smatrix1[1][i] < smatrix2[1][j-1]) {
                            counter++;
                            result[smatrix1[0][i]][smatrix1[1][i]] = smatrix1[2][i];

                        } else {
                            counter++;
                            result[smatrix2[0][j]][smatrix2[1][j-1]] = smatrix2[2][j-1];

                        }
                    }
                }
            }
        }

        int counter2 = 0, l;
        for (int i = 0; i < s2; i++) {
            l = 0;
            if (counter2 == col1 * row1) {
                break;
            }
            int j;
            for (j = 0; j < s1; j++) {
                if (smatrix2[0][i] == smatrix1[0][j] && smatrix2[1][i] == smatrix1[1][j]) {
                    counter2++;
                    l++;
                    break;
                }
            }
            if (l == 0 && counter2 < col1 * row1) {
                result[smatrix2[0][i]][smatrix2[1][i]] = smatrix2[2][i];
            }
        }

        int s3 = 0;
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (result[i][j] != 0) {
                    s3++;
                }
            }

        }
        int[][] finall = sparse_calc(result, row1, col1);
        for (int j = 0; j < s3; j++) {
            System.out.println("i : " + finall[0][j] + "  j :  " + finall[1][j] + " value :  " + finall[2][j]);
        }
    }
    public void sub_matrix(int[][] a, int[][] b, int row1, int col1, int row2, int col2){
        if(row1 != row2 || col1 != col2){
            System.out.println("can not calculate!");
            return;
        }
        int[][] smatrix1 = sparse_calc(a, row1, col1);
        int[][] smatrix2 = sparse_calc(b, row1, col1);
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (a[i][j] != 0) {
                    s1++;
                }

            }
        }

        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (b[i][j] != 0) {
                    s2++;
                }

            }
        }
        int counter = 0, k;
        int[][] result = new int[row1][col1];
        for (int i = 0; i < s1; i++) {
            k = 0;
            if (counter == col1 * row1) {
                break;
            }
            int j;
            for (j = 0; j < s2; j++) {
                if (smatrix1[0][i] == smatrix2[0][j] && smatrix1[1][i] == smatrix2[1][j]) {
                    counter++;
                    k++;
                    result[smatrix1[0][i]][smatrix2[1][j]] = smatrix1[2][i] - smatrix2[2][j];
                    break;
                }
            }
            if (k == 0 && counter < col1 * row1) {
                if (smatrix1[0][i] == smatrix2[0][j-1] && smatrix1[1][i] == smatrix2[1][j-1]) {
                    counter++;
                    result[smatrix1[0][i]][smatrix2[1][j-1]] = smatrix1[2][i] - smatrix2[2][j-1];

                } else {
                    if (smatrix1[0][i] < smatrix2[0][j-1]) {
                        counter++;
                        result[smatrix1[0][i]][smatrix1[1][i]] = smatrix1[2][i];

                    }
                    if (smatrix1[0][i] > smatrix2[0][j-1]) {
                        counter++;
                        result[smatrix2[0][j-1]][smatrix2[1][j-1]] = -smatrix2[2][j-1];

                    }
                    if (smatrix1[0][i] == smatrix2[0][j-1]) {
                        if (smatrix1[1][i] < smatrix2[1][j-1]) {
                            counter++;
                            result[smatrix1[0][i]][smatrix1[1][i]] = smatrix1[2][i];

                        } else {
                            counter++;
                            result[smatrix2[0][j-1]][smatrix2[1][j-1]] = -smatrix2[2][j-1];

                        }
                    }
                }
            }
        }

        int counter2 = 0, l;
        for (int i = 0; i < s2; i++) {
            l = 0;
            if (counter2 == col1 * row1) {
                break;
            }
            int j;
            for (j = 0; j < s1; j++) {
                if (smatrix2[0][i] == smatrix1[0][j] && smatrix2[1][i] == smatrix1[1][j]) {
                    counter2++;
                    l++;
                    break;
                }
            }
            if (l == 0 && counter2 < col1 * row1) {
                result[smatrix2[0][i]][smatrix2[1][i]] = -smatrix2[2][i];
            }
        }
        int s3 = 0;
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (result[i][j] != 0) {
                    s3++;
                }
            }

        }
        int[][] finall = sparse_calc(result, row1, col1);

        for (int j = 0; j < s3; j++) {
            System.out.println("i : " + finall[0][j] + "  j :  " + finall[1][j] + " value :  " + finall[2][j]);
        }
    }
    public void trans_matrix(int[][] a, int row, int col){
        int s = 0;
        int counter = 0;
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                if(a[i][j] != 0){
                    s++;
                }
            }

        }
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++ ) {
                if(a[i][j] != 0 && counter < s){
                    result[j][i] = a[i][j];
                    counter++;
                }

            }

        }
        int s3 = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(result[i][j] != 0){
                    s3++;
                }
            }

        }
        int[][] finall = sparse_calc(result, row, col);
        for (int j =0; j<s3; j++){
            System.out.println( "i : "+ finall[0][j]+ "  j :  " + finall[1][j] + " value :  " + finall[2][j]);
        }


    }
}
