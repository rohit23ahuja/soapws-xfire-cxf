package dev.rohitahuja;

import dev.rohitahuja.dao.EmployeeDao;

public class App {
    public static void main( String[] args ) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.insert("d", "d@gmail.com");
    }
}
