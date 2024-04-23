package pro.sky.stream_api_optional_2_8.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pro.sky.stream_api_optional_2_8.exceptions.ArrayIsFullException;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeAlreadyAddedException;
import pro.sky.stream_api_optional_2_8.model.Employee;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int EMPLOYEE_AMOUNT = 4;

    private final List<Employee> employees = new ArrayList<>(EMPLOYEE_AMOUNT);

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return getAllEmployees().stream()
                .filter((employee) -> employee.getDepartment().equals(departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees(Integer departmentId) {
        return getAllEmployees().stream()
                .filter((employee) -> employee.getDepartment().equals(departmentId))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartments() {
        return getAllEmployees().stream()
                .collect(Collectors.groupingBy((Employee::getDepartment)));
    }


    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return getAllEmployees().stream()
                .filter((employee) -> employee.getDepartment().equals(departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public Employee addEmployee(String name, Integer department, Integer salary) {
        Employee newEmployee = new Employee(name, department, salary);
        getAllEmployees().stream()
                .filter(emp -> emp.equals(newEmployee))
                .findAny()
                .ifPresent(emp -> {
                    throw new EmployeeAlreadyAddedException("Employee " + name +
                            " in department " + department +
                            " with salary " + salary +
                            " is already added");
                });
        if (getAllEmployees().size() >= EMPLOYEE_AMOUNT) {
            throw new ArrayIsFullException("Array is full");
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String name, Integer department, Integer salary) {
        Employee employeeToRemove = new Employee(name, department, salary); // Создание временного объекта для поиска
        boolean removed = employees.removeIf(e -> e.equals(employeeToRemove));
        return removed ?
                employeeToRemove :
                getAllEmployees().stream()
                        .filter(e -> e.equals(employeeToRemove))
                        .findFirst()
                        .orElseThrow(() -> new EmployeeNotFoundException("Employee " + name + " not found"));
    }

    @Override
    public Employee findEmployee(String name, Integer department, Integer salary) {
        Employee employeeToFind = new Employee(name, department, salary); // Создание временного объекта для поиска
        return getAllEmployees().stream()
                .filter(e -> e.equals(employeeToFind))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + name + " not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employees);
    }

}



