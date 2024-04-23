package pro.sky.stream_api_optional_2_8.service;

import org.junit.Test;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeNotFoundException;

import java.util.Collections;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    void setUp() {
        employeeService = mock(EmployeeService.class);
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());
    }

    @Test
    public void getEmployeeWithMinSalaryThrowsException() {
        setUp();
        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeWithMinSalary(1));
        assertEquals("Employee not found", exception.getMessage());
    }
}
