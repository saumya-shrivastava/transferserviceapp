package com.natwest.transferserviceapp.service;

import com.natwest.transferserviceapp.constants.ErrorConstants;
import com.natwest.transferserviceapp.exception.TransferServiceException;
import com.natwest.transferserviceapp.model.Account;
import com.natwest.transferserviceapp.model.Status;
import com.natwest.transferserviceapp.model.Transaction;
import com.natwest.transferserviceapp.model.TransactionInput;
import com.natwest.transferserviceapp.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TransferService {

    @Autowired
    AccountService accountService;
    Account sourceAccount;
    Account destAccount;
    @Autowired
    TransferRepository transferRepository;

    public TransferService() {

    }

    public Status  transferAmount(TransactionInput transactionInput) throws Exception {


        isValidTransaction(transactionInput);

        completeTransaction(transactionInput.getAmount(),transactionInput.getReference());

        return Status.SUCESS;
    }

    public void completeTransaction(double amount,String reference){
        sourceAccount.withdraw(amount);
        accountService.save(sourceAccount);
        destAccount.deposit(amount);
        accountService.save(destAccount);

        saveTransactionDetails(amount,reference);

    }

    private void saveTransactionDetails(double amount,String reference) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDestinationAccountNumber(destAccount.getAccountNumber());
        transaction.setSourceAccountNumber(sourceAccount.getAccountNumber());
        transaction.setReference(reference);
        transferRepository.save(transaction);
    }

    public boolean isValidTransaction(TransactionInput transaction) throws Exception {
        validateAccounts(transaction);
        validAmount(transaction.getAmount());

        return true;
    }
    public void validateAccounts(TransactionInput transaction) throws Exception {
        Optional<Account> sourceAccount = Optional.ofNullable(accountService.getAccountDetails(transaction.getSourceAccountNumber()));
        Optional<Account> destAccount = Optional.ofNullable(accountService.getAccountDetails(transaction.getDestAccountNumber()));

        if(!sourceAccount.isPresent() || !destAccount.isPresent()){
            throw new TransferServiceException(ErrorConstants.ERROR_INVALID_ACCOUNT,"Invalid account number. Please check");
        }
        this.sourceAccount= sourceAccount.get();
        this.destAccount = destAccount.get();

    }

    public void validAmount(double amount){
        if(amount<0){
            throw new TransferServiceException(ErrorConstants.ERROR_INVALID_AMOUNT,"Invalid amount. Amount is negative.");
        }
        if(sourceAccount.getBalance() < amount){
            throw new TransferServiceException(ErrorConstants.ERROR_NOT_SUFFICIENT_BALANCE,"Insufficent Balance in source account");
        }

    }
}
