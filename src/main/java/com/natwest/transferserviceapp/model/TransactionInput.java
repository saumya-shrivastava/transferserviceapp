package com.natwest.transferserviceapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {
    @NotBlank (message = "Source Account number cannot be blank")
    private Long sourceAccountNumber;
    @NotBlank(message = "Destination Account number cannot be blank")
    private Long destAccountNumber;
    @NotBlank(message = "Amount cannot be blank")
    private Double amount;
    private String reference;
}
