/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.util.ArrayList;


public class Grafo {

    public Nodo inicio;
    public Nodo fin;
    public ArrayList<Nodo> cabezas = new ArrayList<>();
    public ArrayList<Nodo> rutaCritica = new ArrayList<>();
    public ArrayList<Nodo> actividadesTemp = new ArrayList<>();
    public ArrayList<Nodo> actividadesle = new ArrayList<>();
    public ArrayList<int[]> rutaC = new ArrayList<>();

    ArrayList<Integer> nodos;

    Object[][] listaAdya;

    public void agregaralista(Object[][] listaAdya, int cantidadNodos) {
        nodos = new ArrayList<>();
        this.listaAdya = listaAdya;
        
        for (int i = 0; i < cantidadNodos; i++) {
            nodos.add(i);
            Nodo nuevo = new Nodo();
            nuevo.nodo = Integer.toString(i + 1);
            cabezas.add(nuevo);
        }
       
        agregarNodos();
       generar();
    
    }
   void  agregaractividades(){
       Nodo act;
       Nodo act2;
         for (int i = 0; i < nodos.size(); i++) {
            for (int j = 0; j < nodos.size(); j++) {
                String[] arr = pasaranumero(listaAdya[i][j].toString());
                if (!arr[0].equals("0")){
                    act=new Nodo();
                    act.nodo=Integer.toString(j+1);
                    act.actividad=arr[1];
                    act.tiempo=Integer.toString( cabezas.get(i).tc);
                    actividadesTemp.add(act);
                    act2=new Nodo();
                    act2.nodo=Integer.toString(j+1);
                    act2.actividad=arr[1];
                    act2.tiempo=Integer.toString(cabezas.get(Integer.parseInt(arr[0])-1).tl-Integer.parseInt(arr[2]));
                    actividadesle.add(act2);
                    if("r".equals(cabezas.get(Integer.parseInt(arr[0])-1).h)
                            && "r".equals(cabezas.get(i).h)
                            && cabezas.get(Integer.parseInt(arr[0])-1).tc-Integer.parseInt(arr[2])-cabezas.get(i).tc==0){
                     int[] aux={Integer.parseInt( cabezas.get(Integer.parseInt(arr[0])-1).nodo),
                         Integer.parseInt(cabezas.get(i).nodo)};
                        rutaC.add(aux);
                    }
                    
                    }
            }
        }
    }
   
    public void agregarNodos() {
        
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = 0; j < nodos.size(); j++) {
                String[] arr = pasaranumero(listaAdya[i][j].toString());
                if (!arr[0].equals("0")) {
                     Nodo uno = cabezas.get(i);
                    Nodo dos = cabezas.get(Integer.parseInt(arr[0]) - 1);
                    agregrarSigAnt(uno, dos);
                }
            }
        }

    }

    public void agregrarSigAnt(Nodo uno, Nodo dos) {
        uno.sigueintes.add(dos);
        dos.anteriores.add(uno);
    }
        int auxFinini=0;
    public void generar() {
         auxFinini = 0;
        for (int i = 0; i < cabezas.size(); i++) {
            if (!cabezas.get(i).anteriores.isEmpty() || !cabezas.get(i).sigueintes.isEmpty()) {
                if (cabezas.get(i).anteriores.isEmpty()) {
                    inicio = cabezas.get(i);
                    auxFinini += 1;
                }
                if (cabezas.get(i).sigueintes.isEmpty()) {
                    auxFinini += 1;
                    fin = cabezas.get(i);
                }
            }
        }
        
        recorrerAdelante(inicio);
        recorrerAtras(fin);
        ruta();
        agregaractividades();
    }

    public void ruta() {
        for (Nodo siguientes : cabezas) {
            if (siguientes.tc - siguientes.tl == 0) {
                rutaCritica.add(siguientes);
                siguientes.h="r";
            }

        }

    }

    public void recorrerAdelante(Nodo nodo) {

        for (int i = 0; i < nodo.sigueintes.size(); i++) {
            tC(nodo.sigueintes.get(i));
        }
        if (!nodo.sigueintes.isEmpty()) {
            iteraAd(nodo.sigueintes);
        }
        fin.tl = fin.tc;
    }

    public void iteraAd(ArrayList<Nodo> nodo) {
        for (Nodo nd : nodo) {
            recorrerAdelante(nd);
        }

    }

    void tC(Nodo nodo) {
        for (Nodo nd : nodo.anteriores) {
            int[] arr = traerAnt(Integer.parseInt(nd.nodo), Integer.parseInt(nodo.nodo));
            String[] arre = pasaranumero(this.listaAdya[arr[0]][arr[1]].toString());
            if (Integer.parseInt(arre[2]) + nd.tc > nodo.tc) {
                nodo.tc = Integer.parseInt(arre[2]) + nd.tc;
            }

        }

    }

    public void recorrerAtras(Nodo nodo) {

        for (int i = 0; i < nodo.anteriores.size(); i++) {
            tl(nodo.anteriores.get(i));
        }
        if (!nodo.anteriores.isEmpty()) {
            iteraAt(nodo.anteriores);
        }

    }

    public void iteraAt(ArrayList<Nodo> nodo) {
        for (Nodo nd : nodo) {
            recorrerAtras(nd);
        }

    }

    public void tl(Nodo nodo) {

        for (int i = 0; i < nodo.sigueintes.size(); i++) {
            int[] arr = traerAnt(Integer.parseInt(nodo.nodo), Integer.parseInt(nodo.sigueintes.get(i).nodo));
            String[] arre = pasaranumero(this.listaAdya[arr[0]][arr[1]].toString());
            if (i == 0) {
                nodo.tl = nodo.sigueintes.get(i).tl - Integer.parseInt(arre[2]);
            } else if (nodo.sigueintes.get(i).tl - Integer.parseInt(arre[2]) < nodo.tl) {
                nodo.tl = nodo.sigueintes.get(i).tl - Integer.parseInt(arre[2]);

            }
            int a = 0;
        }

    }

    public int[] traerAnt(int uno, int dos) {
        int[] num = {0, 0};
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = 0; j < nodos.size(); j++) {

                String[] arr = pasaranumero(listaAdya[i][j].toString());
                if (!arr[0].equals("0")) {

                    if (uno == i + 1 && dos == Integer.parseInt(arr[0])) {
                        num[0] = i;
                        num[1] = j;
                    }

                }
            }
        }
        return num;
    }

    public String[] pasaranumero(String variables) {
        if (!variables.equals("")) {

            String sig = (variables.split(",")[0]);
            String act = (variables.split(",")[1]);
            String tiem = (variables.split(",")[2]);
            String[] arr = {sig, act, tiem};
            return arr;
        }
        String[] arr = {"0", "0", "0"};
        return arr;
    }
}
