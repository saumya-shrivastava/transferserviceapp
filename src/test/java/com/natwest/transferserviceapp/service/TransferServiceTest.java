package com.natwest.transferserviceapp.service;

import com.natwest.transferserviceapp.exception.TransferServiceException;
import com.natwest.transferserviceapp.model.Account;
import com.natwest.transferserviceapp.model.Status;
import com.natwest.transferserviceapp.model.TransactionInput;
import com.natwest.transferserviceapp.repository.AccountRepository;
import com.natwest.transferserviceapp.repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @InjectMocks
    private TransferService transferService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        Account sourceAccount = new Account(78901234l, "SAVINGS");
        sourceAccount.deposit(1000.90);
        Account targetAccount = new Account(48573590L, "CURRENT");

        when(accountService.getAccountDetails(78901234l))
                .thenReturn((sourceAccount));
        when(accountService.getAccountDetails(48573590L))
                .thenReturn((targetAccount));
    }
    @Test
    void whenCorrectDetails_thenSuccessfulTransaction() throws Exception {
        Account sourceAccount = new Account(78901234l, "SAVINGS");
        Account targetAccount = new Account(48573590L, "CURRENT");


        TransactionInput input = new TransactionInput();
        input.setSourceAccountNumber(sourceAccount.getAccountNumber());
        input.setDestAccountNumber(targetAccount.getAccountNumber());
        input.setAmount(5.0);
        input.setReference("test Reference");

        Status isComplete = transferService.transferAmount(input);

        assertTrue (isComplete.equals(Status.SUCESS));
    }

    @Test
    void whenAmountLargerThanBalance_thenEXCEPTION() {


        Account sourceAccount = new Account(78901234l, "SAVINGS");
        Account targetAccount = new Account(48573590L, "CURRENT");

        TransactionInput input = new TransactionInput();
        input.setSourceAccountNumber(sourceAccount.getAccountNumber());
        input.setDestAccountNumber(targetAccount.getAccountNumber());
        input.setAmount(500000000.0);
        input.setReference("test Reference");

        Exception exception = assertThrows(TransferServiceException.class, () -> {
            transferService.transferAmount(input);
        });
        String expectedMessage = "Insufficent Balance in source account";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}