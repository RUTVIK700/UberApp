package com.rutvik.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.Payment;
import com.rutvik.project.uber.uberApp.enums.PaymentStatus;
import com.rutvik.project.uber.uberApp.enums.TranscationMethod;
import com.rutvik.project.uber.uberApp.repositories.PaymentRepository;
import com.rutvik.project.uber.uberApp.services.WalletService;
import com.rutvik.project.uber.uberApp.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//Rider ->100
//Driver->70 ,deduct 30 from drivers wallet
@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
	private final WalletService walletService;
	private final PaymentRepository paymentRepository;
	
	@Override
	public void processPayment(Payment payment) {
		Driver driver=payment.getRide().getDriver();
		
		double platformComission=payment.getAmount()*PLATFORM_COMISSION;
		
		walletService.deductMoneyFromWallet(driver.getUser(), platformComission,null,payment.getRide(),TranscationMethod.RIDE);
		
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
	}

}
