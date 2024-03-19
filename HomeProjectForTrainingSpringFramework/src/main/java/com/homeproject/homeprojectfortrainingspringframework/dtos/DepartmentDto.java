package com.homeproject.homeprojectfortrainingspringframework.dtos;

import com.homeproject.homeprojectfortrainingspringframework.modals.Employee;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private String departmentName;
    private int size;
    @OneToMany
    private List<Employee> employees;
}
