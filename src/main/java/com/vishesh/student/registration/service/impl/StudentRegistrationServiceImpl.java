package com.vishesh.student.registration.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.vishesh.student.registration.model.Student;
import com.vishesh.student.registration.repository.OtpRepository;
import com.vishesh.student.registration.repository.OtpStoreRepository;
import com.vishesh.student.registration.repository.StudentRegistration;
import com.vishesh.student.registration.repository.StudentRegistrationRepository;
import com.vishesh.student.registration.service.StudentRegistrationService;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {
	@Autowired
	StudentRegistrationRepository studentRegistrationRepository;

	@Override
	public Student processStudentRegistration(Student student) {
		StudentRegistration studentRegistration = new StudentRegistration();

		studentRegistration.setFirstName(student.getFirstName());
		studentRegistration.setLastName(student.getLastName());
		studentRegistration.setSchoolName(student.getCollege());
		studentRegistration.setPhoneNumber(new BigDecimal(student.getPhoneNumber()));
		studentRegistration.setEmail(student.getEmail());
		studentRegistration.setLocation(student.getLocation());
		studentRegistration.setClassName(student.getClassName());
		studentRegistration.setCreatedBy("admin");
		studentRegistration.setUpdatedBy("admin");
		studentRegistrationRepository.save(studentRegistration);
		return null;
	}

	@Override
	public Student fetchStudentDetails(Long id) {
		// TODO Auto-generated method stub

		Optional<StudentRegistration> studentDetails = studentRegistrationRepository.findById(new BigDecimal(id));
		Student student = null;
		if (studentDetails.isPresent()) {
			student = new Student();
			StudentRegistration studentResgistration = studentDetails.get();
			student.setFirstName(studentResgistration.getFirstName());
			student.setLastName(studentResgistration.getLastName());
			student.setCollege(studentResgistration.getSchoolName());
			student.setPhoneNumber(String.valueOf(studentResgistration.getPhoneNumber()));
			student.setEmail(studentResgistration.getEmail());
			student.setLocation(studentResgistration.getLocation());
			student.setClassName(studentResgistration.getClassName());
		} else {
			throw new Error("no record found!");
		}
		return student;

	}

	@Value("${twilio.account-sid}")
	private String accountSid;

	@Value("${twilio.auth-token}")
	private String authToken;

	@Value("${twilio.phone-number}")
	private String twilioPhoneNumber;

	@Override
	public String sendOtpPhoneNumber(String phoneNumber) {
		Twilio.init(accountSid, authToken);
		String otp = String.valueOf(new Random().nextInt(900000) + 100000);
		String messageBody = "Your OTP is: " + otp;

		storeOtp(phoneNumber, otp);

		Message.creator(new com.twilio.type.PhoneNumber(phoneNumber),
				new com.twilio.type.PhoneNumber(twilioPhoneNumber), messageBody).create();
		return "OTP generated and sent to mobile";
	}

	@Autowired
	OtpRepository otpRepository;

	public void storeOtp(String source, String otp) {

		OtpStoreRepository storeOtp = new OtpStoreRepository();
		storeOtp.setPhoneNumberOrEmail(source);
		storeOtp.setOtp(otp);
		otpRepository.save(storeOtp);
	}

	@Override
	public String sendOtpEmail(String email,String otp) {
		storeOtp(email, otp);
		return "Stored";
	}
	
	
	@Override
	public String verifyOtp(String phoneNumber, String otp) {
	    Optional<OtpStoreRepository> otpEntityOptional = otpRepository.findByPhoneNumber(phoneNumber);
	    if (otpEntityOptional.isPresent()) {
	    	OtpStoreRepository storedOtpEntity = otpEntityOptional.get();
	        String storedOtp = storedOtpEntity.getOtp();

	        if (storedOtp.equals(otp)) {
	            return "Success";
	        } else {
	            throw new RuntimeException("OTP wrong");
	        }
	    } else {
	        throw new RuntimeException("Invalid phone number");
	    }
	}

}
