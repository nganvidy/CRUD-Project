package com.example.demo.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
//    @Pattern(regexp = "^\\d{10}$")
    private int senderAccountId;

    private int receiveAccountId;
    @Min(1)
    @Max(1000)
    private float amount;
    @NotBlank (message = "Remark is required !")
    private String remark;
    private Date transferAt;
}
