package com.homeproject.homeprojectfortrainingspringframework.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.homeproject.homeprojectfortrainingspringframework.dtos.DepartmentDto;
import com.homeproject.homeprojectfortrainingspringframework.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService _departmentService;

    public DepartmentController(DepartmentService departmentService) {
        _departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> GetAllDepartment()
    {
        return _departmentService.AllDepartment();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetDepartmentById(@PathVariable int id)
    {
        return _departmentService.FindDepartmentById(id);
    }
    @PostMapping("/create")
    public ResponseEntity<?> CreateDepartment(@RequestBody DepartmentDto departmentDto)
    {
        return _departmentService.CreateDepartment(departmentDto);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> UpdateDepartment(@PathVariable int id, @RequestBody DepartmentDto departmentDto)
    {
        return _departmentService.UpdateDepartment(id,departmentDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteDepartment(@PathVariable int id)
    {
        return _departmentService.DeleteDepartment(id);
    }


}
