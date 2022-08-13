/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizdispersion;

import Funciones.Logica;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(50, 50, 50);
    JPanel PIngreso, PTabla;
    JLabel inicializarlbl, aleatorioslbl, insertaretirarlbl, eliminarlbl, infolbl, raizdisplbl, proxdispllbl;
    JLabel cabezaslbl, buscarlbl, cursorlbl;
    JTextField inicializartxt, cantidadtxt, insertaretirartxt, letrastxt;
    JButton inicializarbtn, buscarbtn, retirarbtn, aleatoriosbtn, dispersarbtn;
    JScrollPane scrollTabla;
    Logica a;
    String titulo;
    int nPrimo, cantidad;

    ArrayList<String> arrpalabras, arrtraducciones;
    String[][] matriz, matriz1;
    JLabel[][] tablacursor, tablacab, tablabusc;

    public Frame() {
        setTitle("Matriz de dispersión");
        setLayout(null);
        setIconImage(new ImageIcon(Frame.class.getResource("/imagenes/icono.png")).getImage());
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(0, 0, 1500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        requestFocus();
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        getContentPane().setLayout(null);
        setBackground(negro);
        setForeground(negro);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        PanelIngreso();
        PanelTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inicializarbtn) {
            nPrimo = Integer.parseInt(inicializartxt.getText());
            if (esPrimo(String.valueOf(nPrimo))) {
                aleatoriosbtn.setVisible(true);
                insertaretirarlbl.setVisible(true);
                insertaretirartxt.setVisible(true);
                dispersarbtn.setVisible(true);
                cantidadtxt.setVisible(true);
                letrastxt.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un numero primo");
            }

        }

        if (e.getSource() == aleatoriosbtn) {
            Random r = new Random();
            cantidad = Integer.parseInt(cantidadtxt.getText());

            int[] llaves = new int[cantidad];
            String[] letras = new String[cantidad];
            for (int i = 0; i < cantidad; i++) {
                llaves[i] = (int) (Math.random() * 200) + 1;
                String letras2 = "";
                for (int j = 0; j < 2; j++) {
                    char c = (char) (r.nextInt(26) + 'a');
                    letras2 += String.valueOf(c);
                }
                letras[i] = letras2;
            }

            ArrayList<Integer> datos = new ArrayList();
            for (int i = 0; i < llaves.length; i++) {
                datos.add(llaves[i]);
            }
            ArrayList<Integer> sinRep = generos(datos);

            llaves = new int[sinRep.size()];
            for (int i = 0; i < llaves.length; i++) {
                llaves[i] = sinRep.get(i);
            }
            String datosValores = "", datosLetras = "";

            for (int i = 0; i < llaves.length; i++) {

                if (i == llaves.length - 1) {
                    datosValores += llaves[i];
                    datosLetras += letras[i];
                } else {
                    datosValores += llaves[i] + ",";
                    datosLetras += letras[i] + ",";
                }
            }
            insertaretirartxt.setText(datosValores);
            letrastxt.setText(datosLetras);
        }

        if (e.getSource() == dispersarbtn) {
            buscarbtn.setVisible(true);
            retirarbtn.setVisible(true);
            String[] datosStr = insertaretirartxt.getText().split(",");
            String[] ids = letrastxt.getText().split(",");

            int datos[] = new int[datosStr.length];

            for (int i = 0; i < datos.length; i++) {
                datos[i] = Integer.parseInt(datosStr[i]);
            }

            a = new Logica(nPrimo, datos, ids);
            a.llenar();
            a.guardar();
            a.llenarMatriz();
            System.out.println("");
            System.out.println("");
            System.out.println("Matriz original");
            for (int i = 0; i < a.crearMatriz().length; i++) {
                for (int j = 0; j < a.crearMatriz()[0].length; j++) {
                    System.out.print(a.crearMatriz()[i][j] + " \t");
                }
                System.out.println("");
            }
            System.out.println(a + "");
            String[][] matrizLLaves = a.matrizCabeza();
            for (int i = 0; i < matrizLLaves.length; i++) {
                for (int j = 0; j < matrizLLaves[0].length; j++) {
                    System.out.print(matrizLLaves[i][j] + " ");
                }
                System.out.println("");
            }
            TablaCursor(a);
            TablaCabeza(a);
        }
        if (e.getSource() == buscarbtn) {
            int buscar = Integer.parseInt(insertaretirartxt.getText());
            if (a.BuscarDisponible(buscar)) {
                a.matrizBusqueda(a.buscarDato(buscar));
                String[][] matrizBuscar = a.getMatrizBuscar();
                for (int i = 0; i < matrizBuscar.length; i++) {
                    for (int j = 0; j < matrizBuscar[0].length; j++) {
                        System.out.print(matrizBuscar[i][j] + "\t");
                    }
                    System.out.println("");
                }
                TablaBuscar(a);
            } else {
                JOptionPane.showMessageDialog(null, "Valor no se encuentra", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if (e.getSource() == retirarbtn) {
            int borrado = Integer.parseInt(insertaretirartxt.getText());
            a.borrar(borrado);
            TablaCursor(a);
            TablaCabeza(a);
        }
    }

    private void TablaCursor(Logica a) {
        PTabla.repaint();
        PTabla.removeAll();
        System.out.println("--------------------------------------------");
        tablacursor = new JLabel[a.getMatriz()[0].length][a.getMatriz().length];
        proxdispllbl.setText("Proximo disponible: " + a.getDisponible());
        proxdispllbl.setBounds(200, (a.getMatrizCab().length * 50) - 30, 400, 30);
        PTabla.add(proxdispllbl).setVisible(true);

        cursorlbl.setBounds(20, (a.getMatrizCab().length * 50) - 30, 400, 30);
        PTabla.add(cursorlbl).setVisible(true);

        for (int x = 0; x < a.getMatriz()[0].length; x++) {
            for (int y = 0; y < a.getMatriz().length; y++) {
                tablacursor[x][y] = new JLabel();
                tablacursor[x][y].setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
                tablacursor[x][y].setFont(new Font("Times new roman", Font.PLAIN, 15));
                tablacursor[x][y].setForeground(Color.WHITE);
                tablacursor[x][y].setBackground(grisclaro);
                tablacursor[x][y].setBounds(20 + (62 * (x)), a.getMatrizCab().length * 50 + (30 * (y)), 60, 30);
                tablacursor[x][y].setText(a.getMatriz()[y][x]);
                PTabla.add(tablacursor[x][y]).setVisible(true);
            }
        }
    }

    private void TablaCabeza(Logica a) {

        tablacab = new JLabel[a.getMatrizCab()[0].length][a.getMatrizCab().length];
        cabezaslbl.setBounds(20, 20, 400, 30);
        PTabla.add(cabezaslbl).setVisible(true);
        int mayor = 0, temp = 0;
        for (int i = 0; i < a.getMatrizCab().length; i++) {
            temp = a.getMatrizCab()[i].length;
            if (temp > mayor) {
                mayor = temp;
            }
        }

        for (int x = 0; x < a.getMatrizCab()[0].length; x++) {
            for (int y = 0; y < a.getMatrizCab().length; y++) {
                tablacab[x][y] = new JLabel();
                tablacab[x][y].setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
                tablacab[x][y].setFont(new Font("Times new roman", Font.PLAIN, 15));
                tablacab[x][y].setForeground(Color.WHITE);
                tablacab[x][y].setBackground(grisclaro);
                if (x == 2) {
                    tablacab[x][y].setBounds(20 + (62 * (x)), 50 + (30 * (y)), 100 * mayor, 30);
                } else {
                    tablacab[x][y].setBounds(20 + (62 * (x)), 50 + (30 * (y)), 60, 30);
                }

                tablacab[x][y].setText(a.getMatrizCab()[y][x]);
                PTabla.add(tablacab[x][y]).setVisible(true);
            }
        }
    }

    private void TablaBuscar(Logica a) {
        PTabla.repaint();
        PTabla.removeAll();
        TablaCursor(a);
        TablaCabeza(a);

        tablabusc = new JLabel[a.getMatrizBuscar()[0].length][a.getMatrizBuscar().length];
        buscarlbl.setBounds(a.getMatrizCab()[0].length * 200, 20, 400, 30);
        PTabla.add(buscarlbl).setVisible(true);

        int mayor = 0, temp = 0;
        for (int i = 0; i < a.getMatrizCab().length; i++) {
            temp = a.getMatrizCab()[i].length;
            if (temp > mayor) {
                mayor = temp;
            }
        }

        for (int x = 0; x < a.getMatrizBuscar()[0].length; x++) {
            for (int y = 0; y < a.getMatrizBuscar().length; y++) {
                tablabusc[x][y] = new JLabel();
                tablabusc[x][y].setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
                tablabusc[x][y].setFont(new Font("Times new roman", Font.PLAIN, 15));
                tablabusc[x][y].setForeground(Color.WHITE);
                tablabusc[x][y].setBackground(grisclaro);
                if (x == 1) {
                    tablabusc[x][y].setBounds(a.getMatrizCab()[0].length * 200 + (130 * (x)), 50 + (30 * (y)), 130 * mayor, 30);
                } else {
                    tablabusc[x][y].setBounds(a.getMatrizCab()[0].length * 200 + (130 * (x)), 50 + (30 * (y)), 130, 30);
                }
                tablabusc[x][y].setText(a.getMatrizBuscar()[y][x]);
                PTabla.add(tablabusc[x][y]).setVisible(true);
            }
        }
    }

    private void PanelIngreso() {
        PIngreso = new JPanel();
        PIngreso.setBackground(gris);
        PIngreso.setForeground(gris);
        PIngreso.setBounds(0, 0, 1500, 150);
        PIngreso.setLayout(null);
        add(PIngreso);
        componentesIngreso();
    }

    private void componentesIngreso() {

        inicializarlbl = new JLabel("Inserte número primo:");
        inicializarlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        inicializarlbl.setBounds(20, 10, 500, 30);
        inicializarlbl.setForeground(negro);
        PIngreso.add(inicializarlbl).setVisible(true);

        inicializartxt = new JTextField();
        inicializartxt.setBounds(20, 40, 300, 30);
        PIngreso.add(inicializartxt).setVisible(true);

        inicializarbtn = new JButton("<html><center> Inicializar <html>");
        inicializarbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        inicializarbtn.setBounds(330, 40, 100, 30);
        inicializarbtn.addActionListener(this);
        PIngreso.add(inicializarbtn).setVisible(true);

        insertaretirarlbl = new JLabel("Insertar datos:");
        insertaretirarlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        insertaretirarlbl.setBounds(450, 10, 500, 30);
        insertaretirarlbl.setForeground(negro);
        PIngreso.add(insertaretirarlbl).setVisible(false);

        cantidadtxt = new JTextField("7");
        cantidadtxt.setBounds(450, 40, 50, 40);
        PIngreso.add(cantidadtxt).setVisible(false);

        aleatoriosbtn = new JButton("<html><center> Datos aleatorios<html>");
        aleatoriosbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        aleatoriosbtn.setBounds(520, 40, 100, 40);
        aleatoriosbtn.addActionListener(this);
        PIngreso.add(aleatoriosbtn).setVisible(false);

        insertaretirartxt = new JTextField("23,55,63");
        insertaretirartxt.setBounds(650, 10, 300, 30);
        PIngreso.add(insertaretirartxt).setVisible(false);

        letrastxt = new JTextField("fe,gt,qw");
        letrastxt.setBounds(650, 50, 300, 30);
        PIngreso.add(letrastxt).setVisible(false);

        dispersarbtn = new JButton("<html><center> Dispersar datos<html>");
        dispersarbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        dispersarbtn.setBounds(960, 20, 150, 50);
        dispersarbtn.addActionListener(this);
        PIngreso.add(dispersarbtn).setVisible(false);

        buscarbtn = new JButton("<html><center> Buscar <html>");
        buscarbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        buscarbtn.setBounds(1120, 20, 150, 50);
        buscarbtn.addActionListener(this);
        PIngreso.add(buscarbtn).setVisible(false);

        retirarbtn = new JButton("<html><center> Retirar <html>");
        retirarbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        retirarbtn.setBounds(1120, 70, 150, 50);
        retirarbtn.addActionListener(this);
        PIngreso.add(retirarbtn).setVisible(false);

    }

    private void PanelTabla() {
        scrollTabla = new JScrollPane(PTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollTabla.setBounds(0, 150, 1355, 585);
        scrollTabla.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollTabla.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollTabla.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollTabla.getVerticalScrollBar().setBackground(Color.BLACK);

        PTabla = new JPanel();
        scrollTabla.setViewportView(PTabla);
        PTabla.setLayout(null);
        PTabla.setBackground(grisosc);
        PTabla.setPreferredSize(new Dimension(50000, 10000));
        add(scrollTabla);

        componentesTabla();

    }

    private void componentesTabla() {
        proxdispllbl = new JLabel();
        proxdispllbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        proxdispllbl.setForeground(negro);
        PTabla.add(proxdispllbl).setVisible(true);

        cabezaslbl = new JLabel("Tabla Cabezas");
        cabezaslbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        cabezaslbl.setForeground(negro);
        PTabla.add(cabezaslbl).setVisible(true);

        buscarlbl = new JLabel("Tabla Búsqueda");
        buscarlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        buscarlbl.setForeground(negro);
        PTabla.add(buscarlbl).setVisible(false);

        cursorlbl = new JLabel("Tabla Cursor");
        cursorlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        cursorlbl.setForeground(negro);
        PTabla.add(cursorlbl).setVisible(true);
    }

    public static ArrayList<Integer> generos(ArrayList<Integer> generos) {
        String repetidas = "";
        boolean rep = false;
        ArrayList<Integer> sinrep = new ArrayList<>();
        for (int i = 0; i < generos.size(); i++) {
            if (!sinrep.contains(generos.get(i))) {
                sinrep.add(generos.get(i));
            } else {
                repetidas += generos.get(i) + ", ";
                rep = true;
                System.out.println("Repetida la " + generos.get(i));
            }
        }
        if (rep) {
            JOptionPane.showMessageDialog(null, "Repetidas: " + repetidas + " se eliminarán");
        }
        return sinrep;
    }

    private boolean esPrimo(String llaves) {
        // El 0, 1 y 4 no son primos
        if (Integer.parseInt(llaves) == 0 || Integer.parseInt(llaves) == 1 || Integer.parseInt(llaves) == 4) {
            return false;
        }
        for (int x = 2; x < Integer.parseInt(llaves) / 2; x++) {
            // Si es divisible por cualquiera de estos números, no
            // es primo
            if (Integer.parseInt(llaves) % x == 0) {
                return false;
            }
        }
        // Si no se pudo dividir por ninguno de los de arriba, sí es primo
        return true;
    }
}
