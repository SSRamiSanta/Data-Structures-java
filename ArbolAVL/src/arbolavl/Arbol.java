
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Stack;

/**
 *
 * @author ssrs_
 */
public class Arbol {

    private static final Font FUENTE = new Font("TimesRoman", Font.BOLD, 14);

    private Nodo existente;

    Nodo raiz;
    private int fila;

    Arbol() {
        raiz = null;
    }

    int insertar_nodo(int n, String st) {
        Nodo nuevo, p, q, s, pivote, pp;
        int llave, altura;
        nuevo = new Nodo(n, st);
        if (raiz == null) {
            raiz = nuevo;
            return (1);
        }
        pp = q = null;
        pivote = p = raiz;
        llave = nuevo.codigo;
        while (p != null) {
            if (p.valor_balance != 0) {
                pp = q;
                pivote = p;
            }
            if (llave == p.codigo) {
                existente = p;
                return (2);
                /* ya existe */
            } else {
                q = p;
                if (llave < p.codigo) {
                    p = p.nodo_izq;
                } else {
                    p = p.nodo_der;
                }
            }
        }
        if (llave < q.codigo) {
            q.nodo_izq = nuevo;
        } else {
            q.nodo_der = nuevo;
        }
        if (llave < pivote.codigo) {
            s = pivote.nodo_izq;
            altura = 1;
        } else {
            s = pivote.nodo_der;
            altura = -1;
        }
        p = s;
        while (p != nuevo) {
            if (llave < p.codigo) {
                p.valor_balance = 1;
                p = p.nodo_izq;
            } else {
                p.valor_balance = -1;
                p = p.nodo_der;
            }
        }
        if (pivote.valor_balance == 0) {
            pivote.valor_balance = altura;
        } else if (pivote.valor_balance + altura == 0) {
            pivote.valor_balance = 0;
        } else {
            if (altura == 1) {
                if (s.valor_balance == 1) {
                    System.out.println("Hace rotderecha");
                    rotacion_derecha(pivote, s);
                } else {
                    System.out.println("Hace doblederecha");
                    s = rotacion_doble_derecha(pivote, s);
                }
            } else {
                if (s.valor_balance == -1) {
                    System.out.println("Hace rotizq");
                    rotacion_izquierda(pivote, s);
                } else {
                    System.out.println("Hace dobleizq");
                    s = rotacion_doble_izquierda(pivote, s);
                }
            }
            if (pp == null) {
                System.out.println("Entra a pp");
                raiz = s;
            } else if (pp.nodo_izq == pivote) {
                pp.nodo_izq = s;
            } else {
                pp.nodo_der = s;
            }
        }
        return 1;
    }

    int buscar_nodo(int n) {
        Nodo p, q, pp, pivote;
        int llave;
        pp = q = null;
        pivote = p = raiz;
        llave = n;
        while (p != null) {
            if (p.valor_balance != 0) {
                pp = q;
                pivote = p;
            }
            if (llave == p.codigo) {
                existente = p;
                return (1);
                /* ya existe */
            } else {
                q = p;
                if (llave < p.codigo) {
                    p = p.nodo_izq;
                } else {
                    p = p.nodo_der;
                }
            }
        }
        return 0;
    }

    int retirar_nodo(int n) {
        System.out.println("n es: " + n);
        Stack pila = new Stack();
        Nodo p, q, t, r;
        int llave, accion;
        // Para trabajar terminar por re
        int[] terminar = new int[1];
        boolean encontro = false;
        if (raiz == null) {
            return (1);
        }
        terminar[0] = 0;
        p = raiz;
        while (!encontro && p != null) {
            pila.push(p);

            if (n < p.codigo) {
                p = p.nodo_izq;
            } else if (n > p.codigo) {
                p = p.nodo_der;
            } else {
                existente = p;
                encontro = true;
            }
        }
        if (!encontro) {
            return (2);
        }
        t = null;
        p = (Nodo) pila.pop();
        llave = p.codigo;
        if (p.nodo_izq == null && p.nodo_der == null) {
            accion = 0;
        } else if (p.nodo_der == null) {
            accion = 1;
        } else if (p.nodo_izq == null) {
            accion = 2;
        } else {
            accion = 3;
        }
        System.out.println("p es: "+p.nombre);
        if (accion == 0 || accion == 1 || accion == 2) {
            if (!pila.empty()) {
                q = (Nodo) pila.pop();
                if (llave < q.codigo) {
                    switch (accion) {
                        case 0:
                        case 1:
                            q.nodo_izq = p.nodo_izq;
                            t = balance_der(q, terminar);
                            break;
                        case 2:
                            q.nodo_izq = p.nodo_der;
                            t = balance_der(q, terminar);
                            break;
                    }
                } else {
                    switch (accion) {
                        case 0:
                        case 2:
                            q.nodo_der = p.nodo_der;
                            t = balance_izq(q, terminar);
                            break;
                        case 1:
                            q.nodo_der = p.nodo_izq;
                            t = balance_izq(q, terminar);
                            break;
                    }
                }
            } else {
                switch (accion) {
                    case 0:
                        raiz = null;
                        terminar[0] = 1;
                        break;
                    case 1:
                        raiz = p.nodo_izq;
                        break;
                    case 2:
                        raiz = p.nodo_der;
                        break;
                }
            }
        } else {
            pila.push(p);
            r = p;
            p = r.nodo_der;
            q = null;
            while (p.nodo_izq != null) {
                pila.push(p);
                q = p;
                p = p.nodo_izq;
            }
            
            System.out.println("llave es antes: "+llave+","+r.codigo+","+p.codigo);
            llave = r.codigo = p.codigo;
            r.nombre = p.nombre;
            System.out.println("llave es despues: "+llave+","+r.codigo+","+p.codigo);
            
            if (q != null) {
                q.nodo_izq = p.nodo_der;
                t = balance_der(q, terminar);
            } else {
                r.nodo_der = p.nodo_der;
                t = balance_izq(r, terminar);
            }
            q = (Nodo) pila.pop();
        }
        while (!pila.empty() && terminar[0] == 0) {

            q = (Nodo) pila.pop();
            if (llave < q.codigo) {
                if (t != null) {
                    q.nodo_izq = t;
                    t = null;
                }
                t = balance_der(q, terminar);
            } else {
                if (t != null) {
                    q.nodo_der = t;
                    t = null;
                }
                t = balance_izq(q, terminar);
            }
        }
        if (t != null) {
            if (pila.empty() == true) {
                raiz = t;
            } else {
                q = (Nodo) pila.pop();
                if (llave < q.codigo) {
                    q.nodo_izq = t;
                } else {
                    q.nodo_der = t;
                }
            }
        }

        return 0;
    }

