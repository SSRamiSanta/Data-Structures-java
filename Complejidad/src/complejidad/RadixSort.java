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
public class RadixSort extends Metodos {

    @Override
    int complejidad(int n, int tam) {

        int operaciones = 0;

        //Creacion y llenado del arreglo
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }
        ////////////

        int max = a[0];

        operaciones += 3;

        for (int i = 1; i < n; i++) {
            operaciones += 4;
            if (a[i] > max) {
                operaciones += 2;
                max = a[i];
            }
        }//

        operaciones += 4;

        int posicion = 1;

        while (max / posicion > 0) {//

            operaciones += 11;

            int[] salida = new int[n];
            int[] conteo = new int[n + 1];
            for (int i = 0; i < n; i++) {
                operaciones += 6;
                conteo[(a[i] / posicion) % 10]++;
            }
            for (int i = 1; i < n; i++) {
                operaciones += 8;
                conteo[i] = conteo[i] + conteo[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                operaciones += 17;
                salida[conteo[(a[i] / posicion) % 10] - 1] = a[i];
                conteo[(a[i] / posicion) % 10]--;
            }
            for (int i = 0; i < n; i++) {
                operaciones += 5;
                a[i] = salida[i];
            }
            if (n > 9) {
                operaciones += 2;
                posicion *= 10;
            } else {
                operaciones++;
                posicion++;
            }

        }

        //Imprime array
        if (n == tam) {
            for (int h = 0; h < n; h++) {
                System.out.print(a[h] + " ");
            }
            System.out.println("");
        }

        return operaciones;

    }
}