package ru.ncedu.java.tasks;

import static java.lang.StrictMath.sin;

/**
 * Created by Templerock on 19.07.2017.
 */
public class ControlFlowStatements1Impl implements ControlFlowStatements1 {
    @Override
    public float getFunctionValue(float x) {
        float f;
        if(x>0){
            f = (float) (2*sin(x));
        }   else{
            f = 6 - x;
        }
        return f;
    }

    @Override
    public String decodeWeekday(int weekday) {
        String string = null;
        switch (weekday){
            case 1 :
                string = "Monday";
                break;
            case 2 :
                string = "Tuesday";
                break;
            case 3 :
                string = "Wednesday";
                break;
            case 4 :
                string = "Thursday";
                break;
            case 5 :
                string = "Friday";
                break;
            case 6 :
                string = "Saturday";
                break;
            case 7 :
                string = "Sunday";
                break;
            default :
                string = "Error";
        }
        return string;
    }

    @Override
    public int[][] initArray(){
        int[][] array = new int[8][5];
        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = i * j;
            }
        }
        return array;
    }

    @Override
    public int getMinValue(int[][] array) {
        int min = array[0][0];
        for(int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length ; j++){
                if(array[i][j] < min){
                    min = array[i][j];
                    System.out.println(array[i][j] + " ");
                    System.out.println("\n");
                }
            }
        }
        return min;
    }

    @Override
    public BankDeposit calculateBankDeposit(double P) {
        BankDeposit bankDeposit = new BankDeposit();
        bankDeposit.amount = 1000;
        while(bankDeposit.amount <= 5000){
            bankDeposit.amount = bankDeposit.amount*(1+(P/100));
            bankDeposit.years++;
        }
        return bankDeposit;
    }
    public ControlFlowStatements1Impl(){
    }
}
