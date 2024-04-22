package pro.sky.stream_api_optional_2_8.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.stream_api_optional_2_8.model.Employee;
import pro.sky.stream_api_optional_2_8.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return employeeService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getEmployeeWithMinSalary(departmentId);
    }


    @GetMapping("/all-by-departments")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartments() {
        return employeeService.getAllEmployeesByDepartments();
    }

    @PostMapping("/add-employee")
    public Employee addEmployee(@RequestParam String name, @RequestParam Integer department, @RequestParam Integer salary) {
        return employeeService.addEmployee(name, department, salary);
    }

    @DeleteMapping("/remove-employee")
    public Employee removeEmployee(@RequestParam String name, @RequestParam Integer department, @RequestParam Integer salary) {
        return employeeService.removeEmployee(name, department, salary);
    }

    @GetMapping("/find-employee")
    public Employee findEmployee(@RequestParam String name, @RequestParam Integer department, @RequestParam Integer salary) {
        return employeeService.findEmployee(name, department, salary);
    }

    @GetMapping("/all-employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}





