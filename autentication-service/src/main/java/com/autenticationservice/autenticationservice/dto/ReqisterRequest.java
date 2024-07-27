package com.autenticationservice.autenticationservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
