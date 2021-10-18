package com.natwest.transferserviceapp.repository;

import com.natwest.transferserviceapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {


}
