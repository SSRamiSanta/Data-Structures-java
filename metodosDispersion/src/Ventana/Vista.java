package Ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import metodosdispersion.Logica;

public class Vista extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(50, 50, 50);
    Color grismuyosc = new Color(25, 25, 25);
    Color blanco = new Color(250, 250, 250);

    JPanel PIngreso, PSalida;
    JLabel llavelbl, titulodivisionlbl, titulomidsquarelbl, titulotransformacionlbl, tituloplegamientolbl;
    JLabel divingresolbl, midingresolbl, trbaselbl, trarreglolbl, plegingresolbl;
    JLabel salidalbl;
    JTextField llavetxt, nPrimotxt, nBitstxt, Basetxt, tArreglotxt, ntBitstxt;
    JButton divisionbtn, midsquarebtn, transformacionbtn, plegamientobtn;

    Logica l;

    public Vista() {
        setTitle("Métodos de dispersión");
        setUndecorated(true);
        setLayout(null);
        setIconImage(new ImageIcon(Vista.class.getResource("/imagenes/icono.png")).getImage());
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(30, 30, 1050, 580);
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
        PanelSalida();
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
        if (e.getSource() == divisionbtn) {
            l = new Logica();
            boolean comparar = true;
            if (llavetxt.getText().length() > 9) {
                comparar = false;
            }
            int nPrimo = Integer.parseInt(nPrimotxt.getText());
            if (EsPrimo(nPrimo)) {
                if (comparar) {
                    int llave = Integer.parseInt(llavetxt.getText());
                    salidalbl.setText("= " + l.Division(llave, nPrimo));
                } else {
                    JOptionPane.showMessageDialog(null, "Valor de la llave mayor a 9 cifras", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un número primo", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == midsquarebtn) {
            l = new Logica();
            boolean comparar = true;
            if (llavetxt.getText().length() > 9) {
                comparar = false;
            }
            if (comparar) {
                int llave = Integer.parseInt(llavetxt.getText());
                int nBits = Integer.parseInt(nBitstxt.getText());
                salidalbl.setText("= " + l.Midsquare(llave, nBits));

            } else {
                JOptionPane.showMessageDialog(null, "Valor de la llave mayor a 9 cifras", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource()
                == transformacionbtn) {
            l = new Logica();

            boolean comparar = true;
            if (llavetxt.getText().length() > 9) {
                comparar = false;
            }
            int base = Integer.parseInt(Basetxt.getText());

            if (base <= 16) {
                if (comparar) {
                    int llave = Integer.parseInt(llavetxt.getText());
                    int tArreglo = Integer.parseInt(tArreglotxt.getText());
                    salidalbl.setText("= " + l.Transformacion(llave, base, tArreglo));
                } else {
                    JOptionPane.showMessageDialog(null, "Valor de la llave mayor a 9 cifras", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "La base debe ser menor a 16", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource()
                == plegamientobtn) {
            l = new Logica();

            boolean comparar = true;
            if (llavetxt.getText().length() > 9) {
                comparar = false;
            }

            if (comparar) {
                int llave = Integer.parseInt(llavetxt.getText());
                int ntBits = Integer.parseInt(ntBitstxt.getText());
                salidalbl.setText("= " + l.Plegamiento(llave, ntBits));
            } else {
                JOptionPane.showMessageDialog(null, "Valor de la llave mayor a 9 cifras", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void PanelIngreso() {
        PIngreso = new JPanel();
        PIngreso.setBackground(gris);
        PIngreso.setForeground(gris);
        PIngreso.setBounds(0, 0, 350, 580);
        PIngreso.setLayout(null);
        add(PIngreso);
        componentesIngreso();
    }

    private void PanelSalida() {
        PSalida = new JPanel();
        PSalida.setLayout(null);
        PSalida.setBackground(grisosc);
        PSalida.setForeground(grisosc);
        PSalida.setBounds(350, 0, 700, 580);
        add(PSalida);
        componentesSalida();

    }

    private void componentesIngreso() {
//Llave
        llavelbl = new JLabel("Llave (Máximo 9 cifras):");
        llavelbl.setFont(new Font("Times new roman", Font.BOLD, 30));
        llavelbl.setBounds(20, 10, 500, 30);
        llavelbl.setForeground(negro);
        PIngreso.add(llavelbl).setVisible(true);

        llavetxt = new JTextField("1234");
        llavetxt.setBounds(20, 40, 200, 30);
        PIngreso.add(llavetxt).setVisible(true);

//Division
        titulodivisionlbl = new JLabel("División");
        titulodivisionlbl.setFont(new Font("Times new roman", Font.BOLD, 30));
        titulodivisionlbl.setBounds(20, 75, 500, 30);
        titulodivisionlbl.setForeground(negro);
        PIngreso.add(titulodivisionlbl).setVisible(true);

        divingresolbl = new JLabel("Tamaño del arreglo (Numero primo):");
        divingresolbl.setFont(new Font("Times new roman", Font.BOLD, 20));
        divingresolbl.setBounds(20, 105, 500, 30);
        divingresolbl.setForeground(grismuyosc);
        PIngreso.add(divingresolbl).setVisible(true);

        nPrimotxt = new JTextField("");
        nPrimotxt.setBounds(20, 135, 200, 30);
        PIngreso.add(nPrimotxt).setVisible(true);

        divisionbtn = new JButton("<html><center> MD <html>");
        divisionbtn.setFont(new Font("Times new roman", Font.BOLD, 20));
        divisionbtn.setBounds(230, 135, 80, 30);
        divisionbtn.addActionListener(this);
        PIngreso.add(divisionbtn).setVisible(true);

//Midsquare
        titulomidsquarelbl = new JLabel("Midsquare");
        titulomidsquarelbl.setFont(new Font("Times new roman", Font.BOLD, 30));
        titulomidsquarelbl.setBounds(20, 170, 500, 30);
        titulomidsquarelbl.setForeground(negro);
        PIngreso.add(titulomidsquarelbl).setVisible(true);

        midingresolbl = new JLabel("Numero de bits centrales:");
        midingresolbl.setFont(new Font("Times new roman", Font.BOLD, 20));
        midingresolbl.setBounds(20, 200, 500, 30);
        midingresolbl.setForeground(grismuyosc);
        PIngreso.add(midingresolbl).setVisible(true);

        nBitstxt = new JTextField("");
        nBitstxt.setBounds(20, 230, 200, 30);
        PIngreso.add(nBitstxt).setVisible(true);

        midsquarebtn = new JButton("<html><center> MM <html>");
        midsquarebtn.setFont(new Font("Times new roman", Font.BOLD, 20));
        midsquarebtn.setBounds(230, 230, 80, 30);
        midsquarebtn.addActionListener(this);
        PIngreso.add(midsquarebtn).setVisible(true);

//Transformacion
        titulotransformacionlbl = new JLabel("Transformación");
        titulotransformacionlbl.setFont(new Font("Times new roman", Font.BOLD, 30));
        titulotransformacionlbl.setBounds(20, 265, 500, 30);
        titulotransformacionlbl.setForeground(negro);
        PIngreso.add(titulotransformacionlbl).setVisible(true);

        trbaselbl = new JLabel("Base (Menor a 16):");
        trbaselbl.setFont(new Font("Times new roman", Font.BOLD, 20));
        trbaselbl.setBounds(20, 295, 500, 30);
        trbaselbl.setForeground(grismuyosc);
        PIngreso.add(trbaselbl).setVisible(true);

        Basetxt = new JTextField("");
        Basetxt.setBounds(20, 325, 200, 30);
        PIngreso.add(Basetxt).setVisible(true);

        trarreglolbl = new JLabel("Tamaño arreglo:");
        trarreglolbl.setFont(new Font("Times new roman", Font.BOLD, 20));
        trarreglolbl.setBounds(20, 355, 500, 30);
        trarreglolbl.setForeground(grismuyosc);
        PIngreso.add(trarreglolbl).setVisible(true);

        tArreglotxt = new JTextField("");
        tArreglotxt.setBounds(20, 385, 200, 30);
        PIngreso.add(tArreglotxt).setVisible(true);

        transformacionbtn = new JButton("<html><center> MT <html>");
        transformacionbtn.setFont(new Font("Times new roman", Font.BOLD, 20));
        transformacionbtn.setBounds(230, 385, 80, 30);
        transformacionbtn.addActionListener(this);
        PIngreso.add(transformacionbtn).setVisible(true);

//Plegamiento
        tituloplegamientolbl = new JLabel("Plegamiento");
        tituloplegamientolbl.setFont(new Font("Times new roman", Font.BOLD, 30));
        tituloplegamientolbl.setBounds(20, 420, 500, 30);
        tituloplegamientolbl.setForeground(negro);
        PIngreso.add(tituloplegamientolbl).setVisible(true);

        plegingresolbl = new JLabel("Bits de cada grupo:");
        plegingresolbl.setFont(new Font("Times new roman", Font.BOLD, 20));
        plegingresolbl.setBounds(20, 450, 500, 30);
        plegingresolbl.setForeground(grismuyosc);
        PIngreso.add(plegingresolbl).setVisible(true);

        ntBitstxt = new JTextField("");
        ntBitstxt.setBounds(20, 480, 200, 30);
        PIngreso.add(ntBitstxt).setVisible(true);

        plegamientobtn = new JButton("<html><center> MP <html>");
        plegamientobtn.setFont(new Font("Times new roman", Font.BOLD, 20));
        plegamientobtn.setBounds(230, 480, 80, 30);
        plegamientobtn.addActionListener(this);
        PIngreso.add(plegamientobtn).setVisible(true);

    }

    private void componentesSalida() {
        salidalbl = new JLabel();
        salidalbl.setFont(new Font("Times new roman", Font.BOLD, 80));
        salidalbl.setBounds(75, 250, 600, 80);
        salidalbl.setForeground(negro);
        PSalida.add(salidalbl).setVisible(true);
    }

    private boolean EsPrimo(int num) {
        int raiz = (int) Math.sqrt(num);
        for (int i = 2; i <= raiz; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
