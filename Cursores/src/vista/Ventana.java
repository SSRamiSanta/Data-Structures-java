package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import logica.Arbol;

public class Ventana extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(50, 50, 50);
    JPanel PIngreso, PTabla;
    JLabel inicializarlbl, insertaretirarlbl, eliminarlbl, infolbl, raizdisplbl;
    JTextField inicializartxt, traducciontxt, insertaretirartxt;
    JButton inicializarbtn, retirarbtn, insertarbtn, salirbtn;
    JScrollPane scrollTabla;

    String titulo;
    Arbol a;
    int nuevo, cantdatos;

    ArrayList<String> arrpalabras, arrtraducciones;
    String[][] matriz, matriz1;
    JLabel[][] tabla;

    public Ventana() {
        //setUndecorated(true);
        setLayout(null);
        setIconImage(new ImageIcon(Ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(30, 30, 650, 750);
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
            int i;
            titulo = inicializartxt.getText();
            for (i = 0; i < titulo.length(); i++) {
            }
            if (i > 4) {
                JOptionPane.showMessageDialog(null, "Digite máximo 4 letras");
            } else {
                insertaretirarlbl.setVisible(true);
                insertaretirartxt.setVisible(true);
                insertarbtn.setVisible(true);
                retirarbtn.setVisible(true);

                infolbl.setText("<html><center> Base de datos " + titulo + " inicializada <html>");
                a = new Arbol(titulo);
                nuevo = 1;
                PTabla.repaint();
                PTabla.removeAll();
                cantdatos = 0;
            }
        }

        if (e.getSource()
                == insertarbtn) {
            String palabra = insertaretirartxt.getText();
            if (a.insertarDato(palabra, nuevo) == 2) {
                System.out.println("Palabra insertada " + palabra + " en la psosicion " + nuevo);
                JOptionPane.showMessageDialog(getContentPane(), "Palabra repetida", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                matriz = new String[a.getMatriz().length][a.getMatriz()[0].length];
                llenarMatriz();
                nuevo = siguienteDisp();
                //imprimirMatriz();
                datos();
                infolbl.setText("<html><center> Palabra (" + palabra + ") insertada <html>");
                Tabla();
                cantdatos++;
            }
        }

        if (e.getSource()
                == retirarbtn) {
            String palabra = insertaretirartxt.getText();
            int p = a.retirarDato(palabra);
            matriz = new String[a.getMatriz().length][a.getMatriz()[0].length];
            a.getMatriz()[p][1] = "";
            a.getMatriz()[p][2] = "0";
            a.getMatriz()[p][3] = "0";
            a.getMatriz()[p][4] = "0";
            llenarMatriz();
            nuevo = siguienteDisp();
            datos();
            Tabla();
            infolbl.setText("<html><center> Palabra (" + insertaretirartxt.getText() + ") retirada <html>");
            cantdatos--;
        }
        if (e.getSource() == salirbtn) {
            System.exit(0);
        }
    }

    private void Tabla() {

        PTabla.repaint();
        PTabla.removeAll();
        PTabla.add(infolbl).setVisible(true);

        raizdisplbl.setText("<html><left> Raiz: " + matriz[0][2] + "<br> Siguiente disponible: " + matriz[0][3]);
        PTabla.add(raizdisplbl).setVisible(true);

        tabla = new JLabel[a.getMatriz()[0].length + 1][a.getMatriz().length + 1];

        for (int i = 0; i < a.getMatriz()[0].length; i++) {
            tabla[0][i] = new JLabel();
            tabla[0][i].setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
            tabla[0][i].setFont(new Font("Times new roman", Font.BOLD, 25));
            tabla[0][i].setForeground(Color.WHITE);
            tabla[0][i].setBackground(grisclaro);
            String nombre = "";
            if (i == 0) {
                nombre = "Reg.";
            } else if (i == 1) {
                nombre = "Nombre";
            } else if (i == 2) {
                nombre = "izq";
            } else if (i == 3) {
                nombre = "der";
            } else if (i == 4) {
                nombre = "Bal";
            }

            if (i == 0) {
                tabla[0][i].setBounds((20 * (i + 1)), (30 * 2), 80, 30);
            } else if (i == 1) {
                tabla[0][i].setBounds((100 * (i)), (30 * 2), 250, 30);
            } else if (i == 2) {
                tabla[0][i].setBounds((350), (30 * 2), 70, 30);
            } else if (i == 3) {
                tabla[0][i].setBounds((420), (30 * 2), 70, 30);
            } else if (i == 4) {
                tabla[0][i].setBounds((490), (30 * 2), 70, 30);
            }
            tabla[0][i].setText(nombre);
            PTabla.add(tabla[0][i]);
        }
        for (int x = 1; x < a.getMatriz()[0].length + 1; x++) {
            for (int y = 1; y < a.getMatriz().length + 1; y++) {
                tabla[x][y] = new JLabel();
                tabla[x][y].setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
                tabla[x][y].setFont(new Font("Times new roman", Font.BOLD, 25));
                tabla[x][y].setForeground(Color.WHITE);
                tabla[x][y].setBackground(grisclaro);
                if (x == 1) {
                    tabla[x][y].setBounds(20, (30 * (y + 2)), 80, 30);
                } else if (x == 2) {
                    tabla[x][y].setBounds(100, (30 * (y + 2)), 250, 30);
                } else if (x == 3) {
                    tabla[x][y].setBounds(350, (30 * (y + 2)), 70, 30);
                } else if (x == 4) {
                    tabla[x][y].setBounds(420, (30 * (y + 2)), 70, 30);
                } else if (x == 5) {
                    tabla[x][y].setBounds(490, (30 * (y + 2)), 70, 30);
                }
                tabla[x][y].setText(matriz[y - 1][x - 1]);
                PTabla.add(tabla[x][y]);
            }
        }
    }

    private void PanelIngreso() {
        PIngreso = new JPanel();
        PIngreso.setBackground(gris);
        PIngreso.setForeground(gris);
        PIngreso.setBounds(0, 0, 650, 150);
        PIngreso.setLayout(null);
        add(PIngreso);
        componentesIngreso();
    }

    private void componentesIngreso() {

        inicializarlbl = new JLabel("Inicializar BD (Máx 4 letras):");
        inicializarlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        inicializarlbl.setBounds(20, 10, 500, 30);
        inicializarlbl.setForeground(negro);
        PIngreso.add(inicializarlbl).setVisible(true);

        inicializartxt = new JTextField("BD_1");
        inicializartxt.setBounds(20, 40, 300, 30);
        PIngreso.add(inicializartxt).setVisible(true);

        inicializarbtn = new JButton("<html><center> Inicializar <html>");
        inicializarbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        inicializarbtn.setBounds(330, 40, 100, 30);
        inicializarbtn.addActionListener(this);
        PIngreso.add(inicializarbtn).setVisible(true);

        insertaretirarlbl = new JLabel("Insertar o retirar palabra:");
        insertaretirarlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        insertaretirarlbl.setBounds(20, 70, 500, 30);
        insertaretirarlbl.setForeground(negro);
        PIngreso.add(insertaretirarlbl).setVisible(false);

        insertaretirartxt = new JTextField("palabra");
        insertaretirartxt.setBounds(20, 100, 300, 30);
        PIngreso.add(insertaretirartxt).setVisible(false);

        insertarbtn = new JButton("<html><center> Insertar <html>");
        insertarbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        insertarbtn.setBounds(330, 100, 100, 30);
        insertarbtn.addActionListener(this);
        PIngreso.add(insertarbtn).setVisible(false);

        retirarbtn = new JButton("<html><center> Retirar <html>");
        retirarbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        retirarbtn.setBounds(450, 100, 100, 30);
        retirarbtn.addActionListener(this);
        PIngreso.add(retirarbtn).setVisible(false);

        salirbtn = new JButton("<html><center> X <html>");
        salirbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        salirbtn.setForeground(negro);
        salirbtn.setBackground(grisclaro);
        salirbtn.setBounds(PIngreso.getWidth() - 50, 0, 50, 30);
        salirbtn.addActionListener(this);
        PIngreso.add(salirbtn).setVisible(true);

    }

    private void PanelTabla() {
        scrollTabla = new JScrollPane(PTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTabla.setBounds(0, 150, 643, 600);
        scrollTabla.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollTabla.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollTabla.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollTabla.getVerticalScrollBar().setBackground(Color.BLACK);

        PTabla = new JPanel();
        scrollTabla.setViewportView(PTabla);
        PTabla.setLayout(null);
        PTabla.setBackground(grisosc);
        PTabla.setPreferredSize(new Dimension(0, 1000));
        add(scrollTabla);

        componentesTabla();

    }

    private void componentesTabla() {
        infolbl = new JLabel();
        infolbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        infolbl.setBounds(20, 5, 350, 50);
        infolbl.setForeground(negro);
        PTabla.add(infolbl).setVisible(true);

        raizdisplbl = new JLabel();
        raizdisplbl.setFont(new Font("Times new roman", Font.BOLD, 20));
        raizdisplbl.setBounds(350, 0, 250, 50);
        raizdisplbl.setForeground(negro);
        PTabla.add(raizdisplbl).setVisible(true);

    }

    public void datos() {
        matriz[0][2] = String.valueOf(a.getRaiz());
        matriz[0][3] = String.valueOf(disponibles());

    }

    private void imprimirMatriz() {
        System.out.println("Matriz:");
        for (int i = 0; i < a.getMatriz().length; i++) {
            for (int j = 0; j < a.getMatriz()[0].length; j++) {
                System.out.print(a.getMatriz()[i][j] + " \t");
            }
            System.out.println("");
        }
    }

    private void llenarMatriz() {
        for (int i = 0; i < a.getMatriz().length; i++) {
            for (int j = 0; j < a.getMatriz()[0].length; j++) {
                matriz[i][j] = a.getMatriz()[i][j];
            }
        }
    }

    private int siguienteDisp() {
        int i;
        for (i = 1; i < a.getMatriz().length; i++) {
            if (a.getMatriz()[i][1].equals("")) {
                return i;
            }
        }
        return i;
    }

    private int disponibles() {
        boolean primero = true, siguiente;
        int pos1 = 0;
        for (int i = 1; i < a.getMatriz().length - 1; i++) {
            siguiente = true;
            if ("".equals(a.getMatriz()[i][1])) {
                if (primero) {
                    pos1 = i;
                }
                primero = false;
                for (int j = i + 1; j < a.getMatriz().length; j++) {
                    if ("".equals(a.getMatriz()[j][1]) && siguiente) {
                        matriz[i][3] = String.valueOf(j);
                        //imprimirMatriz();
                        siguiente = false;
                    }
                }
            }

        }
        return pos1;
    }
}
