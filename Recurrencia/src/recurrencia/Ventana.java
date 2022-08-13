package recurrencia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.isNaN;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author ssrs_
 */
public class Ventana extends javax.swing.JFrame implements ActionListener {

    int grado;
    String[] resultados;
    JPanel PanelT, PanelC, PanelI, PanelR;
    Color fondo;
    JLabel grado_lbl, coeficientes_lbl, condicion_lbl, coeficiente_lbl[], fn_lbl[], Result_lbl[];
    JButton grado_btn, calcular_btn, aleatorio_btn, B_Cerrar;
    JTextField grado_txt, coeficientes_txt[], fn_txt[];
    String nombre;
    JScrollPane jsp = new JScrollPane(PanelC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JScrollPane jsp1 = new JScrollPane(PanelR, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    NewtonRamphson ra;
    GaussJordan gj;

    public Ventana() {
        setTitle("Ecuacion en recurrencia");
        setLayout(null);
        setIconImage(new ImageIcon(Ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(500, 100, 400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
    }

    void iniciarComponentes() {

        fondo = new Color(0, 0, 0);
        PanelT = new JPanel();
        PanelT.setLayout(null);
        PanelT.setBounds(0, 0, 400, 600);
        this.add(PanelT);
        PanelT.setBackground(fondo);

        jsp.setBounds(0, 100, 400, 200);
        jsp.setBackground(fondo);
        jsp.setVisible(false);
        PanelT.add(jsp);

        jsp1.setBounds(0, 300, 400, 260);
        jsp1.setBackground(fondo);
        jsp1.setVisible(false);
        PanelT.add(jsp1);

        fondo = new Color(200, 200, 200);
        PanelI = new JPanel();
        PanelI.setLayout(null);
        PanelI.setBounds(0, 0, 400, 100);
        PanelT.add(PanelI);
        PanelI.setBackground(fondo);

        grado_lbl = new JLabel();
        grado_lbl.setText("<html><center> Grado de la ecuación: <html>");
        grado_lbl.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        grado_lbl.setBounds(10, 10, 120, 30);
        PanelI.add(grado_lbl).setVisible(true);

        grado_txt = new JTextField();
        grado_txt.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        grado_txt.setBounds(150, 20, 30, 20);
        PanelI.add(grado_txt).setVisible(true);

        grado_btn = new JButton();
        grado_btn.setText("<html><center> Ingresar <html>");
        grado_btn.setFont(new Font("berlin sans fb demi", Font.BOLD, 15));
        grado_btn.setBounds(200, 10, 100, 30);
        grado_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        grado_btn.setVerticalTextPosition(SwingConstants.CENTER);
        grado_btn.addActionListener(this);
        PanelI.add(grado_btn).setVisible(true);

        aleatorio_btn = new JButton();
        aleatorio_btn.setText("<html><center> Aleatorio <html>");
        aleatorio_btn.setFont(new Font("berlin sans fb demi", Font.BOLD, 15));
        aleatorio_btn.setBounds(200, 50, 100, 30);
        aleatorio_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        aleatorio_btn.setVerticalTextPosition(SwingConstants.CENTER);
        aleatorio_btn.addActionListener(this);
        PanelI.add(aleatorio_btn).setVisible(false);

        B_Cerrar = new JButton("<html><center>X<html>");
        B_Cerrar.setFont(new Font("Consolas", Font.BOLD, 20));
        B_Cerrar.setBounds(PanelI.getWidth() - 50, 5, 30, 20);
        B_Cerrar.setBackground(fondo);
        B_Cerrar.setForeground(Color.BLACK);
        B_Cerrar.setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        PanelI.add(B_Cerrar).setVisible(true);
        B_Cerrar.addActionListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == grado_btn) {

            grado = Integer.parseInt(grado_txt.getText());
            PanelC = new JPanel();
            PanelC.removeAll();
            jsp.setViewportView(PanelC);
            jsp.setVisible(true);
            PanelC.setLayout(null);
            fondo = new Color(150, 150, 150);
            PanelC.setBackground(fondo);
            PanelC.setPreferredSize(new Dimension((grado * 30) + 75, 90));

            coeficientes_lbl = new JLabel("<html><center> Coeficientes: <html>");
            coeficientes_lbl.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
            coeficientes_lbl.setForeground(Color.BLACK);
            coeficientes_lbl.setBounds(5, -15 + (20 * (0 + 1)), 120, 20);
            PanelC.add(coeficientes_lbl).setVisible(true);

            condicion_lbl = new JLabel("<html><center> Condiciones iniciales: <html>");
            condicion_lbl.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
            condicion_lbl.setForeground(Color.BLACK);
            condicion_lbl.setBounds(5, -15 + (50 * (1 + 1)), 150, 30);
            PanelC.add(condicion_lbl).setVisible(true);

            coeficientes_txt = new JTextField[grado + 1];
            coeficiente_lbl = new JLabel[grado + 1];
            fn_txt = new JTextField[grado];
            fn_lbl = new JLabel[grado];

            for (int i = 0; i < grado + 1; i++) {
                coeficientes_txt[i] = new JTextField();
                coeficiente_lbl[i] = new JLabel();
                PanelC.add(coeficientes_txt[i]);
                PanelC.add(coeficiente_lbl[i]);
                coeficientes_txt[i].setBounds(40 + (37 * (i + 3)), 5, 30, 20);
                coeficiente_lbl[i].setBounds(40 + (37 * (i + 3)), 25, 30, 20);
                if (i == 0) {
                    coeficiente_lbl[i].setText("f(n)");
                } else {
                    coeficiente_lbl[i].setText("f(n-" + i + ")");
                }
            }

            for (int i = 0; i < grado; i++) {
                fn_lbl[i] = new JLabel();
                fn_txt[i] = new JTextField();
                PanelC.add(fn_lbl[i]);
                PanelC.add(fn_txt[i]);
                fn_lbl[i].setBounds(40 + (36 * (i + 3)), 75, 30, 20);
                fn_txt[i].setBounds(40 + (35 * (i + 3)), 95, 30, 20);
                fn_lbl[i].setText("f(" + (i + 1) + ")");
            }

            calcular_btn = new JButton();
            calcular_btn.setText("<html><center> Calcular <html>");
            calcular_btn.setFont(new Font("berlin sans fb demi", Font.BOLD, 15));
            calcular_btn.setBounds(50, 150, 100, 30);
            calcular_btn.setHorizontalTextPosition(SwingConstants.CENTER);
            calcular_btn.setVerticalTextPosition(SwingConstants.CENTER);
            calcular_btn.addActionListener(this);
            PanelC.add(calcular_btn).setVisible(true);
            aleatorio_btn.setVisible(true);

        }
        if (e.getSource() == calcular_btn) {

            double[] coeficientes = new double[grado + 1];
            for (int i = 0; i < grado + 1; i++) {
                coeficientes[i] = Double.parseDouble(coeficientes_txt[i].getText());
            }
            double[] fn = new double[grado];

            for (int i = 0; i < grado; i++) {
                fn[i] = Double.parseDouble(fn_txt[i].getText());
            }

            double aux;
            double Raices[], Resultados[];
            ra = new NewtonRamphson(grado);
            gj = new GaussJordan();
            ra.relleno();

            double matriz[][] = new double[grado][grado];

            if (ra.calcularRaíces(coeficientes)) {
                Raices = ra.getRaices();

                for (int i = 0; i < Raices.length; i++) {
                    for (int j = i + 1; j < Raices.length; j++) {
                        if (Raices[i] < Raices[j]) {
                            aux = Raices[i];
                            Raices[i] = Raices[j];
                            Raices[j] = aux;
                        }
                    }
                }
                int cdobles;
                int cr[] = new int[Raices.length];
                double cual[] = new double[Raices.length];
                boolean si;

                for (int i = 0; i < Raices.length; i++) {
                    si = false;
                    cdobles = 0;
                    if (Raices[i] - (double) Math.round(Raices[i]) <= 0.1
                            && (double) Raices[i] - (double) Math.round(Raices[i]) >= (-0.1)) {
                        aux = Math.round(Raices[i]);
                    } else {
                        aux = redondearDecimales(Raices[i], 2);
                    }
                    for (int j = i; j < Raices.length; j++) {
                        if (j < Raices.length - 1) {
                            if (aux == redondearDecimales(Raices[j + 1], 2)) {
                                si = true;
                                cdobles++;
                            }
                        }
                    }
                    if (si) {
                        cual[i] = aux;
                    } else {
                        cual[i] = 0;
                    }
                    cr[i] = cdobles;
                }

                boolean comprueba = true;
                for (int i = 0; i < grado; i++) {
                    BigDecimal bd1 = new BigDecimal(Raices[i]);
                    bd1 = bd1.setScale(3, RoundingMode.HALF_UP);
                    Raices[i] = bd1.doubleValue();

                }

                System.out.println("Raíces:");
                for (int i = 0; i < Raices.length; i++) {
                    System.out.println("R" + (i + 1) + ": " + Raices[i]);
                }

                for (int i = 0; i < grado; i++) {
                    System.out.print("f(" + (i + 1) + ")= ");
                    for (int j = 0; j < grado; j++) {

                        if (cr[j] == 0) {
                            matriz[i][j] = Math.pow(Raices[j], (i + 1));
                            System.out.print("c(" + (j + 1) + ")*" + matriz[i][j] + " ");
                            System.out.print("\t");
                        } else if (cr[j] == 1) {
                            matriz[i][j] = Math.pow((i + 1), (0)) * Math.pow(Raices[j], (i + 1));
                            System.out.print("n^" + (0) + "c(" + (j + 1) + ")*" + matriz[i][j] + " ");
                            j++;
                            System.out.print("\t");
                            matriz[i][j] = Math.pow((i + 1), (1)) * Math.pow(Raices[j], (i + 1));
                            System.out.print("n^" + (1) + "c(" + (j + 1) + ")*" + matriz[i][j] + " ");
                            System.out.print("\t");
                        } else if (cr[j] == 2) {
                            matriz[i][j] = Math.pow((i + 1), (0)) * Math.pow(Raices[j], (i + 1));
                            System.out.print("n^" + (0) + "c(" + (j + 1) + ")*" + matriz[i][j] + " ");
                            j++;
                            System.out.print("\t");
                            matriz[i][j] = Math.pow((i + 1), (1)) * Math.pow(Raices[j], (i + 1));
                            System.out.print("n^" + (1) + "c(" + (j + 1) + ")*" + matriz[i][j] + " ");
                            j++;
                            System.out.print("\t");
                            matriz[i][j] = Math.pow((i + 1), (2)) * Math.pow(Raices[j], (i + 1));
                            System.out.print("n^" + (2) + "c(" + (j + 1) + ")*" + matriz[i][j] + " ");
                            System.out.print("\t");
                        }
                    }
                    System.out.println("");
                }
                for (int i = 0; i < grado; i++) {
                    System.out.println("F(" + (i + 1) + ")= " + fn[i] + "   ");
                }

                Resultados = gj.gaussjordan(grado, matriz, fn);

                for (int x = 0; x < grado; x++) {
                    System.out.println("C" + (x + 1) + "):" + Resultados[x] + " ");
                }

                for (int i = 0; i < grado; i++) {
                    if (isNaN(Resultados[i])) {
                        comprueba = false;
                    } else {
                        BigDecimal bd = new BigDecimal(Resultados[i]);
                        bd = bd.setScale(4, RoundingMode.HALF_UP);
                        Resultados[i] = bd.doubleValue();
                    }
                }

                if (comprueba == false) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado solución unica al sistema");
                } else {
                    PanelR = new JPanel();
                    jsp1.setViewportView(PanelR);
                    jsp1.setVisible(true);
                    PanelR.setLayout(null);
                    fondo = new Color(100, 100, 100);
                    PanelR.setBackground(fondo);
                    PanelR.setPreferredSize(new Dimension((grado * 1000) + 75, 90));

                    Result_lbl = new JLabel[grado + 1];
                    Result_lbl[0] = new JLabel("F(n) = ");
                    Result_lbl[0].setFont(new Font("OCR A Extended", Font.PLAIN, 25));
                    Result_lbl[0].setForeground(Color.BLACK);
                    Result_lbl[0].setBounds(0, 100, 140, 30);
                    PanelR.add(Result_lbl[0]).setVisible(true);

                    for (int i = 0; i < grado; i++) {
                        if (cr[i] == 1) {
                            Result_lbl[i + 1] = new JLabel((Resultados[i]) + " * (" + Raices[i] + ")^n");
                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                            Result_lbl[i + 1].setForeground(Color.BLACK);
                            Result_lbl[i + 1].setBounds((grado * i + 1) * 150, 100, 300, 30);
                            i++;
                            Result_lbl[i + 1] = new JLabel((Resultados[i]) + " * n(" + Raices[i] + ")^n");
                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                            Result_lbl[i + 1].setForeground(Color.BLACK);
                            Result_lbl[i + 1].setBounds((grado * i + 1) * 150, 100, 300, 30);
                        } else if (cr[i] == 2) {
                            Result_lbl[i + 1] = new JLabel((Resultados[i]) + " * (" + Raices[i] + ")^n");
                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                            Result_lbl[i + 1].setForeground(Color.BLACK);
                            Result_lbl[i + 1].setBounds((grado * i + 1) * 150, 100, 300, 30);
                            i++;
                            Result_lbl[i + 1] = new JLabel((Resultados[i]) + " * n(" + Raices[i] + ")^n");
                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                            Result_lbl[i + 1].setForeground(Color.BLACK);
                            Result_lbl[i + 1].setBounds((grado * i + 1) * 150, 100, 300, 30);
                            i++;
                            Result_lbl[i + 1] = new JLabel((Resultados[i]) + " * n^2(" + Raices[i] + ")^n");
                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                            Result_lbl[i + 1].setForeground(Color.BLACK);
                            Result_lbl[i + 1].setBounds((grado * i + 1) * 150, 100, 300, 30);
                            i++;
                            Result_lbl[i + 1] = new JLabel((Resultados[i]) + " * " + Raices[i] + "^n");
                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                            Result_lbl[i + 1].setForeground(Color.BLACK);
                            Result_lbl[i + 1].setBounds((grado * i + 1) * 150, 100, 300, 30);
                        } else {

                            if (i == 0) {
                                Result_lbl[i + 1] = new JLabel((Resultados[i]) + " * (" + Raices[i] + ")^n");
                                PanelR.add(Result_lbl[i + 1]).setVisible(true);
                                Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                                Result_lbl[i + 1].setForeground(Color.BLACK);
                                Result_lbl[i + 1].setBounds((grado * i + 1) * 150, 100, 300, 30);

                            } else {
                                if (Resultados[i] < 0) {
                                    if (i > 0) {
                                        if (Raices[i] == Raices[i - 1]) {
                                            Result_lbl[i + 1] = new JLabel(" - n" + (Resultados[i] * -1) + " * (" + Raices[i] + ")^n");
                                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                                            Result_lbl[i + 1].setForeground(Color.BLACK);
                                            Result_lbl[i + 1].setBounds((grado * i) * 150, 100, 300, 30);
                                            break;
                                        }
                                    }
                                    Result_lbl[i + 1] = new JLabel(" - " + (Resultados[i] * -1) + " * (" + Raices[i] + ")^n");
                                    PanelR.add(Result_lbl[i + 1]).setVisible(true);
                                    Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                                    Result_lbl[i + 1].setForeground(Color.BLACK);
                                    Result_lbl[i + 1].setBounds((grado * i) * 150, 100, 300, 30);

                                } else {
                                    if (i > 0) {
                                        if (Raices[i] == Raices[i - 1]) {
                                            Result_lbl[i + 1] = new JLabel(" + n" + (Resultados[i] * -1) + " * (" + Raices[i] + ")^n");
                                            PanelR.add(Result_lbl[i + 1]).setVisible(true);
                                            Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                                            Result_lbl[i + 1].setForeground(Color.BLACK);
                                            Result_lbl[i + 1].setBounds((grado * i) * 150, 100, 300, 30);
                                            break;
                                        }
                                    }
                                    Result_lbl[i + 1] = new JLabel(" + " + (Resultados[i]) + " * (" + Raices[i] + ")^n");
                                    PanelR.add(Result_lbl[i + 1]).setVisible(true);
                                    Result_lbl[i + 1].setFont(new Font("OCR A Extended", Font.PLAIN, 15));
                                    Result_lbl[i + 1].setForeground(Color.BLACK);
                                    Result_lbl[i + 1].setBounds((grado * i) * 150, 100, 300, 30);

                                }
                            }
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Se encontraron raíces complejas");
            }

            System.out.println("---------------------------------------------------------");
        }
        if (e.getSource() == aleatorio_btn) {

            for (int i = 0; i < grado + 1; i++) {
                coeficientes_txt[i].setText(Double.toString(Math.random() * (100 - (-100)) + (-100)));
            }
            for (int i = 0; i < grado; i++) {
                fn_txt[i].setText(Double.toString(i + 1));
            }
        }
        if (e.getSource() == B_Cerrar) {
            System.exit(0);
        }

    }

    double redondearDecimales(double vinicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = vinicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
