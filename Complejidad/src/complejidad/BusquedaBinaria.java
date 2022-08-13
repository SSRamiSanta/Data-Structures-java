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
public class BusquedaBinaria {

    int a[];
    int t, izq, der, x, p = 0, cont = 0;

    boolean busqueda(int a[], int izq, int der, int x) {
        if (izq > der) {
            return false;
        } else {
            int m = (int) ((izq + der) / 2);
            if (x == a[m]) {
                return true;
            } else {
                if (x < a[m]) {
                    return busqueda(a, izq, m - 1, x);
                } else {
                    return busqueda(a, m + 1, der, x);
                }
            }
        }
    }

    public void inicializarVariables(int n) {
        a = new int[n];
        der = n - 1;
        izq = 0;
        cont = 3;
        x = (int) (Math.random() * n);
        for (int h = 0; h < n; h++) {//Llenar Matriz
            a[h] = n - h;
        }
    }
}
