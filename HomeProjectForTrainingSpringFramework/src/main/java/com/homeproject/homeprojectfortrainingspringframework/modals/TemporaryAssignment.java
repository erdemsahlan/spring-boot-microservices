package com.homeproject.homeprojectfortrainingspringframework.modals;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @OneToOne
    private  Employee employee;
    @OneToOne
    private Department department;
    private Date startDate;
    private Date endDate;

}
