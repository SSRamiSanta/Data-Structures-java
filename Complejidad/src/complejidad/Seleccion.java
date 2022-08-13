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
public class Seleccion extends Metodos {

    @Override
    int complejidad(int n, int tam) {
        int operaciones = 1, x, temp;

        int a[] = new int[n];

        for (int i = 0; i < n; i++) {//Llenar Matriz
            a[i] = n - i;
        }

        for (int i = 0; i < n / 2.0; i++) { // N/2 ???
            operaciones = operaciones + 6;
            x = i;
            for (int j = i + 1; j < n - i; j++) { // N-i ????
                operaciones = operaciones + 5;
                if (a[x] > a[j]) {
                    x = j;
                    operaciones = operaciones + 1;
                }
            }
            operaciones = operaciones + 1;
            operaciones = operaciones + 1;
            if (i != x) {
                operaciones = operaciones + 7;
                temp = a[i];
                a[i] = a[x];
                a[x] = temp;
            }
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
