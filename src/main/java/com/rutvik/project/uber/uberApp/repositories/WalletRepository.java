package com.rutvik.project.uber.uberApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.entities.Wallet;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	Optional<Wallet> findByUser(User user);

}
