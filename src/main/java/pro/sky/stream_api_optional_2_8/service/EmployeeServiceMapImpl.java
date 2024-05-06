package pro.sky.stream_api_optional_2_8.service;

import org.springframework.stereotype.Service;
import pro.sky.stream_api_optional_2_8.exceptions.ArrayIsFullException;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeAlreadyAddedException;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeNotFoundException;
import pro.sky.stream_api_optional_2_8.exceptions.InvalidNameException;
import pro.sky.stream_api_optional_2_8.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isAlpha;
import static pro.sky.stream_api_optional_2_8.utils.Constants.*;


@Service
public class EmployeeServiceMapImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>(EMPLOYEE_AMOUNT);

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
        validateNames(name);

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

        employees.put(name, newEmployee);
        return newEmployee;
    }

    private void validateNames(String... names) {
        for (String name : names) {
            if (!isAlpha(name)) {
                throw new InvalidNameException(name);
            }
        }
    }

    @Override
    public Employee removeEmployee(String name, Integer department, Integer salary) {
        Employee employeeToRemove = new Employee(name, department, salary);
        if (!employees.get(name).equals(employeeToRemove)) {
            throw new EmployeeNotFoundException("Employee " + name +
                    " in department " + department +
                    " with salary " + salary +
                    " not found");
        } else {
            return employees.remove(name);
        }
    }

    @Override
    public Employee findEmployee(String name, Integer department, Integer salary) {
        Employee employeeToFind = new Employee(name, department, salary); // Создание временного объекта для поиска
        return getAllEmployees().stream()
                .filter(e -> e.equals(employeeToFind))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + name +
                        " in department " + department +
                        " with salary " + salary +
                        " not found"));
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

}



