package com.rutvik.project.uber.uberApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.WalletTranscation;
import com.rutvik.project.uber.uberApp.repositories.WalletTransactionRepository;
import com.rutvik.project.uber.uberApp.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
	private final WalletTransactionRepository walletTransactionRepository;
	private  final ModelMapper modelMapper;
	@Override
	public void createNewWalletTansaction(WalletTranscation walletTranscation) {
	walletTransactionRepository.save(walletTranscation);
	}

}
