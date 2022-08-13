package arbolrojonegro;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ssrs_
 */

class Nodo {

    int clave;
    Nodo padre;
    Nodo hijoIzquierdo;
    Nodo hijoDerecho;
    int color;
}

public class ArbolRojoNegro {

    private Nodo raiz;
    private Nodo NULL;
    public ArrayList<String> lista = new ArrayList<>();

    private void preOrden(Nodo node) {
        if (node != NULL) {
            System.out.print(node.clave + " ");
            preOrden(node.hijoIzquierdo);
            preOrden(node.hijoDerecho);
        }
    }

    public void inOrder(Nodo node) {
        if (node != NULL) {
            inOrder(node.hijoIzquierdo);
            System.out.print(node.clave + " ");
            inOrder(node.hijoDerecho);
        }
    }

    ArrayList inOrden(Nodo r) {
        if (r != null) {
            inOrden(r.hijoIzquierdo);
            System.out.println(r.clave + ", ");
            lista.add(Integer.toString(r.clave));
            inOrden(r.hijoDerecho);
        }
        return lista;
    }

    private void postOrden(Nodo node) {
        if (node != NULL) {
            postOrden(node.hijoIzquierdo);
            postOrden(node.hijoDerecho);
            System.out.print(node.clave + " ");
        }
    }

    private Nodo buscarArbol(Nodo node, int key) {
        if (node == NULL || key == node.clave) {
            return node;
        }

        if (key < node.clave) {
            return buscarArbol(node.hijoIzquierdo, key);
        }
        return buscarArbol(node.hijoDerecho, key);
    }

    private void nuevoPadre(Nodo u, Nodo v) {
        if (u.padre == null) {
            raiz = v;
        } else if (u == u.padre.hijoIzquierdo) {
            u.padre.hijoIzquierdo = v;
        } else {
            u.padre.hijoDerecho = v;
        }
        v.padre = u.padre;
    }

