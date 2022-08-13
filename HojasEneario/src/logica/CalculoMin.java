/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ssrs_
 */
public class CalculoMin {

    String[][] matriz;
    private ArrayList<String> valores;
    boolean retorno = true;
    char letramal;

    public CalculoMin(String[][] matriz) {
        this.matriz = matriz;
    }

    public String datosValores() {
        setValores((ArrayList<String>) new ArrayList());
        ArrayList<Character> letras = new ArrayList();
        char letra = 'a';
        int pos = 0;
        for (String[] matriz1 : matriz) {
            String[] datos;
            for (String matriz11 : matriz1) {
                if (!"".equals(matriz11)) {
                    datos = matriz11.split(",");
                    letras.add(datos[0].charAt(0));
                    valores.add(Character.toString(letra) + "," + Character.toString(letras.get(pos)) + "," + Integer.valueOf(datos[1]));
                    pos++;
                }
            }
            letra++;
        }
        return repetidas(letras);
    }

    String repetidas(ArrayList<Character> letras) {
        char letra;
        for (int i = 0; i < letras.size(); i++) {
            letra = letras.get(i);
            for (int j = i + 1; j < letras.size(); j++) {
                if (j == letras.size()) {

                } else {
                    if (letra != letras.get(j)) {
                    } else {
                        setRetorno(false);
                        return "Repetida la " + letra;
                    }

                }
            }
        }
        setRetorno(true);
        return calculomenor();
    }

    String calculomenor() {
        ArrayList<Character> hojas = esHoja();
        ArrayList<String> menorEs = new ArrayList();
        if (hojas == null) {
            return "La letra "+letramal+" debe ser un nodo";
        }
        for (int i = 0; i < hojas.size(); i++) {
            int valorRuta = 0;
            int valorActual = 0;
            char hojaActual = hojas.get(i);
            char padre = '?';

            while (padre != 'a') {
                for (int j = 0; j < valores.size(); j++) {
                    String[] datos = valores.get(j).split(",");
                    if (hojaActual == datos[1].charAt(0)) {
                        if (padre == '?') {
                            valorActual = Integer.valueOf(datos[2]);
                        }
                        padre = datos[0].charAt(0);
                        valorRuta += Integer.valueOf(datos[2]);

                    }
                }
                hojaActual = padre;
            }
            String letraRuta = "" + hojas.get(i) + "," + valorActual + "," + valorRuta;
            menorEs.add(letraRuta);
        }
        int menor, menoraux = 99;
        String resultado = "";
        for (int i = 0; i < menorEs.size(); i++) {
            String[] datos = menorEs.get(i).split(",");
            menor = Integer.valueOf(datos[2]);
            if (menor < menoraux) {
                menoraux = menor;
                resultado = datos[0] + "," + datos[1] + "," + datos[2];
            }
        }
        return resultado;
    }

    ArrayList esHoja() {

        ArrayList<Character> hojas = new ArrayList();

        char letra = 'a';

        while (letra != '{') {
            boolean eshijo = false;
            boolean notienehijos = true;

            int numLetra = 0;

            for (char i = 'a'; i < '{'; i++) {
                if (letra == i) {
                    break;
                }
                numLetra++;
            }

            for (int j = 0; j < matriz[0].length; j++) {

                if (matriz[numLetra][j].equals("")) {
                } else {
                    notienehijos = false;
                }
            }

            for (int i = 0; i < valores.size(); i++) {
                String[] datos = valores.get(i).split(",");
                if (datos[1].charAt(0) == letra) {
                    eshijo = true;
                }
            }

            if (eshijo == false && notienehijos == false && letra != 'a') {
                setLetraMal(letra);
                retorno = false;
                return null;
            }

            if (eshijo && notienehijos) {
                hojas.add(letra);
            }
            letra++;
        }
        return hojas;
    }

    /**
     * @return the retorno
     */
    public boolean isRetorno() {
        return retorno;
    }

    /**
     * @param retorno the retorno to set
     */
    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }

    public void setLetraMal(char letramal) {
        this.letramal = letramal;
    }

    /**
     * @return the valores
     */
    public ArrayList<String> getValores() {
        return valores;
    }

    /**
     * @param valores the valores to set
     */
    public void setValores(ArrayList<String> valores) {
        this.valores = valores;
    }

    public void reinicio() {
        valores = null;
    }

}
