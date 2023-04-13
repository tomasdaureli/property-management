package com.mycompany.propertymanagement.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    
    private Long id;
    private String ownerName;
    @NotNull(message = "Owner Email is required.")
    @Size(min = 1, max = 50, message = "Email should be between 1 to 50 characters in length.")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Password is required.")
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
    
}
