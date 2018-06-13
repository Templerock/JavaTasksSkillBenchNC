package ru.ncedu.java.tasks;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * Created by Templerock on 20.07.2017.
 */
public class ControlFlowStatements3Impl implements ControlFlowStatements3 {
    @Override
    public double getFunctionValue(double x) {
        double f = 0;
        if(x <= 0){
            f = -x;
        }
        if(x >= 2){
            f = 4;
        } if(0< x && x < 2){
            f = pow(x,2);
        }
        return f;
    }

    @Override
    public String decodeSeason(int monthNumber) {
        String string = null;
        switch (monthNumber){
            case 12:
            case 1:
            case 2:
                string = "Winter";
                break;
            case 3:
            case 4:
            case 5:
                string = "Spring";
                break;
            case 6:
            case 7:
            case 8:
                string = "Summer";
                break;
            case 9:
            case 10:
            case 11:
                string = "Autumn";
                break;
            default:
                string = "Error";
        }
        return string;
    }

    @Override
    public long[][] initArray() {
        long [][] array = new long[8][5];
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = (long) pow(abs(i-j),5);
            }
        }
        return array;
    }

    @Override
    public int getMaxProductIndex(long[][] array) {
        long max = 1;
        long m;
        int k = 0;
        for (int n = 0; n <array[0].length; n++) {
            max = max * array[0][n];
        }
            for (int i = 0; i < array.length; i++) {
                m = 1;
                for (int j = 0; j < array[i].length; j++) {
                    m = m * array[i][j];
                }
                if (abs(max) < abs(m)) {
                    max = m;
                    k = i;
                }
            }
            return k;
        }

    @Override
    public float calculateLineSegment(float A, float B) {
        float c = 0;
        while (c <= A){
            c += B;
        }
        return A - c + B;
    }
}