    void iniciar_fila() {
        fila = 0;
    }

    void inorden(Nodo p, Graphics lienzo) {
        lienzo.setFont(FUENTE);
        lienzo.setColor(Color.WHITE);
        if (p != null) {
            inorden(p.nodo_izq, lienzo);
            lienzo.drawString("" + p.codigo + " | " + p.nombre + " | " + p.valor_balance, 5, ++fila * 20);
            inorden(p.nodo_der, lienzo);
        }
    }

    void preorden(Nodo p, Graphics lienzo) {
        if (p != null) {
            lienzo.drawString("" + p.codigo + " " + p.valor_balance, 90, ++fila * 15);
            preorden(p.nodo_izq, lienzo);
            preorden(p.nodo_der, lienzo);
        }
    }

    void posorden(Nodo p, Graphics lienzo) {
        if (p != null) {
            posorden(p.nodo_izq, lienzo);
            posorden(p.nodo_der, lienzo);
            lienzo.drawString("" + p.codigo + " " + p.valor_balance, 130, ++fila * 15);
        }
    }

    void rotacion_derecha(Nodo p, Nodo q) {
        p.valor_balance = 0;
        q.valor_balance = 0;
        p.nodo_izq = q.nodo_der;
        q.nodo_der = p;
    }

    void rotacion_izquierda(Nodo p, Nodo q) {
        p.valor_balance = 0;
        q.valor_balance = 0;
        p.nodo_der = q.nodo_izq;
        q.nodo_izq = p;
    }

    Nodo raizArbol() {
        return raiz;
    }

    Nodo rotacion_doble_derecha(Nodo p, Nodo q) {
        Nodo r;
        r = q.nodo_der;
        q.nodo_der = r.nodo_izq;
        r.nodo_izq = q;
        p.nodo_izq = r.nodo_der;
        r.nodo_der = p;
        switch (r.valor_balance) {
            case -1:
                q.valor_balance = 1;
                p.valor_balance = 0;
                break;
            case 0:
                q.valor_balance = p.valor_balance = 0;
                break;
            case 1:
                q.valor_balance = 0;
                p.valor_balance = -1;
                break;
        }
        r.valor_balance = 0;
        return r;
    }

    Nodo rotacion_doble_izquierda(Nodo p, Nodo q) {
        Nodo r;
        r = q.nodo_izq;
        q.nodo_izq = r.nodo_der;
        r.nodo_der = q;
        p.nodo_der = r.nodo_izq;
        r.nodo_izq = p;
        switch (r.valor_balance) {
            case -1:
                q.valor_balance = 0;
                p.valor_balance = 1;
                break;
            case 1:
                q.valor_balance = -1;
                p.valor_balance = 0;
                break;
            case 0:
                q.valor_balance = p.valor_balance = 0;
                break;
        }
        r.valor_balance = 0;
        return r;
    }

    Nodo balance_der(Nodo q, int[] terminar) {
        Nodo t = null;
        switch (q.valor_balance) {
            case 1:
                q.valor_balance = 0;
                break;
            case -1:
                t = q.nodo_der;
                switch (t.valor_balance) {
                    case 1:
                        t = rotacion_doble_izquierda(q, t);
                        break;
                    case -1:
                        rotacion_izquierda(q, t);
                        break;
                    case 0:
                        q.nodo_der = t.nodo_izq;
                        t.nodo_izq = q;
                        t.valor_balance = 1;
                        terminar[0] = 1;
                        break;
                }
                break;
            case 0:
                q.valor_balance = -1;
                terminar[0] = 1;
                break;
        }
        return t;
    }

    Nodo balance_izq(Nodo q, int[] terminar) {
        Nodo t = null;
        switch (q.valor_balance) {
            case -1:
                q.valor_balance = 0;
                break;
            case 1:
                t = q.nodo_izq;
                switch (t.valor_balance) {
                    case 1:
                        rotacion_derecha(q, t);
                        break;
                    case -1:
                        t = rotacion_doble_derecha(q, t);
                        break;
                    case 0:
                        q.nodo_izq = t.nodo_der;
                        t.nodo_der = q;
                        t.valor_balance = -1;
                        terminar[0] = 1;
                        break;
                }
                break;
            case 0:
                q.valor_balance = 1;
                terminar[0] = 1;
                break;
        }
        return t;
    }

    public Nodo getExistente() {
        return existente;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

}
