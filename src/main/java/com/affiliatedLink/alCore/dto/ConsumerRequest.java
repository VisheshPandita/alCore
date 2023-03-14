package com.affiliatedLink.alCore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerRequest {
    @NotBlank
    private String consumerFirstName;
    @NotBlank
    private String consumerLastName;
    @Email
    private String consumerEmail;
}
