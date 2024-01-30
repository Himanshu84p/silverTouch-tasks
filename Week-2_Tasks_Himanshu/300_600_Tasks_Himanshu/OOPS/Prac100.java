import java.util.ArrayList;
import java.util.List;

// Class representing an Employee
class Employee {
    private String name;
    private int employeeId;
    private String designation;

    public Employee(String name, int employeeId, String designation) {
        this.name = name;
        this.employeeId = employeeId;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getDesignation() {
        return designation;
    }
}

// Class representing Salary information
class Salary {
    private double baseSalary;
    private double bonus;

    public Salary(double baseSalary, double bonus) {
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }

    public double calculateTotalSalary() {
        return baseSalary + bonus;
    }
}

// Class representing Payroll, managing employee information and generating pay stubs
class Payroll {
    private List<Employee> employees;
    private List<Salary> salaries;

    public Payroll() {
        this.employees = new ArrayList<>();
        this.salaries = new ArrayList<>();
    }

    public void addEmployee(Employee employee, Salary salary) {
        employees.add(employee);
        salaries.add(salary);
    }

    public void generatePayStubs() {
        System.out.println("Pay Stubs:");
        System.out.println("----------------------------------------");

        for (int i = 0; i < employees.size(); ++i) {
            System.out.println("Employee ID: " + employees.get(i).getEmployeeId());
            System.out.println("Name: " + employees.get(i).getName());
            System.out.println("Designation: " + employees.get(i).getDesignation());
            System.out.println("Base Salary: $" + salaries.get(i).calculateTotalSalary());
            System.out.println("----------------------------------------");
        }
    }
}

public class Prac100 {
    public static void main(String[] args) {
        // Create instances of employees and their salaries
        Employee emp1 = new Employee("John Doe", 101, "Software Engineer");
        Employee emp2 = new Employee("Jane Smith", 102, "Product Manager");

        Salary salary1 = new Salary(60000.0, 5000.0);
        Salary salary2 = new Salary(80000.0, 7000.0);

        // Create Payroll and add employees with their salaries
        Payroll payroll = new Payroll();
        payroll.addEmployee(emp1, salary1);
        payroll.addEmployee(emp2, salary2);

        // Generate pay stubs
        payroll.generatePayStubs();
    }
}
