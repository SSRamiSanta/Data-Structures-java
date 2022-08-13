/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosdispersion;

/**
 *
 * @author ssrs_
 */
public class Logica {

    public int Division(int llave, int nPrimo) {
        int dispersion = llave % nPrimo;
        return dispersion;
    }

    public int Midsquare(int llave, int nBits) {
        int tamaño, mitadBits;
        long numero = llave;
        System.out.println("Numero: " + numero * numero);
        String bits = Long.toBinaryString(numero * numero);
        System.out.println("Bits: " + bits);
        int añadir;
        if (bits.length() % 2 == 0) {
            tamaño = bits.length() / 2;
            añadir = 1;
        } else {
            tamaño = (bits.length() / 2) + 1;
            añadir = 0;
        }
        if (nBits % 2 == 0) {
            mitadBits = nBits / 2;
        } else {
            mitadBits = (nBits / 2) + 1;
        }
        String bitsCentrales = bits.substring(tamaño - mitadBits + añadir, tamaño - mitadBits + nBits + añadir);
        return (int) convertirADecimal(bitsCentrales);
    }

    private String convertirABits(int llave) {
        String bits = "";
        boolean salir = true;
        do {
            if (llave == 1 || llave == 0) {
                salir = false;
            } else {
                bits = llave % 2 + bits;
                llave = llave / 2;
            }
        } while (salir);
        bits = 1 + bits;
        return bits;
    }

    private double convertirADecimal(String bits) {
        double valor = 0;
        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '1') {
                valor += Math.pow(2, bits.length() - 1 - i);
            }
        }
        return valor;
    }

    public String Transformacion(int llave, int base, int tArreglo) {
        if (base > 2) {
            String cam = Integer.toString(llave, base);
            String bin = "";
            for (int i = 0; i < cam.length(); i++) {
                bin += Integer.toBinaryString(Integer.parseInt(cam.charAt(i) + "", base));
            }
            int i = Integer.parseInt(bin, 2);
            return (i % tArreglo) + "";
        }
        return "Base mayor a dos";
    }

    public String Plegamiento(int llave, int npBits) {
        String binario = Integer.toBinaryString(llave);
        int tamaño;
        if (binario.length() % npBits == 0) {
            tamaño = binario.length() / npBits;
        } else {
            tamaño = binario.length() / npBits + 1;
        }
        String arreglo[] = new String[tamaño];
        for (int i = 0; i < tamaño; i++) {
            if (i >= tamaño - 1) {
                arreglo[i] = binario.substring(0, binario.length() - (npBits * i));
                for (int j = arreglo[i].length(); j < npBits; j++) {
                    arreglo[i] = 0 + arreglo[i];
                }
            } else {
                arreglo[i] = binario.substring(binario.length() - (npBits * i) - npBits, binario.length() - (npBits * i));

            }
        }

        String arreglo1 = arreglo[0];
        if (npBits < 4) {
            for (int i = 0; i < arreglo.length - 1; i++) {
                arreglo1 = operarArreglo(arreglo1, arreglo[i]);
            }
        } else {
            for (int i = 0; i < arreglo.length - 1; i++) {
                arreglo1 = operarArreglo(arreglo1, arreglo[i + 1]);
            }
        }
        int retorno = (int) convertirADecimal(arreglo1);

        return Integer.toString(retorno);
    }

    private String operarArreglo(String arreglo1, String arreglo2) {
        String retorno = "";
        for (int i = 0; i < arreglo1.length(); i++) {
            if (arreglo1.charAt(i) == '1' && arreglo2.charAt(i) == '1') {
                retorno += 0;
            } else if (arreglo1.charAt(i) == '0' && arreglo2.charAt(i) == '0') {
                retorno += 0;
            } else {
                retorno += 1;
            }

        }
        return retorno;
    }
}
