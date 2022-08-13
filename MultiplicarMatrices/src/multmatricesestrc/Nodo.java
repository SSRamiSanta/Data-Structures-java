package multmatricesestrc;

import java.util.ArrayList;

public class Nodo {

    NodoFila fila; //Apuntador a fila
    Nodo raiz, sig; //Apuntador a la siguiente columna
    private int col;

    Nodo() {
        this.sig = null;
        this.fila = null;
    }

    Nodo(int col) {
        this.col = col;
        this.sig = null;
        this.fila = null;
    }

    int agregaCol(int col) {
        Nodo n = new Nodo(col);

        if (n.raiz == null) {
            n.raiz.col = col;
            return 0;
        }

        Nodo p, q;

        q = null;
        p = n.raiz;
        while (p!=null && p.col<n.col) {
            q=p;
            p=p.sig;
            if(p!=null && p.col==n.col){
                if(q!=null){
                    
                }
            }
        }
                
        return 0;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

}
