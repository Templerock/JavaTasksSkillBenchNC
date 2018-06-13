package ru.ncedu.java.tasks;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Templerock on 20.10.2017.
 */
public class BusinessCardImpl implements  BusinessCard{
    String firstName;
    String lastName;
    String birthDate;
    private Calendar birthDateCalendarFormat;
    char gender;
    String department;
    int salary;
    String phoneNumber;

    public BusinessCardImpl(){}

    BusinessCardImpl(String name, String lastName, String department, String birthDate, char gender, int salary, String phoneNumber, Calendar brthDate){
        this.firstName = name;
        this.lastName = lastName;
        this.department = department;
        this.birthDate = birthDate;
        this.gender = gender;
        this.salary = salary;
        this.birthDateCalendarFormat = brthDate;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public BusinessCard getBusinessCard(Scanner scanner) {
        Calendar brthDate = null;
        String firstName = null;
        String lastName = null;
        String department = null;
        String phoneNumber = null;
        String birthDate = null;
        int salary = 0;
        char gender = 'z';

        scanner.useDelimiter(";");

        if (scanner.hasNext()){
            firstName = scanner.next();
        }
        if (scanner.hasNext()){
            lastName = scanner.next();
        }
        if (scanner.hasNext()){
            department = scanner.next();
        }
        if (scanner.hasNext()){
            birthDate = scanner.next();
            brthDate = this.ValidDate(birthDate, "DD-MM-YYYY");
            if ( brthDate == null ){
                throw new InputMismatchException();
            }
        }
        if (scanner.hasNext()){
            String genderArg = scanner.next();
            if(genderArg.equals('F')){
                gender = genderArg.charAt(0);
            }
            if (genderArg.equals('M')){
                gender = genderArg.charAt(0);
            }   else {
                throw new InputMismatchException();
            }
        }
        if (scanner.hasNext()){
            String numb = scanner.next();
            int salaryArg;
            try{
                salaryArg = Integer.parseInt(numb);
            }catch(NumberFormatException nfe){
                throw new InputMismatchException();
            }
            if (salaryArg < 100 || salaryArg > 100000 )
                throw new InputMismatchException();
            salary = salaryArg;
        }
        if (scanner.hasNext()){
            String numb = scanner.next();
            if(numb.length() != 10){
                throw new InputMismatchException();
            }   else {
                phoneNumber = numb.substring(0,10);
            }
        }

        return new BusinessCardImpl(firstName, lastName, department, birthDate, gender, salary, phoneNumber, brthDate);
    }

    @Override
    public String getEmployee() {
        return firstName + " " + this.lastName;
    }

    @Override
    public String getDepartment() {
        return this.department;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        long currentTime = System.currentTimeMillis();
        calendar.setTimeInMillis(currentTime);
        return calendar.get(Calendar.YEAR) - this.birthDateCalendarFormat.get(Calendar.YEAR);
    }

    @Override
    public String getGender() {
        return (this.gender == 'F')?("Female"):("Male");
    }

    @Override
    public String getPhoneNumber() {
        String number = this.phoneNumber;
        return "+7 "+ number.substring(0,3) +
                "-" + number.substring(3,6) +
                "-" + number.substring(6,8) +
                "-" + number.substring(8,10);
    }
    private Calendar ValidDate(String stringToValidate, String format)
    {
        if(stringToValidate == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        Date date;
        try{
            date = sdf.parse(stringToValidate);
        }catch (ParseException e){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
