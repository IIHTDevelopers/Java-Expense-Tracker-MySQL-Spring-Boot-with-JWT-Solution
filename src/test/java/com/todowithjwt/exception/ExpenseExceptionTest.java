package com.todowithjwt.exception;

import static com.todowithjwt.utils.TestUtils.businessTestFile;
import static com.todowithjwt.utils.TestUtils.currentTest;
import static com.todowithjwt.utils.TestUtils.testReport;
import static com.todowithjwt.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.todowithjwt.entity.Expense;
import com.todowithjwt.repository.ExpenseRepository;
import com.todowithjwt.repository.UserInfoRepository;
import com.todowithjwt.service.impl.ExpenseServiceImpl;

@SpringBootTest
public class ExpenseExceptionTest {

	@Autowired
	private ExpenseServiceImpl expenseService;

	@MockBean
	private ExpenseRepository expenseRepository;

	@MockBean
	private UserInfoRepository userRepository;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateExpenseUserNotFound() throws Exception {
		Expense expense = new Expense();
		expense.setName("Test Expense");
		expense.setAmount(100.0);
		expense.setCategory("Test Category");
		expense.setDate(new Date());
		expense.setNote("Test Note");
		when(userRepository.findById(1101)).thenReturn(Optional.empty());
		try {
			expenseService.createExpense(expense, 1);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (ResourceNotFoundException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}

	@Test
	public void testGetExpenseByIdExpenseNotFound() throws Exception {
		Long expenseId = 1101L;
		when(expenseRepository.findById(expenseId)).thenReturn(Optional.empty());
		try {
			expenseService.getExpenseById(expenseId);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (ResourceNotFoundException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}

	@Test
	public void testUpdateExpenseExpenseNotFound() throws Exception {
		Long expenseId = 1101L;
		Expense expenseDetails = new Expense();
		when(expenseRepository.findById(expenseId)).thenReturn(Optional.empty());
		try {
			expenseService.updateExpense(expenseId, expenseDetails);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (ResourceNotFoundException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}

	@Test
	public void testDeleteExpenseExpenseNotFound() throws Exception {
		Long expenseId = 1101L;
		when(expenseRepository.findById(expenseId)).thenReturn(Optional.empty());
		try {
			expenseService.deleteExpense(expenseId);
			yakshaAssert(currentTest(), false, businessTestFile);
		} catch (ResourceNotFoundException e) {
			yakshaAssert(currentTest(), true, businessTestFile);
		}
	}
}