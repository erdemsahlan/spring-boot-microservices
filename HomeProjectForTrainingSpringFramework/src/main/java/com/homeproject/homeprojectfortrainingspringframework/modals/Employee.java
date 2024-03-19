package com.homeproject.homeprojectfortrainingspringframework.modals;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    private String employeeIdentityNo;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String country;
    private String phone;
    private Date birthDate;
    private Date jobStartDate;
    private boolean isActive;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

}
