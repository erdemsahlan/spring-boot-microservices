package com.homeproject.homeprojectfortrainingspringframework.modals;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String DepartmentName;
    private int size;
    private boolean isActive;
    @OneToMany()
    private List<Employee> Employees;

}
