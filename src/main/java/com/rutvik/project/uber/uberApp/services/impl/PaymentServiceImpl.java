package com.rutvik.project.uber.uberApp.services.impl;

import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.Payment;
import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.enums.PaymentStatus;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.repositories.PaymentRepository;
import com.rutvik.project.uber.uberApp.services.PaymentService;
import com.rutvik.project.uber.uberApp.strategies.PaymentStrategyManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final PaymentStrategyManager paymentStrategyManager;

	@Override
	public void processPayment(Ride ride) {
		Payment payment=paymentRepository.findByRide(ride)
				.orElseThrow(()-> new ResourceNotFoundException("Payment not found for ride "+ride.getId()));
		paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);;
	}

	@Override
	public Payment createNewPayment(Ride ride) {
		Payment payment=Payment.builder()
				.ride(ride)
				.paymentMethod(ride.getPaymentMethod())
				.amount(ride.getFare())
				.paymentStatus(PaymentStatus.PENDING)
				.build();
		return paymentRepository.save(payment);
	}

	@Override
	public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
			payment.setPaymentStatus(paymentStatus);
			paymentRepository.save(payment);
	}
	
}
