import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

class Manager extends Employee {
    private List<Employee> subordinates;

    public Manager(String name, double salary) {
        super(name, salary);
        this.subordinates = new ArrayList<>();
    }

    public void addSubordinate(Employee employee) {
        subordinates.add(employee);
    }

    public void removeSubordinate(Employee employee) {
        subordinates.remove(employee);
    }

    public List<Employee> getSubordinates() {
        return new ArrayList<>(subordinates);
    }
}

class Department {
    private String name;
    private Manager manager;
    private List<Employee> employees;

    public Department(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public double calculateTotalSalaries() {
        double totalSalaries = manager.getSalary();
        for (Employee employee : employees) {
            totalSalaries += employee.getSalary();
        }
        return totalSalaries;
    }

    public void promoteEmployee(Employee employee, double salaryIncrease) {
        employee.setSalary(employee.getSalary() + salaryIncrease);
    }
}

public class Prac63_EmployeeManagementSystem {
    public static void main(String[] args) {
        // Create employees
        Employee employee1 = new Employee("John", 50000);
        Employee employee2 = new Employee("Alice", 45000);

        // Create manager
        Manager manager = new Manager("Manager Bob", 80000);
        manager.addSubordinate(employee1);
        manager.addSubordinate(employee2);

        // Create department
        Department department = new Department("Engineering", manager);
        department.addEmployee(employee1);
        department.addEmployee(employee2);

        // Calculate total salaries in the department
        double totalSalaries = department.calculateTotalSalaries();
        System.out.println("Total Salaries in " + department.getName() + ": $" + totalSalaries);

        // Promote employee1
        department.promoteEmployee(employee1, 5000);
        System.out.println(employee1.getName() + "'s new salary: $" + employee1.getSalary());
    }
}
