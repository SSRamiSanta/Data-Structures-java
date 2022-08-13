
import java.awt.Container;
import javax.swing.JFrame;
import grafica.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vista extends JFrame implements ActionListener {

    ArrayList<JTextField> inputs = new ArrayList<>();
    PanelD panelpuntos = new PanelD();
    Container c = getContentPane();
    Color black, graywhite, gray, grayheavy, grayblack, white;
    JLabel L_Titulo;
    JButton generarLista = new JButton("Generar");
    JButton calcular = new JButton("Calcular");
    JButton limpiar = new JButton("Limpiar");
    JButton B_Cerrar = new JButton("X");
    JComboBox inicio = new JComboBox();
    JComboBox fin = new JComboBox();
    JPanel P_Ingresos, P_Titulo;

    public Vista() {
        Colores();

        setResizable(false);
        setTitle("Ruta crítica");
        setIconImage(new ImageIcon(Vista.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);

        c.setLayout(null);
        c.setBackground(grayblack);
        c.add(panelpuntos);

        P_Titulo = new JPanel();
        P_Titulo.setLayout(null);
        P_Titulo.setBounds(0, 0, 1000, 50);
        P_Titulo.setBackground(black);
        c.add(P_Titulo);

        L_Titulo = new JLabel();
        L_Titulo.setText("Ruta Crítica");
        L_Titulo.setFont(new Font("Times new roman", Font.BOLD, 25));
        L_Titulo.setBounds(20, 5, 500, 30);
        L_Titulo.setForeground(white);
        P_Titulo.add(L_Titulo).setVisible(true);

        panelpuntos.setBounds(10, 50, 965, 605);
        panelpuntos.setBackground(Color.DARK_GRAY);
        panelpuntos.init();
        panelpuntos.c.addMouseListener(panelpuntos.c);

        panelpuntos.add(generarLista);
        generarLista.setBounds(450, 10, 100, 20);
        generarLista.addActionListener(this);

        P_Titulo.add(limpiar);
        limpiar.setBounds(650, 10, 100, 20);
        limpiar.addActionListener(this);

        panelpuntos.add(calcular);
        calcular.setBounds(570, 10, 100, 20);
        calcular.addActionListener(this);

        B_Cerrar = new JButton("<html><center>X<html>");
        B_Cerrar.setFont(new Font("Consolas", Font.BOLD, 30));
        B_Cerrar.setBounds(P_Titulo.getWidth() - 80, 10, 40, 30);
        P_Titulo.add(B_Cerrar).setVisible(true);
        B_Cerrar.addActionListener(this);
    }

    void mostrar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(120, 50, 1000, 700);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(generarLista)) {
            generarinput(panelpuntos.c.p);
            panelpuntos.c.setEnabled(false);
        }
        if (e.getSource().equals(calcular)) {
            try {
                if (validarCiclos(panelpuntos.c.p) == false) {
                    panelpuntos.c.agregaralista(agregaraList(panelpuntos.c.p), true);
                    panelpuntos.c.grafo.agregaralista(panelpuntos.c.listaAdya, panelpuntos.c.p.size());
                    panelpuntos.c.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "No ingrese ciclos");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Error en el ingreso");
            } catch (StackOverflowError r) {
                JOptionPane.showMessageDialog(null, "Actividad invalida");
            }

        }
        if (e.getSource().equals(limpiar)) {
            this.dispose();
            Vista aaa = new Vista();
            aaa.mostrar();

        }
        if (e.getSource().equals(B_Cerrar)) {
            System.exit(0);
        }
    }

    public boolean validarCiclos(ArrayList<Punto> puntos) {
        boolean esta = false;
        int ind = inputs.size() / puntos.size();
        int aux = 0;
        for (int i = 0; i < ind; i++) {
            for (int j = 0; j < ind; j++) {
                String texto = inputs.get(aux).getText();
                aux++;
                String[] extr = texto.split(",");
                if (extr.length > 1) {
                    if ((Integer.parseInt(extr[0]) - 1) == i) {
                        esta = true;
                        break;
                    }
                }

            }
        }
        return esta;
    }

    public Object[][] agregaraList(ArrayList<Punto> puntos) {

        int ind = inputs.size() / puntos.size();
        Object[][] listaAdya = new Object[ind][ind];
        int aux = 0;
        for (int i = 0; i < ind; i++) {
            for (int j = 0; j < ind; j++) {
                listaAdya[i][j] = inputs.get(aux).getText();
                aux++;
            }
        }
        return listaAdya;
    }

    public void generarinput(ArrayList<Punto> puntos) {

        int tamaño = puntos.size();
        for (int i = 0; i < tamaño; i++) {
            inicio.addItem(i + 1);
            fin.addItem(i + 1);
            for (int j = 0; j < tamaño; j++) {
                if (j == 0) {
                    JLabel ver = new JLabel();
                    panelpuntos.add(ver);
                    ver.setText(Integer.toString(puntos.get(i).getNumero()) + "->");
                    ver.setForeground(black);
                    ver.setBounds(450 + j * 30, 50 + i * 20, 30, 20);
                }

                JTextField pri = new JTextField();
                panelpuntos.add(pri);
                pri.setBounds(500 + j * 40, 50 + i * 20, 40, 20);
                inputs.add(pri);
            }
        }

    }

    private void Colores() {
        black = new Color(0, 0, 0);
        graywhite = new Color(180, 180, 180);
        gray = new Color(100, 100, 100);
        grayheavy = new Color(50, 50, 50);
        grayblack = new Color(25, 25, 25);
        white = new Color(250, 250, 250);
    }

}
