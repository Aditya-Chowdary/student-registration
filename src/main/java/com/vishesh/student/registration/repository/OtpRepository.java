package com.vishesh.student.registration.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OtpRepository extends CrudRepository<OtpStoreRepository, BigDecimal> {
	@Query(value = "SELECT * FROM otp_store WHERE phonenumber_email = :phoneNumber", nativeQuery = true)
	Optional<OtpStoreRepository> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
