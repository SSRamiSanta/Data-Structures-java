/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.util.ArrayList;

/**
 *
 * @author (╯°□°)╯︵ ┻━┻
 */
public class Nodo {

    ArrayList<Nodo> sigueintes;
    ArrayList<Nodo> anteriores;
   public int tc;
    public int tl;
    public String nodo;
     public String actividad;
         public String tiempo;
    public String h;
    public Nodo() {

        this.sigueintes = new ArrayList<Nodo>();
        this.anteriores = new ArrayList<Nodo>();
        actividad = "";
        this.tc = 0;
        this.tl = 0;
        this.nodo = "";
        this.h="";
        tiempo="";

    }

}
