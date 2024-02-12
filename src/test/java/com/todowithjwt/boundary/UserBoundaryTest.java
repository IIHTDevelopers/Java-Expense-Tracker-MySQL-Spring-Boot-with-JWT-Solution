package com.todowithjwt.boundary;

import static com.todowithjwt.utils.TestUtils.boundaryTestFile;
import static com.todowithjwt.utils.TestUtils.currentTest;
import static com.todowithjwt.utils.TestUtils.testReport;
import static com.todowithjwt.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.todowithjwt.dto.UserDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserBoundaryTest {

	private static Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testIdNotNull() throws IOException {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(0);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameNotBlank() throws IOException {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("");
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testEmailNotBlank() throws IOException {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("");
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPasswordNotBlank() throws IOException {
		UserDTO userDTO = new UserDTO();
		userDTO.setPassword("");
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}