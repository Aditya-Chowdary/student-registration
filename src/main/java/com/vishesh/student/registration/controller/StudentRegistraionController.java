package com.vishesh.student.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishesh.student.registration.util.EmailOtpUtil;
import com.vishesh.student.registration.model.OTP;
import com.vishesh.student.registration.model.Student;
import com.vishesh.student.registration.service.EmailService;
import com.vishesh.student.registration.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishesh.student.registration.util.StudentValidationUtil;

@RestController
@RequestMapping("v1/student")
public class StudentRegistraionController {
	@Autowired
	StudentValidationUtil studentValidationUtil;

	@Autowired
	EmailOtpUtil emailOtpUtil;

	@Autowired
	EmailService emailService;

	@Autowired
	StudentRegistrationService studentRegistrationService;

	@CrossOrigin
	@PostMapping(value = "/register")
	public Student studentRegistration(@RequestBody Student student) throws Throwable {
		List<String> errorMessage = studentValidationUtil.validateStudentBody(student);
		if (!CollectionUtils.isEmpty(errorMessage)) {
			errorMessage.forEach(error -> {
				throw new Error(error);
			});

		}
		studentRegistrationService.processStudentRegistration(student);
		return student;
	}

	@CrossOrigin
	@GetMapping(value = "/details/{id}") 
	public Student studentDetails(@PathVariable("id") Long id) throws Throwable {

		return studentRegistrationService.fetchStudentDetails(id);

	}
	@CrossOrigin
	@PostMapping(value = "/send-otp")
	public String sendOtp(@RequestBody String email) {
    System.out.println("email:"+email);
		String otp = EmailOtpUtil.generateOtp();
		emailService.sendOtpEmail(email, otp);
		studentRegistrationService.sendOtpEmail(email, otp);
		return "OTP sent successfully!";
	}
	
	@CrossOrigin
	@PostMapping(value = "/sendOtpPhoneNumber")
	public String sendOtpPhoneNumber(@RequestBody String phoneNumber) {
		System.out.println("number:"+phoneNumber);
		return studentRegistrationService.sendOtpPhoneNumber(phoneNumber);

	}
	
	

}
