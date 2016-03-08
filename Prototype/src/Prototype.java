import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Роман on 21.02.2016.
 */
public class Prototype {
    static double [][] g = {{-10.0,-20.0},{3.0,-4.0}};
    static double [] f = {-30.0,-1.0};
    static double [] y0 = {3.0,7.0};

//----------------------------------------------------------------------------------------------------------------------


    public static void evaluate(){
        double imagineTime = 0.9;
        double temp = Calculator.getNorm(g);
        imagineTime /= temp;
        method(imagineTime);
    }

    public static void printVect(double[] v){
        for(int i = 0; i<v.length; i++){
            System.out.print(v[i]+"   ");
        }
        System.out.println("----------");
    }

    public static void method(double imagineTime){
        ArrayList<Double> b1 = new ArrayList<Double>();
        ArrayList<Double> b2 = new ArrayList<Double>();
        ArrayList<Double> a = new ArrayList<Double>();
        double[] y1 = new double[y0.length];
        for(int i = 0; i < y1.length; i++)
            y1[i] = 0.0;
        readMethodNums(b1,b2,a);
        for(int k =0; k<5; k++){
            rkOneStep(imagineTime, b1, b2, a, y1);
        }
        printVect(y0);
    }

    private static void rkOneStep(double imagineTime, ArrayList<Double> b1, ArrayList<Double> b2, ArrayList<Double> a, double[] y1) {
        double[] k1 = new double[y0.length];
        double[] k2 = new double[y0.length];
        for(int i = 0; i < 10; i++){
            Calculator.getr(k1,y0,g,f);//k1

            //k2
            for(int j = 0; j < y0.length; j++){
                double temp = k1[j] * imagineTime * a.get(i);
                y1[j] = y0[j];
                y1[j] += temp;
            }
            Calculator.getr(k2,y1,g,f);

            //y(n+1)
            for(int k = 0; k < y0.length; k++){
                y0[k] += imagineTime * b1.get(i) * k1[k];
                y0[k] += imagineTime * b2.get(i) * k2[k];
            }
        }
    }

    public static void readMethodNums(ArrayList<Double>b1, ArrayList<Double> b2, ArrayList<Double> a){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("20Pi_div_2.dat")));
            String s;
            s = reader.readLine();
            while((s=reader.readLine()) != null){
                s=reader.readLine();
                double x = Double.valueOf(s);
                a.add(x);

                s=reader.readLine();
                x = Double.valueOf(s);
                b1.add(x);

                s=reader.readLine();
                x = Double.valueOf(s);
                b2.add(x);

            }
        }
        catch (FileNotFoundException e){
            System.out.println("FileNotFound");
        }
        catch (IOException e){

        }

    }
    public static void main(String[] args){
        evaluate();
    }
}
