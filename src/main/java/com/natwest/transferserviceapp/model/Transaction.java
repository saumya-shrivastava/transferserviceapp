package com.natwest.transferserviceapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity(name="transaction")
@SequenceGenerator(name = "transaction_seq", allocationSize = 1,initialValue = 5)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Long transactionId;
    private Long sourceAccountNumber;
    private Long destinationAccountNumber;
    private Double amount;
    private String reference;

}
