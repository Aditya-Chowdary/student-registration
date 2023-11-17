package com.vishesh.student.registration.model;

import java.math.BigDecimal;

public class OTP {
	private BigDecimal otpId;
	private String phoneNumberOrEmail;
	private String otp;
	
	public BigDecimal getOtpId() {
		return otpId;
	}
	public void setOtpId(BigDecimal otpId) {
		this.otpId = otpId;
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
