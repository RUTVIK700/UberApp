package com.rutvik.project.uber.uberApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.entities.Wallet;
import com.rutvik.project.uber.uberApp.entities.WalletTranscation;
import com.rutvik.project.uber.uberApp.enums.TranscationMethod;
import com.rutvik.project.uber.uberApp.enums.TranscationType;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.repositories.WalletRepository;
import com.rutvik.project.uber.uberApp.services.WalletService;
import com.rutvik.project.uber.uberApp.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
	private final WalletRepository walletRepository;
	private final ModelMapper modelMapper;
	private final WalletTransactionService walletTransactionService;
	
	@Override
	@Transactional
	public Wallet addMoney(User user, Double amount,String transactionId,Ride ride,TranscationMethod transcationMethod) {
		Wallet wallet=findByUser(user);
		Double updatedAmount= wallet.getBalance()+amount;
		wallet.setBalance(updatedAmount);
		
		WalletTranscation walletTranscation=WalletTranscation.builder()
				.transcationId(transactionId)
				.ride(ride)
				.wallet(wallet)
				.transcationType(TranscationType.CREDIT)
				.transcationMethod(transcationMethod)
				.amount(amount)
				.build();
		walletTransactionService.createNewWalletTansaction(walletTranscation);
		
		return walletRepository.save(wallet);
	}

	@Override
	public void WithdrawAllMoneyFromWallet() {
		// TODO Auto-generated method stub

	}

	@Override
	public Wallet findWalletById(Long walletId) {
		
		return walletRepository.findById(walletId).orElseThrow(()->new ResourceNotFoundException("Wallet does not exists with Id "+walletId));
	}

	@Override
	public Wallet createNewWallet(User user) {
		Wallet wallet=new Wallet();
		wallet.setUser(user);
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet findByUser(User user) {
		
		return walletRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("Wallet does not exists with Id "+user.getId()));
	}

	@Override
	@Transactional
	public Wallet deductMoneyFromWallet(User user, Double amount,String transactionId,Ride ride,TranscationMethod transcationMethod) {
		Wallet wallet=findByUser(user);
		wallet.setBalance(wallet.getBalance()-amount);
		
		WalletTranscation walletTranscation=WalletTranscation.builder()
				.transcationId(transactionId)
				.ride(ride)
				.wallet(wallet)
				.transcationType(TranscationType.DEBIT)
				.transcationMethod(transcationMethod)
				.amount(amount)
				.build();
		
		walletTransactionService.createNewWalletTansaction(walletTranscation);
		
//		wallet.getTranscation().add(walletTranscation);
		
		return walletRepository.save(wallet);
	}

}
