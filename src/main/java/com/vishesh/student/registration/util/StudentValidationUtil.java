package com.vishesh.student.registration.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vishesh.student.registration.model.Student;

import io.micrometer.common.util.StringUtils;

@Component
public class StudentValidationUtil {

	public List<String> validateStudentBody(Student student) {
		List<String> errorMessage = new ArrayList<>();
		if (StringUtils.isEmpty(student.getFirstName())){
			errorMessage.add("First name is required");
		}
		if (StringUtils.isEmpty(student.getLastName())) {
			errorMessage.add("Last name is required");
		}
		if (StringUtils.isEmpty(student.getCollege())) {
			errorMessage.add("College name is required");
		}
		if (StringUtils.isEmpty(String.valueOf(student.getPhoneNumber()))){
			errorMessage.add("Phone Number is required");
		}
		if (StringUtils.isEmpty(student.getEmail())) {
			errorMessage.add("Email is required");
		}
		
	return errorMessage;
	}

}
