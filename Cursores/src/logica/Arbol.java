package logica;

import java.util.ArrayList;
import java.util.Stack;
import vista.Ventana;

public class Arbol {

    private int raiz;
    String titulo;
    String existente;
    private final String[][] matriz;

    public Arbol(String titulo) {
        raiz = 0;
        this.titulo = titulo;
        matriz = new String[21][5];
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][0] = i + "";
                matriz[i][1] = "";
                matriz[i][3] = "0";
                matriz[i][2] = "0";
                matriz[i][4] = "0";
            }
        }
    }

    public int insertarDato(String palabra, int nuevo) {
        int p, q, s, pivote, pp, altura;
        pp = q = 0;

        if (getRaiz() == 0) {
            raiz = nuevo;
            matriz[nuevo][1] = palabra;
            //matriz[1][1] = letra;
            return 0;
        }
        matriz[nuevo][1] = palabra;
        pivote = p = raiz;

        for (String[] matriz1 : matriz) {
            if (!"".equals(matriz[p][1])) {
                if (!"0".equals(matriz[p][4])) {
                    pp = q;
                    pivote = p;
                }

                if (matriz[p][1].compareTo(palabra) == 0) {
                    existente = matriz[p][1];
                    return 2;
                } else {
                    q = p;
                    if (matriz[p][1].compareTo(palabra) > 0) {
                        p = Integer.valueOf(matriz[p][2]);
                    } else {
                        p = Integer.valueOf(matriz[p][3]);
                    }
                }
            }
        }

        if (matriz[q][1].compareTo(palabra) > 0) {
            matriz[q][2] = String.valueOf(nuevo);
        } else {
            matriz[q][3] = String.valueOf(nuevo);
        }

        if (matriz[pivote][1].compareTo(palabra) > 0) {
            s = Integer.valueOf(matriz[pivote][2]);
            altura = 1;
        } else {
            s = Integer.valueOf(matriz[pivote][3]);
            altura = -1;
        }

        p = s;
        while (p != nuevo) {
            if (matriz[p][1].compareTo(palabra) > 0) {
                matriz[p][4] = "1";
                p = Integer.valueOf(matriz[p][2]);
            } else {
                matriz[p][4] = "-1";
                p = Integer.valueOf(matriz[p][3]);
            }
        }

        if (matriz[pivote][4].equals("0")) {
            matriz[pivote][4] = String.valueOf(altura);
        } else if (Integer.valueOf(matriz[pivote][4]) + altura == 0) {
            matriz[pivote][4] = "0";
        } else {
            if (altura == 1) {
                if (matriz[s][4].equals("1")) {
                    rotacionDer(pivote, s);
                } else {
                    s = rotacionDobleDer(pivote, s);
                }
            } else {
                if (matriz[s][4].equals("-1")) {
                    rotacionIzq(pivote, s);
                } else {
                    s = rotacionDobleIzq(pivote, s);
                }
            }

            if (pp == 0) {
                raiz = s;
                matriz[0][2] = String.valueOf(getRaiz());
            } else if (Integer.valueOf(matriz[pp][2]) == pivote) {
                matriz[pp][2] = String.valueOf(s);
            } else {
                matriz[pp][3] = String.valueOf(s);
            }
        }

        return 1;
    }

    public int retirarDato(String palabra) {
        int n = valorPalabra(palabra);
        Stack pila = new Stack();
        int p, q, t, r;
        int llave, accion;

        int[] terminar = new int[1];
        boolean encontro = false;
        if (raiz == 0) {
            return 1;
        }
        terminar[0] = 0;
        p = raiz;

        while (!encontro) {
            pila.push(p);
            if (n < valorPalabra(matriz[p][1])) {
                p = Integer.valueOf(matriz[p][2]);
            } else if (n > valorPalabra(matriz[p][1])) {
                p = Integer.valueOf(matriz[p][3]);
            } else {
                existente = String.valueOf(p);
                encontro = true;
            }
        }

        if (!encontro) {
            return 2;
        }
        t = 0;
        p = (int) pila.pop();
        llave = valorPalabra(matriz[p][1]);
        if ("0".equals(matriz[p][2]) && "0".equals(matriz[p][3])) {
            accion = 0;
        } else if ("0".equals(matriz[p][3])) {
            accion = 1;
        } else if ("0".equals(matriz[p][2])) {
            accion = 2;
        } else {
            accion = 3;
        }

        if (accion == 0 || accion == 1 || accion == 2) {
            if (!pila.empty()) {
                q = (int) pila.pop();
                if (llave < valorPalabra(matriz[q][1])) {
                    switch (accion) {
                        case 0:
                        case 1:
                            matriz[q][2] = matriz[p][2];
                            t = balanceDer(q, terminar);
                            break;
                        case 2:
                            matriz[q][2] = matriz[p][3];
                            t = balanceDer(q, terminar);
                            break;
                    }
                } else {
                    switch (accion) {
                        case 0:
                        case 2:
                            matriz[q][3] = matriz[p][3];
                            t = balanceIzq(q, terminar);
                            break;
                        case 1:
                            matriz[q][3] = matriz[p][2];
                            t = balanceIzq(q, terminar);
                            break;
                    }
                }
            } else {
                switch (accion) {
                    case 0:
                        raiz = 0;
                        terminar[0] = 1;
                        break;
                    case 1:
                        raiz = Integer.valueOf(matriz[p][2]);
                        break;
                    case 2:
                        raiz = Integer.valueOf(matriz[p][3]);
                        break;
                }
            }

        } else {
            pila.push(p);
            r = p;
            p = Integer.valueOf(matriz[r][3]);
            q = 0;

            while (!"0".equals(matriz[p][2])) {
                pila.push(p);
                q = p;
                p = Integer.valueOf(matriz[p][2]);
            }

            matriz[r][1] = matriz[p][1];
            matriz[p][1] = "";

            llave = valorPalabra(matriz[r][1]);

            if (q != 0) {
                matriz[q][2] = matriz[p][3];
                t = balanceDer(q, terminar);
            } else {
                matriz[r][3] = matriz[p][3];
                t = balanceIzq(r, terminar);
            }
            q = (int) pila.pop();
        }
        while (!pila.empty() && terminar[0] == 0) {
            q = (int) pila.pop();
            if (llave < valorPalabra(matriz[q][1])) {
                if (t != 0) {
                    matriz[q][2] = String.valueOf(t);
                    t = 0;
                }
                t = balanceDer(q, terminar);
            } else {
                if (t != 0) {
                    matriz[q][3] = String.valueOf(t);
                    t = 0;
                }
                t = balanceIzq(q, terminar);
            }
        }
        if (t != 0) {
            if (pila.empty() == true) {
                raiz = t;
            } else {
                q = (int) pila.pop();
                if (llave < valorPalabra(matriz[q][1])) {
                    matriz[q][2] = String.valueOf(t);
                } else {
                    matriz[q][3] = String.valueOf(t);
                }
            }
        }
        return p;
    }

    void rotacionDer(int p, int q) {
        matriz[p][4] = "0";
        matriz[q][4] = "0";
        matriz[p][2] = matriz[q][3];
        matriz[q][3] = String.valueOf(p);
    }

    void rotacionIzq(int p, int q) {
        matriz[p][4] = "0";
        matriz[q][4] = "0";
        matriz[p][3] = matriz[q][2];
        matriz[q][2] = String.valueOf(p);
    }

    int rotacionDobleDer(int p, int q) {
        int r;
        r = Integer.valueOf(matriz[q][3]);
        matriz[q][3] = matriz[r][2];
        matriz[r][2] = String.valueOf(q);
        matriz[p][2] = matriz[r][3];
        matriz[r][3] = String.valueOf(p);

        switch (matriz[r][4]) {
            case "-1":
                matriz[q][4] = "1";
                matriz[p][4] = "0";
                break;
            case "0":
                matriz[q][4] = matriz[p][4] = "0";
                break;
            case "1":
                matriz[q][4] = "0";
                matriz[p][4] = "-1";
                break;
        }
        matriz[r][4] = "0";
        return r;
    }

    int rotacionDobleIzq(int p, int q) {
        int r;
        r = Integer.valueOf(matriz[q][2]);
        matriz[q][2] = matriz[r][3];
        matriz[r][3] = String.valueOf(q);
        matriz[p][3] = matriz[r][2];
        matriz[r][2] = String.valueOf(p);
        switch (Integer.valueOf(matriz[r][4])) {
            case -1:
                matriz[q][4] = "0";
                matriz[p][4] = "1";
                break;
            case 1:
                matriz[q][4] = "-1";
                matriz[p][4] = "0";
                break;
            case 0:
                matriz[q][4] = matriz[p][4] = "0";
                break;
        }
        matriz[r][4] = "0";
        return r;
    }

    int balanceDer(int q, int[] terminar) {
        int t = 0;
        switch (matriz[q][4]) {
            case "1":
                matriz[q][4] = "0";
                break;
            case "-1":
                t = Integer.valueOf(matriz[q][3]);
                switch (matriz[t][4]) {
                    case "1":
                        t = rotacionDobleIzq(q, t);
                        break;
                    case "-1":
                        rotacionIzq(q, t);
                        break;
                    case "0":
                        matriz[q][3] = matriz[t][2];
                        matriz[t][2] = String.valueOf(q);
                        matriz[t][4] = "1";
                        terminar[0] = 1;
                        break;
                }
                break;
            case "0":
                matriz[q][4] = "-1";
                terminar[0] = 1;
                break;
        }
        return t;
    }

    int balanceIzq(int q, int[] terminar) {
        int t = 0;
        switch (matriz[q][4]) {
            case "-1":
                matriz[q][4] = "0";
                break;
            case "1":
                t = Integer.valueOf(matriz[q][2]);
                switch (matriz[t][4]) {
                    case "1":
                        rotacionDer(q, t);
                        break;
                    case "-1":
                        t = rotacionDobleDer(q, t);
                        break;
                    case "0":
                        matriz[q][2] = matriz[t][3];
                        matriz[t][3] = String.valueOf(q);
                        matriz[t][4] = "-1";
                        terminar[0] = 1;
                        break;
                }
                break;
            case "0":
                matriz[q][4] = "1";
                terminar[0] = 1;
                break;
        }
        return t;
    }

    public String[][] getMatriz() {
        return matriz;
    }

    private int valorPalabra(String palabra) {
        String[] letras = palabra.split("");
        int total = 0;
        for (String letra : letras) {
            int pos = 1;
            for (char j = 'a'; j < 'z'; j++) {
                if (letra.charAt(0) == j) {
                    total += pos;
                }
                pos++;
            }
        }
        return total;
    }

    public int getRaiz() {
        return raiz;
    }
}
