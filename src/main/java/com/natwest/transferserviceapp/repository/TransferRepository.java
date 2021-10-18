package com.natwest.transferserviceapp.repository;

import com.natwest.transferserviceapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transaction,Long> {
}
