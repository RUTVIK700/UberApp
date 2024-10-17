package com.rutvik.project.uber.uberApp.services;

import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.entities.Wallet;
import com.rutvik.project.uber.uberApp.enums.TranscationMethod;

public interface WalletService {
	
	Wallet addMoney(User user, Double amount,String transactionId,Ride ride,TranscationMethod transcationMethod);
 
	Wallet deductMoneyFromWallet(User user, Double amount,String transactionId,Ride ride,TranscationMethod transcationMethod);
	
	void WithdrawAllMoneyFromWallet();
	
	Wallet findWalletById(Long walletId);
	
	Wallet createNewWallet(User user);
	
	Wallet findByUser(User user);
}
