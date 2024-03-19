package com.homeproject.homeprojectfortrainingspringframework.controllers;


import com.homeproject.homeprojectfortrainingspringframework.dtos.EmployeeDto;
import com.homeproject.homeprojectfortrainingspringframework.modals.Employee;
import com.homeproject.homeprojectfortrainingspringframework.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
private final  EmployeeService _employeeService;

    public EmployeeController(EmployeeService _employeeService) {
        this._employeeService = _employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> GetAllEmployees()
    {
        return _employeeService.GetALlEmployee();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> GetEmployee(@PathVariable int id)
    {
        return _employeeService.GetEmployeeById(id);
    }
    @GetMapping("/create")
    public ResponseEntity<?> CreateEmployee(@RequestBody EmployeeDto employeeDto)
    {
        return _employeeService.CreateEmployee(employeeDto);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdateEmployee(@PathVariable int id,@RequestBody EmployeeDto employeeDto)
    {
        return _employeeService.UpdateEmployee(id,employeeDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteEmployee(@PathVariable int id)
    {
        return _employeeService.DeleteEmployee(id);
    }

}
