package com.homeproject.homeprojectfortrainingspringframework.services;

import com.homeproject.homeprojectfortrainingspringframework.dtos.DepartmentDto;
import com.homeproject.homeprojectfortrainingspringframework.modals.Department;
import com.homeproject.homeprojectfortrainingspringframework.repository.IGenericJpaRepository;
import com.homeproject.homeprojectfortrainingspringframework.utilities.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    IGenericJpaRepository<Department> _department;

    @Autowired
    public void setDao(IGenericJpaRepository<Department> jpaToSet) {
        _department = jpaToSet;
        _department.setClas(Department.class);
    }
    public ResponseEntity<?> CreateDepartment(DepartmentDto departmentDto)
    {
        Department department = Mapper.map(departmentDto,Department.class);
        var result= _department.create(department);
        DepartmentDto resultDto = Mapper.map(result,DepartmentDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
    }
    public ResponseEntity<?> AllDepartment()
    {
        List<Department> department = _department.findAll();
        List<DepartmentDto> departmentDto=Mapper.mapAll(department,DepartmentDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(departmentDto);
    }
    public ResponseEntity<DepartmentDto> FindDepartmentById(int id)
    {
        Department department = _department.findOne(id);
        DepartmentDto departmentDto = Mapper.map(department,DepartmentDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(departmentDto);
    }
    public Department FindDepartmentByIdForOtherServices(int id)
    {
        Department department = _department.findOne(id);
        return department;
    }
    public ResponseEntity<?> UpdateDepartment(int id,DepartmentDto departmentDto)
    {
        Department data = _department.findOne(id);
        Department department= Mapper.map(departmentDto, Department.class);
        data.setDepartmentName(department.getDepartmentName());
        data.setSize(department.getSize());
        _department.update(data);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    public ResponseEntity<?> DeleteDepartment(int id)
    {
        Department department = _department.findOne(id);
        department.setActive(false);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
