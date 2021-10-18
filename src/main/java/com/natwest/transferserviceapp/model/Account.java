package com.natwest.transferserviceapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Getter
@NoArgsConstructor
@Entity(name = "account")
public class Account {

    @Id
    private Long accountNumber;
    private Double balance;
   String accountType;

    public Account(Long accountNumber,String accountType){
        this.accountNumber=accountNumber;
        this.balance= Double.valueOf(0);
        this.accountType=accountType;
    }

    /**
     * method to withdraw amount if amount is greater than 0 and
     * balance is greater than amount
     * @param amount
     */
    public void withdraw(double amount){
        if(amount > 0 && balance>=amount) {
            this.balance -= amount;
        }
        else {
            //throw exception here that balance in account is lessa than amount
        }
    }

    /**
     * Method to deposit amount if amount is greater than 0
     * @param amount
     */
    public void deposit(double amount){

        if(amount>0) {
            this.balance += amount;
        }

    }

}
