package com.natwest.transferserviceapp.controller;

import com.natwest.transferserviceapp.model.Status;
import com.natwest.transferserviceapp.model.TransactionInput;
import com.natwest.transferserviceapp.service.TransferService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

/**
 * Controller to initiate amount transfer from source to destination account.
 *
 * @author saumya.shrivastava
 */
@RestController
@RequestMapping("natwest/transfers")
public class TransferController {
    @Autowired
    TransferService transferService;

    @PostMapping(path = "/initiate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> initiateTransfer(@RequestBody TransactionInput input) throws Exception {

        Status status = transferService.transferAmount(input);

        String txStatus = status.toString();
        String message= txStatus.toString();
        if(txStatus.equals(status.SUCESS.toString())){
            message = MessageFormat.format("Successful Transfer of Amount :{0}," +
                            "\n Source AccNum: {1} to Dest AccNum: {2}", input.getAmount(),
                    input.getSourceAccountNumber().toString(),input.getDestAccountNumber().toString());
        }

        ResponseEntity<Object> respObj = new ResponseEntity<>(message
                ,  HttpStatus.OK);

        return respObj;
    }
}
