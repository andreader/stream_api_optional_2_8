package pro.sky.stream_api_optional_2_8.service;

import pro.sky.stream_api_optional_2_8.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Map<Integer, List<Employee>> getAllEmployeesByDepartments();

    Employee getEmployeeWithMaxSalary(Integer departmentId);

    Employee getEmployeeWithMinSalary(Integer departmentId);

    List<Employee> getAllEmployees(Integer departmentId);

    Employee addEmployee(String name, Integer department, Integer salary);

    Employee removeEmployee(String name);

    Employee findEmployee(String name);

    Collection<Employee> getAllEmployees();
}
