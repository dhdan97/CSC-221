package application;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    private List<Employee> lst = new ArrayList<>();

    public List<Employee> getLst() {

        return lst;
    }

    public void createNew() {

        lst.add(new Employee());
    }
    
    public void createNew(Employee newEmployee) {//added by me
    	lst.add(newEmployee);
    }

    public void setLst(List<Employee> lst) {

        this.lst = lst;
    }
}