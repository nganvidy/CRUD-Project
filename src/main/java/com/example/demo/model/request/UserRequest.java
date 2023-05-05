package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors(chain = true)


public class UserRequest {
    @NotBlank(message = "Username is required !")
    private String username;
    @NotBlank(message = "Gender is required !")
    private String gender;
    private String address;
}