    private void eliminar(Nodo nodo, int key) {
        Nodo z = NULL;
        Nodo x;
        Nodo y;
        while (nodo != NULL) {
            if (nodo.clave == key) {
                z = nodo;
            }

            if (nodo.clave <= key) {
                nodo = nodo.hijoDerecho;
            } else {
                nodo = nodo.hijoIzquierdo;
            }
        }

        if (z == NULL) {
            JOptionPane.showMessageDialog(null, "La llave no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
            System.out.println("La llave no existe");
            return;
        }

        y = z;
        int yColorOriginal = y.color;
        if (z.hijoIzquierdo == NULL) {
            x = z.hijoDerecho;
            nuevoPadre(z, z.hijoDerecho);
        } else if (z.hijoDerecho == NULL) {
            x = z.hijoIzquierdo;
            nuevoPadre(z, z.hijoIzquierdo);
        } else {
            y = minimum(z.hijoDerecho);
            yColorOriginal = y.color;
            x = y.hijoDerecho;
            if (y.padre == z) {
                x.padre = y;
            } else {
                nuevoPadre(y, y.hijoDerecho);
                y.hijoDerecho = z.hijoDerecho;
                y.hijoDerecho.padre = y;
            }

            nuevoPadre(z, y);
            y.hijoIzquierdo = z.hijoIzquierdo;
            y.hijoIzquierdo.padre = y;
            y.color = z.color;
        }
        if (yColorOriginal == 0) {
            balanceoEl(x);
        }
    }

    // Balanceo del arbol despues de eliminar un nodo
    private void balanceoEl(Nodo x) {
        Nodo s;
        while (x != raiz && x.color == 0) {
            if (x == x.padre.hijoIzquierdo) {
                s = x.padre.hijoDerecho;
                if (s.color == 1) {
                    s.color = 0;
                    x.padre.color = 1;
                    leftRotate(x.padre);
                    s = x.padre.hijoDerecho;
                }

                if (s.hijoIzquierdo.color == 0 && s.hijoDerecho.color == 0) {
                    s.color = 1;
                    x = x.padre;
                } else {
                    if (s.hijoDerecho.color == 0) {
                        s.hijoIzquierdo.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.padre.hijoDerecho;
                    }

                    s.color = x.padre.color;
                    x.padre.color = 0;
                    s.hijoDerecho.color = 0;
                    leftRotate(x.padre);
                    x = raiz;
                }
            } else {
                s = x.padre.hijoIzquierdo;
                if (s.color == 1) {
                    s.color = 0;
                    x.padre.color = 1;
                    rightRotate(x.padre);
                    s = x.padre.hijoIzquierdo;
                }

                if (s.hijoDerecho.color == 0 && s.hijoDerecho.color == 0) {
                    s.color = 1;
                    x = x.padre;
                } else {
                    if (s.hijoIzquierdo.color == 0) {
                        s.hijoDerecho.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.padre.hijoIzquierdo;
                    }

                    s.color = x.padre.color;
                    x.padre.color = 0;
                    s.hijoIzquierdo.color = 0;
                    rightRotate(x.padre);
                    x = raiz;
                }
            }
        }
        x.color = 0;
    }
    // Balancea el nodo despues de una insercion 

    private void balanceoIn(Nodo k) {
        Nodo u;
        while (k.padre.color == 1) {
            if (k.padre == k.padre.padre.hijoDerecho) {
                u = k.padre.padre.hijoIzquierdo;
                if (u.color == 1) {
                    u.color = 0;
                    k.padre.color = 0;
                    k.padre.padre.color = 1;
                    k = k.padre.padre;
                } else {
                    if (k == k.padre.hijoIzquierdo) {
                        k = k.padre;
                        rightRotate(k);
                    }
                    k.padre.color = 0;
                    k.padre.padre.color = 1;
                    leftRotate(k.padre.padre);
                }
            } else {
                u = k.padre.padre.hijoDerecho;

                if (u.color == 1) {
                    u.color = 0;
                    k.padre.color = 0;
                    k.padre.padre.color = 1;
                    k = k.padre.padre;
                } else {
                    if (k == k.padre.hijoDerecho) {
                        k = k.padre;
                        leftRotate(k);
                    }
                    k.padre.color = 0;
                    k.padre.padre.color = 1;
                    rightRotate(k.padre.padre);
                }
            }
            if (k == raiz) {
                break;
            }
        }
        raiz.color = 0;
    }

    private void imprimir(Nodo root, String indent, boolean last) {
        if (root != NULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "rojo" : "negro";
            System.out.println(root.clave + "(" + sColor + ")");
            imprimir(root.hijoIzquierdo, indent, false);
            imprimir(root.hijoDerecho, indent, true);
        }
    }

    public ArbolRojoNegro() {
        NULL = new Nodo();
        NULL.color = 0;
        NULL.hijoIzquierdo = null;
        NULL.hijoDerecho = null;
        raiz = NULL;
    }

    private void inici() {
        NULL = new Nodo();
        NULL.color = 0;
        NULL.hijoIzquierdo = null;
        NULL.hijoDerecho = null;
        raiz = NULL;
    }

    public void preorder() {
        preOrden(this.raiz);
    }

    public void inorder() {
        inOrder(this.raiz);
    }

    public void postorder() {
        postOrden(this.raiz);
    }

    public Nodo searchTree(int k) {
        return buscarArbol(this.raiz, k);
    }

    public Nodo minimum(Nodo node) {
        while (node.hijoIzquierdo != NULL) {
            node = node.hijoIzquierdo;
        }
        return node;
    }

    public Nodo maximum(Nodo node) {
        while (node.hijoDerecho != NULL) {
            node = node.hijoDerecho;
        }
        return node;
    }

    public Nodo successor(Nodo x) {
        if (x.hijoDerecho != NULL) {
            return minimum(x.hijoDerecho);
        }

        Nodo y = x.padre;
        while (y != NULL && x == y.hijoDerecho) {
            x = y;
            y = y.padre;
        }
        return y;
    }

    public Nodo predecessor(Nodo x) {
        if (x.hijoIzquierdo != NULL) {
            return maximum(x.hijoIzquierdo);
        }

        Nodo y = x.padre;
        while (y != NULL && x == y.hijoIzquierdo) {
            x = y;
            y = y.padre;
        }

        return y;
    }

    public void leftRotate(Nodo x) {
        Nodo y = x.hijoDerecho;
        x.hijoDerecho = y.hijoIzquierdo;
        if (y.hijoIzquierdo != NULL) {
            y.hijoIzquierdo.padre = x;
        }
        y.padre = x.padre;
        if (x.padre == null) {
            this.raiz = y;
        } else if (x == x.padre.hijoIzquierdo) {
            x.padre.hijoIzquierdo = y;
        } else {
            x.padre.hijoDerecho = y;
        }
        y.hijoIzquierdo = x;
        x.padre = y;
    }

    public void rightRotate(Nodo x) {
        Nodo y = x.hijoIzquierdo;
        x.hijoIzquierdo = y.hijoDerecho;
        if (y.hijoDerecho != NULL) {
            y.hijoDerecho.padre = x;
        }
        y.padre = x.padre;
        if (x.padre == null) {
            this.raiz = y;
        } else if (x == x.padre.hijoDerecho) {
            x.padre.hijoDerecho = y;
        } else {
            x.padre.hijoIzquierdo = y;
        }
        y.hijoDerecho = x;
        x.padre = y;
    }

    int insert(int key) {
        Nodo nodo = new Nodo();
        nodo.padre = null;
        nodo.clave = key;
        nodo.hijoIzquierdo = NULL;
        nodo.hijoDerecho = NULL;
        nodo.color = 1;

        Nodo y = null;
        Nodo x = this.raiz;

        while (x != NULL) {
            y = x;
            if (nodo.clave < x.clave) {
                x = x.hijoIzquierdo;
            } else if (nodo.clave == x.clave) {
                return 0;
            } else {
                x = x.hijoDerecho;
            }
        }

        nodo.padre = y;
        if (y == null) {
            raiz = nodo;
        } else if (nodo.clave < y.clave) {
            y.hijoIzquierdo = nodo;
        } else if (nodo.clave == y.clave) {
            return 0;
        } else {
            y.hijoDerecho = nodo;
        }
        System.out.println("nodo " + nodo.clave);
        if (nodo.padre == null) {
            nodo.color = 0;
            return 1;
        }

        if (nodo.padre.padre == null) {
            return 1;
        }
        balanceoIn(nodo);
        return 1;
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public int getColor(Nodo nodo) {
        return nodo.color;
    }

    public void inicializar() {
        inici();
    }

    public void eliminarNodo(int data) {
        eliminar(this.raiz, data);
    }

    public void printTree() {
        imprimir(this.raiz, "", true);
    }
}
