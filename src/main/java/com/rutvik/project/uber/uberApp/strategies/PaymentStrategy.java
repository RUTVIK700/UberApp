package com.rutvik.project.uber.uberApp.strategies;

import com.rutvik.project.uber.uberApp.entities.Payment;

public interface PaymentStrategy {
	 Double PLATFORM_COMISSION=0.3;
	void processPayment(Payment payment);
}
