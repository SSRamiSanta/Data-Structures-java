/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

/**
 *
 * @author JohnSebastian
 */
public class Punto {
    int y;
    int x;
    int numero;
    public Punto() {
    y=0;
    x=0;
    numero=0;
    }
    public Punto(int x, int y, int en) {
    this.y=y;
    this.x=x;
    this.numero=en;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
