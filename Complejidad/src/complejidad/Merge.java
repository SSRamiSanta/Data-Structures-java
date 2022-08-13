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
public class Merge {

    int[] a;
    int n;

    // clasifica desde
// la posicion 1 . Por ej. para
// a=[20,-1,5,6,7,5,4,5,6];
// la llamada es fusion(a,8);
    Merge(int n) {
        this.n = n;
    }

    void llenar() {
        a = new int[n];
        for (int i = 0; i < n; i++) {//Llenar Matriz
            a[i] = n - i;
        }
        fusion(a, n - 1);
    }

    int c = 0;

    void fusion(int[] a, int n) {
        c += 2;
        if (n == 1) {
            fusion(a,n);
        }
        if (n == 2) {
            c += 3;
            if (a[1] > a[2]) {
                c += 7;
                int t = a[1];
                a[1] = a[2];
                a[2] = t;
                fusion(a,n);
            }
        } else {
            c += 16;
            int[] u = new int[n];
            int[] v = new int[n + 1];
            int dividir = (int) (n / 2);
            for (int k = 1; k <= dividir; k++) {
                c += 5;
                u[k] = a[k];
            }
            for (int j = 1, k = dividir + 1;
                    k <= n; k++, j++) {
                c += 10;
                v[j] = a[k];
            }
            fusion(u, dividir);
            fusion(v, n - dividir);
            mezclar(u, dividir, v, n - dividir, a);
        }
        this.a = a;
    }

    void mezclar(int[] u, int mu, int[] v, int mv, int[] a) {
        c += 9;
        int i = 1;
        int j = 1;
        u[mu + 1] = 32767;
        v[mv + 1] = 32767;
        for (int k = 1; k <= mu + mv; k++) {
            c += 6;
            if (u[i] < v[j]) {
                c += 5;
                a[k] = u[i];
                i++;
            } else {
                c += 5;
                a[k] = v[j];
                j++;
            }
        }
    }

    void imprimirarr() {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
        System.out.print("OE: " + c);
    }
}
