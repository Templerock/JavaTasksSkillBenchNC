package ru.ncedu.java.tasks;

/**
 * Created by Templerock on 23.07.2017.
 */
public class EmployeeImpl implements Employee {
    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void increaseSalary(int value) {
        salary = salary + value;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {
        if(this.manager == null){
            return "No manager";
        }   else return manager.getFullName();
    }

    @Override
    public Employee getTopManager() {
        if(this.manager==null){
            return this;
        }   else{
            return manager.getTopManager();
        }
    }

    private int salary = 1000;
    private String firstName;
    private String lastName;
    Employee manager;
}
