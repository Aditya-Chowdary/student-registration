package com.vishesh.student.registration.service;

import com.vishesh.student.registration.model.Student;

public interface StudentRegistrationService {
public Student processStudentRegistration(Student student);

public Student fetchStudentDetails(Long id);

public String sendOtpPhoneNumber(String phoneNumber);

public String sendOtpEmail(String email, String otp);
public String verifyOtp(String phoneNumber, String otp);

}
