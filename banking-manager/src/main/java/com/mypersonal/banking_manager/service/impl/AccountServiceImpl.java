package com.mypersonal.banking_manager.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.mypersonal.banking_manager.dto.AccountDto;
import com.mypersonal.banking_manager.entity.Account;
import com.mypersonal.banking_manager.mapper.AccountMapper;
import com.mypersonal.banking_manager.repository.AccountRepository;
import com.mypersonal.banking_manager.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository
						.findById(id)
						.orElseThrow(()-> new RuntimeException("Account does not found"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not found"));
		double totalBal = account.getBalance() + amount;
		account.setBalance(totalBal);
		Account updatedBalance = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(updatedBalance);
	}


	@Override
	public AccountDto withdrawal(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not found"));
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insuffiecient Balance");
		}
		
		double totalBal = account.getBalance() - amount;
		account.setBalance(totalBal);
		Account updatedBalance = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(updatedBalance);
	}


	@Override
	public List<AccountDto> getAllAccountInfo() {
		// TODO Auto-generated method stub
		List<Account> accounts = accountRepository.findAll();
		
		return 	accounts.stream()
				.map((account)->AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
		
	}


	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not found"));
		accountRepository.deleteById(id);
		
	}



}
