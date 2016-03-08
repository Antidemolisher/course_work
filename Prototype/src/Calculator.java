import java.math.BigDecimal;

/**
 * Created by Роман on 21.02.2016.
 */
public  class Calculator {
    public static double getNorm(double[][] A){
        double[] lineSums = new double[A.length];
        for(int i = 0; i < A.length; i++){
            lineSums[i] = 0.0;
            for(int j = 0; j < A[i].length;j++){
                lineSums[i] += Math.abs(A[i][j]);
            }
        }
        double norm = 0.0;
        for(int i = 0; i < lineSums.length; i++)
            if(lineSums[i] > norm)
                norm = lineSums[i];
        return norm;
    }
    public static void getr(double[] result, double[] y,  double[][] G, double[] f){
        for(int i = 0; i < result.length; i++){
            result[i] = 0.0;
            for(int j = 0; j < result.length; j++)
            {
                double temp = G[i][j] * y[j];
                result[i] += temp;
            }
            result[i] -= f[i];
        }
    }
}
