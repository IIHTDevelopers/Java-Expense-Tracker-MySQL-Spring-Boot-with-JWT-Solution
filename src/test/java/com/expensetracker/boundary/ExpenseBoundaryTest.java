package com.expensetracker.boundary;

import static com.expensetracker.utils.TestUtils.boundaryTestFile;
import static com.expensetracker.utils.TestUtils.currentTest;
import static com.expensetracker.utils.TestUtils.testReport;
import static com.expensetracker.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.expensetracker.dto.ExpenseDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ExpenseBoundaryTest {

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
	public void testExpenseIdNotNull() throws IOException {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setId(null);
		Set<ConstraintViolation<ExpenseDTO>> violations = validator.validate(expenseDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testExpenseNameNotBlank() throws IOException {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setName("");
		Set<ConstraintViolation<ExpenseDTO>> violations = validator.validate(expenseDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAmountNotNull() throws IOException {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setAmount(null);
		Set<ConstraintViolation<ExpenseDTO>> violations = validator.validate(expenseDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAmountPositive() throws IOException {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setAmount(-10.0);
		Set<ConstraintViolation<ExpenseDTO>> violations = validator.validate(expenseDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testCategoryNotBlank() throws IOException {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setCategory("");
		Set<ConstraintViolation<ExpenseDTO>> violations = validator.validate(expenseDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDateNotNull() throws IOException {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setDate(null);
		Set<ConstraintViolation<ExpenseDTO>> violations = validator.validate(expenseDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
