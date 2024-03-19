package com.homeproject.homeprojectfortrainingspringframework.services;

import com.homeproject.homeprojectfortrainingspringframework.dtos.DepartmentDto;
import com.homeproject.homeprojectfortrainingspringframework.dtos.EmployeeDto;
import com.homeproject.homeprojectfortrainingspringframework.modals.Department;
import com.homeproject.homeprojectfortrainingspringframework.modals.Employee;
import com.homeproject.homeprojectfortrainingspringframework.repository.IEmployeeRepository;
import com.homeproject.homeprojectfortrainingspringframework.utilities.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final IEmployeeRepository _employeeRepository;
    private final DepartmentService _departmentService;


    public EmployeeService(IEmployeeRepository employeeRepository, DepartmentService departmentService) {
        _employeeRepository = employeeRepository;
        _departmentService = departmentService;
    }

    public ResponseEntity<List<EmployeeDto>> GetALlEmployee()
    {
        List<Employee> employee = _employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList=Mapper.mapAll(employee, EmployeeDto.class);
        return  ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);
    }

    public ResponseEntity<EmployeeDto> CreateEmployee(EmployeeDto employeeDto)
    {
        Employee employee=Mapper.map(employeeDto, Employee.class);
        var result=_employeeRepository.save(employee);
        EmployeeDto resultDto=Mapper.map(result, EmployeeDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
    }

    public  ResponseEntity<EmployeeDto> GetEmployeeById(int id) {
        Employee employee=_employeeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("{id}"));
        EmployeeDto employeeDto=Mapper.map(employee,EmployeeDto.class);
        return  ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }
    public ResponseEntity<?> UpdateEmployee(int id, @RequestBody EmployeeDto employeeDto)
    {
        Employee employee = _employeeRepository.findById(id).orElseThrow();
        Department department = _departmentService.FindDepartmentByIdForOtherServices(employeeDto.getDepartmentId());
        Employee employeeUpdateData=Mapper.map(employeeDto,Employee.class);
        employee.setName(employeeUpdateData.getName());
        employee.setSurname(employeeUpdateData.getSurname());
        employee.setCountry(employeeUpdateData.getCountry());
        employee.setPhone(employeeUpdateData.getPhone());
        employee.setEmail(employeeUpdateData.getEmail());
        employee.setAddress(employeeUpdateData.getAddress());
        employee.setDepartment(department);
        _employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    public ResponseEntity<?> DeleteEmployee(int id)
    {
        Employee employee=_employeeRepository.findById(id).orElseThrow();
        employee.setActive(false);
        _employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }


}
