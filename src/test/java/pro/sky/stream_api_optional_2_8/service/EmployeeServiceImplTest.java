package pro.sky.stream_api_optional_2_8.service;

import org.junit.jupiter.api.*;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeNotFoundException;
import pro.sky.stream_api_optional_2_8.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class);
    }

    @Test
    public void getEmployeeWithMinSalaryThrowsException() {
        // given
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        // when
        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeWithMinSalary(1));

        // then
        assertEquals("Employee not found", exception.getMessage());
    }

    @Test
    public void getEmployeeWithMinSalaryReturnsCorrectEmployee() {
        // given
        List<Employee> employees = Arrays.asList(
                new Employee("John", 1, 50000),
                new Employee("Alice", 1, 60000),
                new Employee("Bob", 1, 45000)
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // when
        Employee result = employeeService.getEmployeeWithMinSalary(1);

        // then
        assertEquals("Bob", result.getName());
        assertEquals(1, result.getDepartment());
        assertEquals(45000, result.getSalary());
    }


    @Test
    void getAllEmployees() {
    }

    @Test
    void getAllEmployeesByDepartments() {
    }

    @Test
    void getEmployeeWithMaxSalary() {
    }

    @Test
    void addEmployee() {
    }

    @Test
    void removeEmployee() {
    }

    @Test
    void findEmployee() {
    }

    @Test
    void testGetAllEmployees() {
    }
}