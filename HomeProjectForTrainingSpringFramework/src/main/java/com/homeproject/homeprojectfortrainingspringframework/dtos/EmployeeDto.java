package com.homeproject.homeprojectfortrainingspringframework.dtos;

import com.homeproject.homeprojectfortrainingspringframework.modals.Department;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto {
    private String employeeIdentityNo;
    private String Name;
    private String Surname;
    private String Address;
    private String Email;
    private String Country;
    private String Phone;
    private Date BirthDate;
    private Date JobStartDate;
    private Integer DepartmentId;
    private int totalCash;

}
