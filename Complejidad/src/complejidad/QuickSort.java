/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejidad;

/**
 *
 * @author ssrs_
 */
public class QuickSort extends Metodos {

    int a[];
    int t, i, j, operaciones;

    @Override
    int complejidad(int n, int tam) {

        operaciones += 12;

        int tempi = i;
        int tempj = j;
        int x = a[(int) ((i + j) / 2)];
        do {

            operaciones += 6;
            while (a[i] < x) {

                operaciones += 4;
                i++;
            }
            while (x < a[j]) {

                operaciones += 4;
                j--;
            }
            if (i < j) {

                operaciones += 4;
                cambio(a, i, j);
                i = i + 1;
                j = j - 1;
            }
        } while (i < j);
        if (i == j) {
            
            operaciones+=2;
            if (x < a[i]) {
                
                operaciones+=2;
                j = j - 1;
            } else {
                
                operaciones+=2;
                i = i + 1;
            }
        }
        if (j - 1 == tempi) {
            
            operaciones+=3;
            if (a[tempi] > a[j]) {
                cambio(a, tempi, j);
            }
        } else {
            
            operaciones++;
            if (j > tempi) {
                complejidad(tempi, j);
            }
        }
        if (i + 1 == tempj) {
            
            operaciones+=3;
            if (a[i] > a[tempj]) {
                cambio(a, i, tempj);
            }
        } else {
            
            operaciones++;
            if (i < tempj) {
                complejidad(i, tempj);
            }
        }

        if (n == tam) {
            for (int i = 0; i < n; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println("");
        }
        return operaciones;
    }// quicksort

    void cambio(int a[], int i, int j) {
        operaciones+=7;
        t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    void crearMatriz(int n) {
        a = new int[n];
        j = n - 1;
        i = 0;
        operaciones = 3;

        for (int h = 0; h < n; h++) {//Llenar Matriz
            a[h] = n - h;
        }
    }
}
