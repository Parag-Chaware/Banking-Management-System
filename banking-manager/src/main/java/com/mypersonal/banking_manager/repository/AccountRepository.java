package com.mypersonal.banking_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypersonal.banking_manager.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
