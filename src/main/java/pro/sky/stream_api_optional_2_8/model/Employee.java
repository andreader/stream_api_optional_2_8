package pro.sky.stream_api_optional_2_8.model;

import java.util.Objects;

public class Employee {
    private final String name;
    private Integer department;
    private Integer salary;

    private static int counter;

    public Employee(String name, Integer department, Integer salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(department, employee.department)
                && Objects.equals(salary, employee.salary)
                && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department, salary);
    }
}

