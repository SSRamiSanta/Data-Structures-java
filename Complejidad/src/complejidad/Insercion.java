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
public class Insercion extends Metodos {

    @Override
    int complejidad(int n, int tam) {

        int temp, j, operaciones = 1;

        int a[] = new int[n];

        for (int i = 0; i < n; i++) {//Llenar Matriz
            a[i] = n - i;
        }

        for (int i = 1; i < n; i++) {

            operaciones += 13;

            j = i - 1;
            temp = a[i];
            while (j > -1 && temp < a[j]) {

                operaciones += 5;

                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
        operaciones++;

        if (n == tam) {
            for (int i = 0; i < n; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println("");
        }

        return operaciones;
    }

}
