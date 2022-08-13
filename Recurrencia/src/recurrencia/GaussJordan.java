/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recurrencia;

/**
 *
 * @author ssrs_
 */
public class GaussJordan {

    
    double[] gaussjordan(int grado,double m[][],double r[]){
        
        for (int i = 0; i < grado; i++) {
            double d,c;
            d=m[i][i];
            for (int j = 0; j < grado; j++) {
                m[i][j]=(m[i][j]/d);
            }
            r[i]=(r[i]/d);    
            for (int j = 0; j < grado; j++) {
                if(i!=j){
                    c=m[j][i];
                    for (int k = 0; k < grado; k++) {
                        m[j][k]=m[j][k]-(c*m[i][k]);
                    }
                    r[j]=r[j]-(c*r[i]);
                }
            }
        }
        return r;
    }
}
