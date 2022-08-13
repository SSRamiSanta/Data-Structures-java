package arbolb;
import java.util.ArrayList;

public class Nodo {

    private int orden;
    private boolean hoja;
    private ArrayList<String> valoresNodo;
    private ArrayList<Nodo> SubNodos;
    private int nivel;
    Nodo nodo;
    Nodo hijoizquierda;
    Nodo hijoderecha;
    Nodo hijomitad;

    //Nodo para iniciar el arbol.
    public Nodo(int orden, boolean hoja) {
        this.setOrden(orden);//Indica el orden del arbolB
        this.setHoja(hoja);//Indica si el nodo es hoja. Es decir que no tiene hijos.
        setValoresNodos(new ArrayList<String>());//Se crea lista para almacenar los valores del nodo.
        setSubNodos(new ArrayList<Nodo>());//Se crea lista para almacenar los subnodos del nodo principal.
        setNivel(0);
    }

    public Nodo(int orden, SubNodo snodo) {
        this.setOrden(orden);
        this.setHoja(false);
        setValoresNodos(new ArrayList<String>());
        setSubNodos(new ArrayList<Nodo>());
        nivel = 0;
        valoresNodo.add(snodo.getValor());
        SubNodos.add(snodo.getHi());
        SubNodos.add(snodo.getHd());

    }

    //Nodo que se crea al momento de dividir el ArbolB.
    public Nodo(int orden, boolean hoja, ArrayList<String> valores, ArrayList<Nodo> hijos) {
        this.setOrden(orden);
        this.setHoja(hoja);
        this.setValoresNodos(valores);
        this.setSubNodos(hijos);
    }

    public void setHijoIzq(Nodo hijoizq) {
        this.hijoizquierda = nodo.hijoizquierda;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public ArrayList<String> getValoresNodos() {
        return valoresNodo;
    }

    public void setValoresNodos(ArrayList<String> valoresNodos) {
        this.valoresNodo = valoresNodos;
    }

    public ArrayList<Nodo> getSubNodos() {
        return SubNodos;
    }

    public void setSubNodos(ArrayList<Nodo> subNodos) {
        SubNodos = subNodos;
    }

    public SubNodo insertar(String valor) {
        SubNodo sn;
        if (hoja) {
            int i = 0;
            while (i < valoresNodo.size() && valor.compareTo(valoresNodo.get(i)) > 0) {
                i++;//Ciclo que define la posición a guardar el valor.
            }
            valoresNodo.add(i, valor);//se agrega el valor ingresado a la lista valoresnodo.
            sn = tamanonodo();//Se verifica el tamaño del nodo.
            return sn;
        } else {
            int i = 0;
            while ((i < valoresNodo.size()) && (valor.compareTo(valoresNodo.get(i)) > 0)) {
                i++;//Ciclo que valide la posición a guardar el valor.
            }
            sn = SubNodos.get(i).insertar(valor);//chicharrón carnudo.
            if (sn != null) {
                SubNodos.remove(i);
                String val = sn.getValor();
                Nodo iz = sn.getHi();
                Nodo de = sn.getHd();

                valoresNodo.add(i, val);
                SubNodos.add(i, de);
                SubNodos.add(i, iz);
                sn = tamanonodo();

                return sn;
            } else {
                return null;
            }
        }
    }

    private SubNodo tamanonodo() {
        SubNodo sn;
        if (valoresNodo.size() > (2 * orden)) {
            sn = dividir();//Se valida si el tamano del nodo es mayor al permitido y se divide.
            return sn;
        }
        return null;
    }

    private SubNodo dividir() {
        ArrayList<String> Vi = new ArrayList<String>();//Se crea un arreglo para almacenar los valores a la izquierda del valor central
        ArrayList<Nodo> Hi = new ArrayList<Nodo>();//Se crea un arreglo para almacenar los hijos a la izquierda del valor central
        ArrayList<String> Vd = new ArrayList<String>();//Se crea un arreglo para almacenar los valores a la derecha del valor central
        ArrayList<Nodo> Hd = new ArrayList<Nodo>();//Se crea un arreglo para almacenar los hijos a la derecha del valor central

        int i = 0;
        while (i < valoresNodo.size() / 2) {
            Vi.add(valoresNodo.get(i));
            i++;//Este ciclo ingresa los valores a la izquierda del nodo a la lista.
        }

        String val = valoresNodo.get(i);
        i++;

        while (i < valoresNodo.size()) {
            Vd.add(valoresNodo.get(i));
            i++;//Este ciclo ingresa los valores a la derecha del nodo a la lista.
        }

        if (!hoja) {
            for (int k = 0; k <= 2 * orden + 1; k++) {
                if (k < orden + 1) {
                    Hi.add(SubNodos.get(k));
                } else {
                    Hd.add(SubNodos.get(k));
                }
            }
        }

        Nodo iz = new Nodo(orden, hoja, Vi, Hi);
        Nodo de = new Nodo(orden, hoja, Vd, Hd);
        SubNodo sn = new SubNodo(val, iz, de);
        return sn;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Nodo buscarnodo(String valor) {
        if (valoresNodo.contains(valor)) {
            return this;
        } else {
            if (hoja) {
                return null;
            } else {
                int i = 0;
                while (i < valoresNodo.size() && valor.compareTo(valoresNodo.get(i)) > 0) {
                    i++;
                }
                return SubNodos.get(i).buscarnodo(valor);
            }
        }
    }

}
