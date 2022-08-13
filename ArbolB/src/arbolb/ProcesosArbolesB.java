package arbolb;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;

public class ProcesosArbolesB {

    private int ordenarbol;
    private ArrayList<String> ArbolB;//Guarda los valores que se van ingresando.
    private Nodo nodo;//Almacena la información necesaria para crear el arbolB
    protected int contadornodos = 1;//Me indica la cantidad de nodos en el ArbolB
    public List listaP = new ArrayList();
    public List listaH = new ArrayList();
    public List sale2 = new ArrayList();

    public List posx1 = new ArrayList();
    public List posx2 = new ArrayList();
    public List posx3 = new ArrayList();

    public List posy = new ArrayList();

    public ProcesosArbolesB(int orden) {
        setOrdenarbol(orden);//Me indica en que orden es el arbolB.
        setNodo(new Nodo(orden, true));//Instancia el primer nodo como raiz.
        ArbolB = new ArrayList<String>();//Se crea lista para almacenar los valores que se ingresan al arbolB
    }


    protected void Insertar(String Valor) {
        ArbolB.add(Valor);//Se ingresa el valor a la lista con los valores del ArbolB
        SubNodo sn = nodo.insertar(Valor);//Se ejecuta el metodo insertar del objeto NODO para retornar un subnodo.
        if (sn != null) {
            Nodo nuevonodo = new Nodo(ordenarbol, sn);
            this.nodo = nuevonodo;
        }
    }

    public int getOrdenarbol() {
        return ordenarbol;
    }

    public void setOrdenarbol(int ordenarbol) {
        this.ordenarbol = ordenarbol;
    }
    //niv.getNivel();

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public String imprimirarbol(Graphics g, ProcesosArbolesB arbol) {
        String arbolstring = "";

        ArrayList<Nodo> niv = arbol.recorrer(g);
        int saltolinea = 0;
        for (Nodo nod : niv) {
            if (nod.getNivel() != saltolinea) {
                arbolstring += "\n ";
                saltolinea = nod.getNivel();
            }
            arbolstring += "   -" + nod.getValoresNodos().toString() + "-   ";
        }
        return arbolstring;
    }

    public ArrayList<Nodo> recorrer(Graphics g) {

        dibujar d = new dibujar();
        Queue<Nodo> cola = new LinkedList<Nodo>();
        ArrayList<Nodo> sale = new ArrayList<Nodo>();
        sale.add(nodo);
        cola.add(nodo);
        int x = 500;
        int y = 10;

        boolean entro = false;
        while (!cola.isEmpty()) {
            Nodo nod = cola.poll();

            for (Nodo hijo : nod.getSubNodos()) {

                hijo.setNivel(nod.getNivel() + 1);
                sale.add(hijo);
                cola.add(hijo);

                if (nod.getValoresNodos().size() > 1) {
                    if (hijo.getValoresNodos().size() > 1) {
                        if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(0)) < 0) {
                            nod.hijoizquierda = hijo;
                        } else if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(1)) > 0) {
                            nod.hijoderecha = hijo;
                        } else {
                            nod.hijomitad = hijo;
                        }
                    } else {
                        if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(0)) < 0) {
                            nod.hijoizquierda = hijo;
                        } else if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(1)) > 0) {
                            nod.hijoderecha = hijo;
                        } else {
                            nod.hijomitad = hijo;
                        }
                    }

                } else {
                    if (hijo.getValoresNodos().size() > 1) {
                        if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(0)) < 0) {
                            nod.hijoizquierda = hijo;
                        } else if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(0)) > 0) {
                            nod.hijoderecha = hijo;
                        } else {
                            nod.hijomitad = hijo;
                        }
                    } else {
                        if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(0)) < 0) {
                            nod.hijoizquierda = hijo;
                        } else if (hijo.getValoresNodos().get(0).compareTo(nod.getValoresNodos().get(0)) > 0) {
                            nod.hijoderecha = hijo;
                        } else {
                            nod.hijomitad = hijo;
                        }
                    }
                }
            }
            if (entro == false) {
                listaP.add(nod);
                listaH.add(nod.getValoresNodos());
                entro = true;
            }

        }
        Nodo padres[] = new Nodo[listaP.size()];
        Nodo hijos[] = new Nodo[listaP.size()];

        for (int i = 0; i < listaP.size(); i++) {
            padres[i] = (Nodo) listaP.get(i);
            listaP.remove(i);
            listaH.remove(i);

        }
        System.out.println("raiz " + padres[0].getValoresNodos());
        // -------------------------- METODO DIBUJAR ARBOL B ------------------------
        d.DibujaArbol(g, x, y, padres[0]);
        for (int i = 0; i < listaP.size(); i++) {
            padres[i] = null;
        }

        return sale;
    }

    protected String eliminar(String valor) {
        if (ArbolB.contains(valor)) {
            Nodo nuevonodo = new Nodo(ordenarbol, true);
            SubNodo Subn;
            ArbolB.remove(valor);
            for (String integer : ArbolB) {
                Subn = nuevonodo.insertar(integer);
                if (Subn != null) {
                    nuevonodo = Subn.toRaiz();
                }
            }
            nodo = nuevonodo;
            return "El #" + valor + " esta eliminado.";
        } else {
            return "Elemento no registrado.";
        }
    }

    public Nodo buscar(String valor) {
        return nodo.buscarnodo(valor);
    }

    public void buscar2(String valor) {
        Nodo nd = buscar(valor);
        if (nd != null) {
            JOptionPane.showMessageDialog(null, "El número " + valor + " se encuentra en el nivel: " + nd.getNivel());
        } else {
            JOptionPane.showMessageDialog(null, "Valor NO encontrado");
        }
    }
}