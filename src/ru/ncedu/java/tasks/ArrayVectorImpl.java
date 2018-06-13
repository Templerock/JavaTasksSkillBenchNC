package ru.ncedu.java.tasks;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Templerock on 27.07.2017.
 */
public class ArrayVectorImpl implements ArrayVector {
    @Override
    public void set(double... elements) {
        vector = elements;
    }

    @Override
    public double[] get() {
        return vector;
    }

    @Override
    public ArrayVector clone() {
        ArrayVector cloneVector = new ArrayVectorImpl();
        cloneVector.set(get().clone());
        return cloneVector;
    }

    @Override
    public int getSize() {
        return get().length;
    }

    @Override
    public void set(int index, double value) {
        if((index >= 0) && (index < vector.length)){
            vector[index] = value;
        }else if (index >= vector.length){

            vector = Arrays.copyOf(get(), index + 1);
            vector[index] = value;
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 && index > vector.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return vector[index];
    }

    @Override
    public double getMax() {
        double max = vector[0];
        for(int i = 0; i< vector.length; i++){
            if(vector[i] > max){
                max = vector[i];
            }
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = vector[0];
        for(int i = 0; i<vector.length; i++){
            if(vector[i] < min){
                min = vector[i];
            }
        }
        return min;
    }

    @Override
    public void sortAscending() {
        double temp;
        for(int j = 0; j < get().length; j++){
            for(int i = 0; i < get().length - j - 1; i++){
                if(get()[i] > get()[i+1]){
                    temp = get()[i];
                    get()[i] = get()[i+1];
                    get()[i+1] = temp;

                }
            }
        }
    }

    @Override
    public void mult(double factor) {
        for(int i = 0; i < get().length; i++){
            get()[i] = get()[i]*factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        if (anotherVector.get().length >= get().length) {
            for (int i = 0; i < get().length; i++) {
                get()[i] += anotherVector.get()[i];
            }
        } else {
            for (int i = 0; i < anotherVector.get().length; i++) {
                get()[i] += anotherVector.get()[i];
            }
        }
        ArrayVector arrayVector = this;
        return arrayVector;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double result = 0;
        if (anotherVector.get().length >= get().length) {
            for (int i = 0; i < get().length; i++) {
                result += get(i)*anotherVector.get()[i];
            }
        }else {
            if (vector.length > anotherVector.get().length) {
                for (int i = 0; i <anotherVector.get().length; i++) {
                    result += get(i)*anotherVector.get()[i];
                }
            }
        }
        return result;
    }

    @Override
    public double getNorm() {
        double norm;
        double summ = 0;
        for(int i = 0; i< vector.length;i++){
            summ += vector[i]*vector[i];
        }
        norm = Math.sqrt(summ);
        return norm;
    }

    private double[] vector;
}
