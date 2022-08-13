/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import logica.Matriz;
import logica.huffman;

/**
 *
 * @author ssrs_
 */
public class Frame extends JFrame {

    JPanel panel0;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;

    JLabel etiqueta1, sincrbits, comprbits, bitsletras, palabrabits, bits;

    JButton insertarlista, reset;

    JTextField listaLetras = new JTextField();
    JTextField numerosI = new JTextField();
    JTextField numerosE = new JTextField();
    JTextField numeroEx = new JTextField();
    JTable tabla;
    Tabla modelo;

    String[][] matriztotal;

    JScrollPane scroll_arbol, scroll_datos;

    public Frame() {
        setTitle("Algoritmo Huffman");
        setLayout(null);
        setIconImage(new ImageIcon(Frame.class.getResource("/imagenes/icono.png")).getImage());
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(-10, 0, 1500, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        componentes();
    }

    private void componentes() {
        Paneles();
        etiquetas();
        botones();
        cajasDeTexto();
    }

    private void Paneles() {
        panel0 = new JPanel();
        panel0.setLayout(null);
        panel0.setBounds(0, 0, 1600, 50);
        panel0.setBackground(Color.WHITE);
        this.getContentPane().add(panel0);

        panel2 = new JPanel();

        panel2.setLayout(null);

        panel2.setBackground(Color.BLACK);

        scroll_arbol = new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.getContentPane().add(scroll_arbol);

        scroll_arbol.setViewportView(panel2);
        panel2.setPreferredSize(new Dimension(100, 100));

        scroll_arbol.setBounds(0, 50, 1450, 200);

        panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBounds(0, 250, 1600, 150);
        panel3.setBackground(Color.WHITE);
        this.getContentPane().add(panel3);

        panel4 = new JPanel();
        panel4.setLayout(null);
        //panel4.setBounds(0, 400, 1600, 400);
        panel4.setBackground(Color.GRAY);

        scroll_datos = new JScrollPane(panel4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll_datos.setBounds(0, 400, 1600, 330);

        this.getContentPane().add(scroll_datos);
    }

    private void etiquetas() {
        etiqueta1 = new JLabel("Solo letras sin espacios: ");
        etiqueta1.setForeground(Color.BLACK);
        etiqueta1.setFont(new Font("Courier new", Font.BOLD, 16));
        etiqueta1.setBounds(5, 10, 300, 20);
        panel0.add(etiqueta1);

        sincrbits = new JLabel();
        sincrbits.setForeground(Color.BLACK);
        sincrbits.setFont(new Font("Courier new", Font.BOLD, 16));
        sincrbits.setBounds(25, 5, 350, 40);
        panel4.add(sincrbits);

        comprbits = new JLabel();
        comprbits.setForeground(Color.BLACK);
        comprbits.setFont(new Font("Courier new", Font.BOLD, 16));
        comprbits.setBounds(25, 5, 200, 150);
        panel4.add(comprbits);

        bitsletras = new JLabel();
        bitsletras.setForeground(Color.BLACK);
        bitsletras.setFont(new Font("Courier new", Font.BOLD, 16));
        bitsletras.setBounds(400, 5, 500, 250);
        panel4.add(bitsletras);

        palabrabits = new JLabel();
        palabrabits.setForeground(Color.BLACK);
        palabrabits.setFont(new Font("Courier new", Font.PLAIN, 16));
        palabrabits.setBounds(20, 250, 1500, 50);
        panel4.add(palabrabits);

        bits = new JLabel();
        bits.setForeground(Color.BLACK);
        bits.setFont(new Font("Courier new", Font.PLAIN, 16));
        bits.setBounds(20, 275, 1500, 50);
        panel4.add(bits);
    }

    private void cajasDeTexto() {
        panel0.add(listaLetras);
        listaLetras.setBounds(300, 10, 500, 20);
        listaLetras.setText("computadoryteclado");
    }

    private void botones() {
        insertarlista = new JButton("OK");
        insertarlista.setFont(new Font("Courier new", Font.BOLD, 14));
        insertarlista.setBounds(850, 10, 150, 20);
        panel0.add(insertarlista);

        reset = new JButton("Reset");
        reset.setFont(new Font("Courier new", Font.BOLD, 14));
        panel0.add(reset);
        reset.setBounds(1220, 10, 150, 20);

        ActionListener oyente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == insertarlista) {

                    String s = listaLetras.getText();
                    String[] pal = s.split("");

                    Matriz m = new Matriz(s);
                    m.ordenar();

                    char caracteres[] = m.contar();
                    int matriz[][] = m.crearMatriz();

                    ArrayList<Integer> frecuencias = m.getFrecuencias();
                    int tam = frecuencias.size();

                    for (int i = 0; i < matriz[0].length - tam; i++) {
                        huffman h = new huffman(matriz, frecuencias);
                        matriz = h.llenar();
                        frecuencias = h.getFrecuencias();
                    }

                    matriztotal = m.matriztotal(caracteres, matriz);
                    List<List<Integer>> miLista = m.miLista(matriz);
                    m.rellenarArbol(miLista, matriz, caracteres);

                    System.out.println("Caracteres:");
                    for (int i = 0; i < caracteres.length; i++) {
                        System.out.println(" " + caracteres[i]);
                    }

                    for (int i = 0; i < miLista.size(); i++) {
                        miLista.get(i).remove(miLista.get(i).size() - 1);
                    }

                    ArrayList<String> codigos = m.codigos(miLista, matriz, caracteres);

                    int mult = pal.length * 8;

                    System.out.println("Sin comprimir: " + pal.length + " * 8 = " + mult + " bits");

                    String comprimido = "";
                    int num = 0;
                    for (int i = 0; i < pal.length; i++) {
                        for (int k = 0; k < caracteres.length; k++) {
                            if (pal[i].equals(Character.toString(caracteres[k]))) {
                                num = k;
                            }
                        }
                        comprimido += codigos.get(num);
                    }

                    String[] compr = comprimido.split("");
                    int multcompr = compr.length;

                    System.out.println("" + comprimido);
                    System.out.println("Comprimido: " + multcompr);

                    double n1 = (double) multcompr;
                    double n2 = (double) mult;

                    double porcentaje = (n1 / n2) * 100;
                    double porcentaje2 = (1 - (n1 / n2)) * 100;

                    m.dibujarArbol(caracteres.length, panel2);

                    tabla = new JTable();

                    modelo = new Tabla(matriztotal, matriztotal[0]);

                    scroll_datos.setViewportView(panel4);
                    panel4.setPreferredSize(new Dimension(pal.length * 200, 100));

                    tabla.setModel(modelo);
                    tabla.setBounds(0, 0, 1200, 300);
                    tabla.setVisible(true);
                    panel3.add(tabla);
                    panel3.updateUI();
                    sincrbits.setText("<html><center> Sin comprimir: " + pal.length + " * 8 = " + mult + " bits <html>");
                    comprbits.setText("<html><center> Comprimido: " + multcompr + " bits \n"
                            + "Codificado: " + porcentaje + "% de espacio. Ahorro: " + porcentaje2 + "% <html>");
                    String codigosst = "<html><center>";
                    for (int i = 0; i < codigos.size(); i++) {
                        codigosst += "Letra: " + caracteres[i] + ", codigo: " + codigos.get(i) + "\n";
                    }
                    codigosst += "<html>";
                    bitsletras.setText(codigosst);

                    String palabra = "";

                    for (int i = 0; i < pal.length; i++) {
                        palabra += pal[i];
                        int h = 0;

                        for (int j = 0; j < caracteres.length; j++) {
                            if (pal[i].equals(Character.toString(caracteres[j]))) {
                                h = j;
                            }
                        }
                        String[] cantidad = codigos.get(h).split("");

                        for (int j = 1; j < cantidad.length; j++) {
                            palabra += " ";
                        }
                    }

                    palabrabits.setText(palabra);
                    bits.setText(comprimido);

                }

                if (e.getSource() == reset) {
                    panel2.repaint();
                    panel3.repaint();
                    panel4.repaint();
                    panel3.removeAll();
                    //panel4.removeAll();
                    panel2.removeAll();
                    sincrbits.setText("");
                    comprbits.setText("");
                    bitsletras.setText("");
                    palabrabits.setText("");
                    bits.setText("");
                }
            }
        };
        insertarlista.addActionListener(oyente);
        reset.addActionListener(oyente);
    }
}
