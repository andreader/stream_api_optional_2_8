package pro.sky.stream_api_optional_2_8.service;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeNotFoundException;
import pro.sky.stream_api_optional_2_8.model.Employee;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Spy
    EmployeeServiceImpl employeeService;

    @Test
    void test1() {
        //given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("22", 1, 100);
        employees.add(employee);
        //when
        when(employeeService.getAllEmployees()).thenReturn(employees);
        //then
        Employee actualEmployee = employeeService.getEmployeeWithMinSalary(1);
        assertEquals(employee, actualEmployee);
    }

    @Test
    void test2() {
        //given
        List<Employee> employees = new ArrayList<>();
        //when
        when(employeeService.getAllEmployees()).thenReturn(employees);
        //then
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeWithMinSalary(1));
        assertEquals("Employee not found", exception.getMessage());
    }
}
