package com.homeproject.homeprojectfortrainingspringframework.repository;

import com.homeproject.homeprojectfortrainingspringframework.modals.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("select t from Employee t where t.employeeIdentityNo=:tcNo")
    Optional<Employee> findEmployeeByTcNo(String tcNo);

}
