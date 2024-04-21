package pro.sky.stream_api_optional_2_8.service;

import pro.sky.stream_api_optional_2_8.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee getEmployeeWithMaxSalary(Integer departmentId);

    Employee getEmployeeWithMinSalary(Integer departmentId);

    List<Employee> getAllEmployees(Integer departmentId);

    Map<Integer, List<Employee>> getAllEmployeesByDepartments(Integer departmentId);

    Employee addEmployee(String name, Integer department, Integer salary);

    Employee removeEmployee(String name, Integer department, Integer salary);

    Employee findEmployee(String name, Integer department, Integer salary);

    List<Employee> getAllEmployees();
}
