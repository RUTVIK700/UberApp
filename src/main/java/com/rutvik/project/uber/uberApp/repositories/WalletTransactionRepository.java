package com.rutvik.project.uber.uberApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rutvik.project.uber.uberApp.entities.WalletTranscation;
@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTranscation, Long> {

}
