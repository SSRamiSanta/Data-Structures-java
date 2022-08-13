/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;

/**
 *
 * @author ssrs_
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author ssrs_
 */
public class Ventana extends JFrame implements ActionListener, KeyListener {

    public static final int ANCHO = 800, ALTO = 600;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final Color negro = new Color(0, 0, 0);
    private static final Color grisclaro = new Color(180, 180, 180);
    private static final Color gris = new Color(100, 100, 100);
    private static final Color grisosc = new Color(50, 50, 50);
    private static final Color blanco = new Color(250, 250, 250);

    private static final Color COLOR = new Color(51, 51, 55);
    private static final Color COLOR1 = new Color(0, 0, 0);

    private static final Font FUENTE = new Font("TimesRoman", Font.BOLD, 18);

    private static final Border BORDE = BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY);

    private static JPanel panel_datos;
    private static JPanel panel_lista_innorden;
    private static JPanel panel_dibujo_arbol;

    private final JScrollPane scroll_lista_innorden = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private final JScrollPane scroll_dibujo_arbol = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JLabel label_titulo;
    private JLabel label_tam_matriz;
    private final JTextField[] field_codigo = new JTextField[1];

    private final JButton boton_procesar = new JButton();
    private final JButton boton_Cerrar = new JButton();

    private final JButton[] botones_operacion = new JButton[3];

    private Arbol arbol;
    private final Dibujar dibujar = new Dibujar();

    public Ventana() {

        setIconImage(new ImageIcon(Ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        setLayout(null);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(negro);
        //setContentPane(IMAGEN_FONDO);
        requestFocus();
        addKeyListener(this);
        getRootPane().setBorder(BORDE);
        getContentPane().setLayout(null);

        iniciarComponentes();

    }

    private void iniciarComponentes() {
        arbol = new Arbol();

        panel_datos = new JPanel();
        panel_datos.setBackground(gris);
        panel_datos.setForeground(blanco);
        panel_datos.setBounds(5, 5, ANCHO - 15, 90);
        panel_datos.setBorder(BORDE);
        panel_datos.setLayout(null);
        panel_datos.requestFocus();
        panel_datos.addKeyListener(this);

        label_titulo = new JLabel("Arbol AVL");
        label_titulo.setFont(new Font("Times new roman", Font.BOLD, 30));
        label_titulo.setBounds(5, 5, 300, 20);
        label_titulo.setForeground(COLOR1);

        panel_datos.add(label_titulo);

        label_tam_matriz = new JLabel("Ingrese el codigo:");
        label_tam_matriz.setBounds(10, 40, 300, 20);
        label_tam_matriz.setFont(new Font("Arial", Font.BOLD, 20));
        label_tam_matriz.setForeground(COLOR1);

        panel_datos.add(label_tam_matriz);

        for (int i = 0; i < botones_operacion.length; i++) {

            switch (i) {
                case 0:
                    botones_operacion[i] = new JButton("Agregar");
                    break;
                case 1:
                    botones_operacion[i] = new JButton("Retirar");
                    break;
                default:
                    botones_operacion[i] = new JButton("Buscar");
                    break;
            }

            botones_operacion[i].setBounds(panel_datos.getWidth() / 2 - 50, -15 + ((i + 1) * 25), 100, 20);
            botones_operacion[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            botones_operacion[i].setForeground(blanco);
            botones_operacion[i].setBackground(grisosc);
            botones_operacion[i].setBorder(BORDE);
            botones_operacion[i].addActionListener(this);
            botones_operacion[i].addKeyListener(this);
            botones_operacion[i].requestFocus();
            botones_operacion[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    Object o = e.getSource();
                    if (o instanceof JButton) {
                        JButton boton = (JButton) o;

                        boton.setBackground((grisosc));
                        boton.setForeground((blanco));
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    Object o = e.getSource();
                    if (o instanceof JButton) {
                        JButton boton = (JButton) o;

                        boton.setBackground((grisosc));
                        boton.setForeground((blanco));
                    }
                }
            });
            botones_operacion[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent arg0) {
                    Object o = arg0.getSource();
                    if (o instanceof JButton) {
                        JButton boton = (JButton) o;

                        boton.setBackground((grisosc));
                        boton.setForeground((blanco));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Object o = e.getSource();
                    if (o instanceof JButton) {
                        JButton boton = (JButton) o;
                        boton.setBackground((grisosc));
                        boton.setForeground((blanco));
                    }
                }
            });

            panel_datos.add(botones_operacion[i]);
        }

        for (int i = 0; i < field_codigo.length; i++) {

            field_codigo[i] = new JTextField("" + (i + 1));

            field_codigo[i].setBounds(((i + 1) * 200), 40, 100, 20);
            field_codigo[i].setBackground(grisosc);
            field_codigo[i].setForeground(blanco);
            field_codigo[i].setBorder(BORDE);
            field_codigo[i].requestFocus();
            field_codigo[i].addKeyListener(this);
            field_codigo[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char caracter = e.getKeyChar();

                    // Verificar si la tecla pulsada no es un digito
                    if (((caracter < '0')
                            || (caracter > '9'))
                            && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
                        e.consume();  // ignorar el evento de teclado
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                        boton_procesar.doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            field_codigo[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    Object o = e.getSource();
                    if (o instanceof JTextField) {
                        JTextField field_in_num_monedas = (JTextField) o;
                        field_in_num_monedas.setSelectionStart(0);
                        field_in_num_monedas.setSelectionEnd(field_in_num_monedas.getText().length());
                        field_in_num_monedas.setBackground(grisosc);
                        field_in_num_monedas.setForeground(blanco);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    Object o = e.getSource();
                    if (o instanceof JTextField) {
                        JTextField field_in_num_monedas = (JTextField) o;
                        field_in_num_monedas.setBackground(grisosc);
                        field_in_num_monedas.setForeground(blanco);
                    }
                }
            });

            panel_datos.add(field_codigo[i]);
            panel_datos.setComponentZOrder(field_codigo[i], 0);
        }
        field_codigo[0].requestFocus();

        scroll_lista_innorden.setBounds(5, 100, 200, 410);
        scroll_lista_innorden.setVisible(false);
        scroll_lista_innorden.setBorder(BORDE);
        scroll_lista_innorden.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scroll_lista_innorden.getHorizontalScrollBar().setBackground(Color.BLACK);
        scroll_lista_innorden.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scroll_lista_innorden.getVerticalScrollBar().setBackground(Color.BLACK);

        panel_lista_innorden = new JPanel();
        panel_lista_innorden.setBackground(COLOR);
        panel_lista_innorden.setForeground(COLOR1);
        panel_lista_innorden.setBounds(5, 100, 200, ALTO - 110);
        panel_lista_innorden.setBorder(BORDE);
        panel_lista_innorden.setLayout(null);
        panel_lista_innorden.requestFocus();
        panel_lista_innorden.addKeyListener(this);

        panel_dibujo_arbol = new JPanel();
        panel_dibujo_arbol.setBackground(COLOR);
        panel_dibujo_arbol.setForeground(COLOR1);
        panel_dibujo_arbol.setBounds(210, 100, ANCHO - 220, ALTO - 110);
        panel_dibujo_arbol.setVisible(false);
        panel_dibujo_arbol.setBorder(BORDE);
        panel_dibujo_arbol.setLayout(null);
        panel_dibujo_arbol.requestFocus();
        panel_dibujo_arbol.addKeyListener(this);

        scroll_dibujo_arbol.setBounds(210, 100, 500, 410);
        scroll_dibujo_arbol.setVisible(false);
        scroll_dibujo_arbol.setBorder(BORDE);
        scroll_dibujo_arbol.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scroll_dibujo_arbol.getHorizontalScrollBar().setBackground(Color.BLACK);
        scroll_dibujo_arbol.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scroll_dibujo_arbol.getVerticalScrollBar().setBackground(Color.BLACK);

        boton_Cerrar.setText("<html><center>X<html>");
        boton_Cerrar.setBackground(negro);
        boton_Cerrar.setForeground(blanco);
        boton_Cerrar.setFont(new Font("Consolas", Font.BOLD, 30));
        boton_Cerrar.setBounds(panel_datos.getWidth() - 40, 5, 40, 30);
        panel_datos.add(boton_Cerrar).setVisible(true);
        boton_Cerrar.addActionListener(this);

        add(panel_datos);
        add(panel_lista_innorden);
        add(panel_dibujo_arbol);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /////////////////////////////BOTON AGREGAR//////////////////////////
        if (e.getSource() == botones_operacion[0]) {
            panel_lista_innorden.paint(panel_lista_innorden.getGraphics());
            panel_dibujo_arbol.paint(panel_dibujo_arbol.getGraphics());
            arbol.setFila(0);
            panel_dibujo_arbol.setVisible(true);

            int n = Integer.parseInt(field_codigo[0].getText());
            String s = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a registrar:");
            if (arbol.insertar_nodo(n, s) == 2) {
                JOptionPane.showMessageDialog(null, "El codigo ya existe");
            }
            arbol.inorden(arbol.raiz, panel_lista_innorden.getGraphics());
            dibujar.DibujaArbol(panel_dibujo_arbol.getGraphics(), 0, 30, arbol.raiz, panel_dibujo_arbol);

        }

        /////////////////////////////BOTON RETIRAR//////////////////////////
        if (e.getSource() == botones_operacion[1]) {

            panel_dibujo_arbol.paint(panel_dibujo_arbol.getGraphics());
            panel_lista_innorden.paint(panel_lista_innorden.getGraphics());
            arbol.setFila(0);

            int n = Integer.parseInt(field_codigo[0].getText());
            if (arbol.retirar_nodo(n) == 2) {
                JOptionPane.showMessageDialog(null, "El nodo a retirar no se encuentra en el arbol");
            }
            arbol.inorden(arbol.raiz, panel_lista_innorden.getGraphics());
            dibujar.DibujaArbol(panel_dibujo_arbol.getGraphics(), 0, 30, arbol.raiz, panel_dibujo_arbol);

        }

        /////////////////////////////BOTON BUSCAR//////////////////////////
        if (e.getSource() == botones_operacion[2]) {

            int n = Integer.parseInt(field_codigo[0].getText());
            if (arbol.buscar_nodo(n) == 1) {
                JOptionPane.showMessageDialog(null, "El nombre del alumno es: " + arbol.getExistente().nombre);
            }
        }
        if (e.getSource() == boton_Cerrar) {
            System.exit(0);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
            System.exit(0);
        }

        Object o = e.getSource();
        if (o instanceof JButton) {
            JButton boton = (JButton) o;

            boton.doClick();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
