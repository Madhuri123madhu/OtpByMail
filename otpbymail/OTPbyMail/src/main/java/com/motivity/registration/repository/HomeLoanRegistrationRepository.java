package com.motivity.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.motivity.registration.model.HomeLoanRegistration;

@Repository
public interface HomeLoanRegistrationRepository extends JpaRepository<HomeLoanRegistration, Long>{

	@Query(value = "SELECT * from Home_Loans where is_verified=false",nativeQuery = true)
	HomeLoanRegistration findByIsVerified();
	
}
