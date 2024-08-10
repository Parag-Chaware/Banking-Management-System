package com.mypersonal.banking_manager.service;

import java.util.List;

import com.mypersonal.banking_manager.dto.AccountDto;

public interface AccountService {
	
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdrawal(Long id, double amount);
	
	List<AccountDto> getAllAccountInfo();
	
	void deleteAccount(Long id);
}
