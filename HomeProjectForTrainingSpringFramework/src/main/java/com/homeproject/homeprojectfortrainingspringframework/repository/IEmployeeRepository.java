package com.homeproject.homeprojectfortrainingspringframework.repository;

import com.homeproject.homeprojectfortrainingspringframework.modals.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

}
