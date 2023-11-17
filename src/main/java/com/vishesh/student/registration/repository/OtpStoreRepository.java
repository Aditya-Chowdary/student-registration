package com.vishesh.student.registration.repository;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "otp_store")
public class OtpStoreRepository {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "otp_id")
	private BigDecimal OtpId;

	@Column(name = "phonenumber_email")
	private String phoneNumberOrEmail;

	@Column(name = "otp")
	private String otp;

	public BigDecimal getOtpId() {
		return OtpId;
	}

	public void setOtpId(BigDecimal otpId) {
		OtpId = otpId;
	}

	public String getPhoneNumberOrEmail() {
		return phoneNumberOrEmail;
	}

	public void setPhoneNumberOrEmail(String phoneNumberOrEmail) {
		this.phoneNumberOrEmail = phoneNumberOrEmail;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
