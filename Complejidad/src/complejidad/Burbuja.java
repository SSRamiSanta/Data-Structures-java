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
public class Burbuja extends Metodos {

    @Override
    int complejidad(int n, int tam) {
        int operaciones = 1, t;
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {//Llenar Matriz
            a[i] = n - i;
        }

        for (int i = 0; i < n - 1; i++) {//4(n-1)+2
            operaciones += 5;
            for (int j = i + 1; j < n; j++) {//12(n*n - ((n-1)n)/2 - n)(n-1)
                if (a[i] > a[j]) {//
                    t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                    operaciones += 7;
                }
                operaciones += 5;
            }
            operaciones++;
        }
        operaciones += 2;

        if (n == tam) {
            for (int i = 0; i < n; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println("");
        }

        return operaciones;
    }
}
