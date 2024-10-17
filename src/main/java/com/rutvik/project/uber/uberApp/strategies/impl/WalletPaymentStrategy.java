package com.rutvik.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.Payment;
import com.rutvik.project.uber.uberApp.entities.Rider;
import com.rutvik.project.uber.uberApp.enums.PaymentStatus;
import com.rutvik.project.uber.uberApp.enums.TranscationMethod;
import com.rutvik.project.uber.uberApp.repositories.PaymentRepository;
import com.rutvik.project.uber.uberApp.services.WalletService;
import com.rutvik.project.uber.uberApp.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//Rider had 232, Driver had 500
//Ride cost 100,comission =30
//Rider->232-100=132
//Driver ->500+(100-30)=570
@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
	private final WalletService walletService;
	private final PaymentRepository paymentRepository;

	@Override
	@Transactional
	public void processPayment(Payment payment) {
		Driver driver = payment.getRide().getDriver();
		Rider rider = payment.getRide().getRider();

		walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(),
				TranscationMethod.RIDE);

		double driversCut = payment.getAmount() * (1 - PLATFORM_COMISSION);
		walletService.addMoney(driver.getUser(), driversCut, null, payment.getRide(), TranscationMethod.RIDE);
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
	}

}
