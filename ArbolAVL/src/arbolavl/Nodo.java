/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;

/**
 *
 * @author ssrs_
 */
public class Nodo {

    int codigo; // matricula
    String nombre;
    int valor_balance;
    Nodo nodo_izq;
    Nodo nodo_der;

    Nodo(int codigo) {
        this.codigo = codigo;
        this.nombre = "";
        valor_balance = 0;
        nodo_izq = nodo_der = null;
    }

    Nodo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        valor_balance = 0;
        nodo_izq = nodo_der = null;
    }

}
