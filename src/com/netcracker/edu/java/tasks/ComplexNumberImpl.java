package com.netcracker.edu.java.tasks;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.Math.pow;

/**
 * Created by Templerock on 23.07.2017.
 */
public class ComplexNumberImpl implements ComplexNumber {

    @Override
    public double getRe() {
        return re;
    }

    @Override
    public double getIm() {
        return im;
    }

    @Override
    public boolean isReal() {
        if(getIm() == 0.0){
            return true;
        }
        return false;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        String re;
        String im;
        double a = 0;
        double b = 0;
            for (int i = 0; i < value.length(); i++) {
            char v = value.charAt(i);
            if (!(v == '0' || v == '1' || v == '2' || v == '3' || v == '4' || v == '5' || v == '6' || v == '7' || v == '8' || v == '9' || v == 'i' || v == '+' || v == '-' || v=='.')) {
                throw new NumberFormatException();
            }

            if (((value.lastIndexOf("+") == 0 || value.lastIndexOf("+") == -1) && (value.lastIndexOf("-") == 0 || value.lastIndexOf("-") == -1))) {
                if (value.lastIndexOf("i") != -1 && value.lastIndexOf("i") != value.length() - 1) {
                    throw new NumberFormatException();
                } else {
                    if (value.lastIndexOf("i") != -1) {
                        if (value.lastIndexOf("i") != 1 && value.lastIndexOf("i") != 0) {
                            a = Double.parseDouble(value.substring(0, value.length() - 1));
                            this.im = a;
                            this.re = b;
                        } else {
                            if (value.lastIndexOf("i") == 0) {
                                a = 1;
                                this.im = a;
                                this.re = b;
                            } else {
                                if (!(value.startsWith("+") || value.startsWith("-"))) {
                                    a = Double.parseDouble(value.substring(0, value.length() - 1));
                                    this.im = a;
                                    this.re = b;
                                } else {
                                    if (value.startsWith("+")) {
                                        a = 1;
                                        this.im = a;
                                        this.re = b;
                                    } else {
                                        a = -1;
                                        this.im = a;
                                        this.re = b;
                                    }
                                }
                            }
                        }
                    } else {
                        b = Double.parseDouble(value.substring(0, value.length()));
                        this.re = b;
                        this.im = a;
                    }
                }
            } else {
                if (value.lastIndexOf("+") != -1 && value.lastIndexOf("+") != 0) {
                    re = value.substring(0, value.lastIndexOf("+"));
                    im = value.substring(value.lastIndexOf("+"), value.length());
                } else {
                    re = value.substring(0, value.lastIndexOf("-"));
                    im = value.substring(value.lastIndexOf("-"), value.length());
                }
                if (!im.endsWith("i")) {
                    throw new NumberFormatException();
                } else {
                    if (im.lastIndexOf("i") != 1 && im.lastIndexOf("i") != 0) {
                        a = Double.parseDouble(im.substring(0, im.length() - 1));
                        this.im = a;
                    } else {
                        if (im.lastIndexOf("i") == 0) {
                            a = 1;
                            this.im = a;
                        } else {
                            if (!(im.startsWith("+") || im.startsWith("-"))) {
                                a = Double.parseDouble(im.substring(0, im.length() - 1));
                                this.im = a;
                            } else {
                                if (im.startsWith("+")) {
                                    a = 1;
                                    this.im = a;
                                } else {
                                    a = -1;
                                    this.im = a;
                                }
                            }
                        }
                    }
                }
                this.re = Double.parseDouble(re);
            }
        }
    }

    @Override
    public ComplexNumberImpl copy() {
        ComplexNumberImpl copy = new ComplexNumberImpl();
        copy.re = this.getRe();
        copy.im = this.getIm();
        return copy;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        ComplexNumberImpl clone  = (ComplexNumberImpl) super.clone();{
            return clone;
        }
    }

    @Override
    public int compareTo(ComplexNumber other) {
        double comparableObj;
        double otherObj;
        comparableObj = pow(this.getRe(),2) + pow(this.getIm(),2);
        otherObj = pow(other.getRe(),2) + pow(other.getIm(),2);
        if (comparableObj > otherObj){
            return 1;
        }
        if (comparableObj < otherObj){
            return -1;
        }   else {
            return 0;
        }
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    @Override
    public ComplexNumber negate() {
        ComplexNumberImpl negate = this;
        negate.re = -this.getRe();
        negate.im = -this.getIm();
        return negate;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        ComplexNumberImpl add = this;
        add.re = this.getRe() + arg2.getRe();
        add.im = this.getIm() + arg2.getIm();
        return add;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        ComplexNumberImpl multiply = this;
        double a = this.getRe();
        double b = this.getIm();
        double c = arg2.getRe();
        double d = arg2.getIm();
        multiply.re = a*c - b*d;
        multiply.im = b*c + a*d;
        return multiply;
    }

    @Override
    public String toString(){
        ComplexNumberImpl string;
        string = this;
        if ((string.getRe() != 0 && string.getIm() == 0) || (string.getRe() == 0 && string.getIm() == 0)){
            return string.getRe() + "";
        }
        if (string.getRe() == 0 && string.getIm() != 0){
            return string.getIm() + "i";
        }
        else {
            if (string.getIm() < 0){
                return string.getRe() + "" + string.getIm() + "i";
            }   else {
                return string.getRe() + "+" + string.getIm() + "i";
            }
        }
    }

    @Override
    public boolean equals(Object other){
    if (other instanceof ComplexNumber && (this.getRe() == ((ComplexNumber) other).getRe() && this.getIm() == ((ComplexNumber) other).getIm())){
        return true;
    }   else
        return false;
    }

    private double re;
    private double im;

    public ComplexNumberImpl(){
    }

    public ComplexNumberImpl(String value){
        ComplexNumberImpl temp = this;
        temp.set(value);
    }

    public ComplexNumberImpl(double re, double im){
        this.re = re;
        this.im = im;
    }
}
