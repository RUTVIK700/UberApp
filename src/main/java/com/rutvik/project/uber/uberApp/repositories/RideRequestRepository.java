package com.rutvik.project.uber.uberApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rutvik.project.uber.uberApp.entities.RideRequest;
@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest,Long> {

}
