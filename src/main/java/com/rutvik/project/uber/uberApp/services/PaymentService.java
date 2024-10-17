package com.rutvik.project.uber.uberApp.services;

import com.rutvik.project.uber.uberApp.entities.Payment;
import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.enums.PaymentStatus;

public interface PaymentService {

	void processPayment(Ride ride);
	
	Payment createNewPayment(Ride ride);
	
	void updatePaymentStatus(Payment payment,PaymentStatus paymentStatus);
}
