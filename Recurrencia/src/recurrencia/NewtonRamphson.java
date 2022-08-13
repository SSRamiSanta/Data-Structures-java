/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recurrencia;

public class NewtonRamphson {

    int grado;

    NewtonRamphson(int grado) {
        this.grado = grado;
    }

    double x_i, x_i1, ye, yd, eva, deriv;
    int a, r = 0;
    double[] raices;
    boolean siraiz = true;
    int raicesdobles;

    void relleno() {
        raices = new double[grado];
        for (int i = 0; i < grado; i++) {
            raices[i] = 0;
        }
    }

    boolean calcularRaíces(double[] coef) {
        boolean comprueba = true;
        if (coef.length == 3) {
            cuadrado(coef);
        } else {
            double algo;
            for (int i = 0; i < 10; i++) {
                algo = 0;
                algo = Evaluada(i, coef) * Derivada(i, coef);
                if (algo > 0) {
                    x_i = i;
                    break;
                }
                System.out.println("Lo hizo " + i + " veces");
            }
            System.out.println("Antes de entrar xi: " + x_i);
            MetodoNR(coef, x_i);
        }
        if (siraiz) {

        } else {
            System.out.println("No hay raices reales");
            comprueba = false;
        }
        return comprueba;
    }

    void cuadrado(double[] coef) {
        double x0, x1, x2;
        x0 = Math.round(coef[0]);
        x1 = Math.round(coef[1]);
        x2 = Math.round(coef[2]);
        double aux = (double) (Math.pow(x1, 2) - 4 * x0 * x2);
        if (aux < 0) {
            siraiz = false;
        } else {
            aux = (double) Math.sqrt(aux);
            System.out.println(aux);
            raices[r] = ((x1 * (-1)) + aux) / (2 * x0);
            r++;
            raices[r] = ((x1 * (-1)) - aux) / (2 * x0);
            siraiz = true;
        }
    }

    double[] MetodoNR(double[] coef, double anterior) {
        double temp;
        a = 0;
        do {
            ye = 0;
            yd = 0;
            ye = Evaluada(anterior, coef);
            yd = Derivada(anterior, coef);
            if (yd == 0) {
                break;
            } else {
                x_i1 = anterior - (ye / yd);
                temp = x_i1 - anterior;
                anterior = x_i1;
                System.out.println("x_i: " + anterior);
                a++;
            }
        } while (Math.abs(temp) > 0.001 && a < 100);
        if (a == 100) {
            siraiz = false;
            System.out.println("No hay raices reales");
        }

        raices[r] = anterior;
        r++;
        if (coef.length != 3) {
            calcularRaíces(Sintetica(coef, anterior));
        }
        return raices;
    }

    double Evaluada(double anterior, double[] coef) {
        eva = 0;
        for (int i = 0; i < coef.length; i++) {
            eva += coef[i] * (Math.pow(anterior, (coef.length - i - 1)));
        }
        return eva;
    }

    double Derivada(double anterior, double[] coef) {
        deriv = 0;
        for (int i = 0; i < coef.length - 1; i++) {
            deriv += (coef.length - i - 1) * coef[i] * (Math.pow(anterior, (coef.length - i - 2)));
        }
        return deriv;
    }

    double[] Sintetica(double[] coef, double x_i) {
        double[] nuevoscoef = new double[coef.length - 1];
        double coefact = coef[0];
        System.out.println("-----------");
        System.out.println("Division sintetica:");
        for (int i = 0; i < coef.length - 1; i++) {
            nuevoscoef[i] = coefact;
            coefact = coefact * x_i + coef[i + 1];
            if (i == coef.length - 2) {
                System.out.print(nuevoscoef[i] + "x^" + (coef.length - i - 2));
            } else {
                System.out.print(nuevoscoef[i] + "x^" + (coef.length - i - 2) + "  +  ");
            }
        }
        System.out.println("");
        return nuevoscoef;
    }

    double[] getRaices() {
        return raices;
    }

}
