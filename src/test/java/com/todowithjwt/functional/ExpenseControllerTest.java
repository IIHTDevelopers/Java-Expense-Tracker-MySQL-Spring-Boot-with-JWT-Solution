package com.todowithjwt.functional;

import static com.todowithjwt.utils.TestUtils.businessTestFile;
import static com.todowithjwt.utils.TestUtils.currentTest;
import static com.todowithjwt.utils.TestUtils.testReport;
import static com.todowithjwt.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.todowithjwt.controller.ExpenseController;
import com.todowithjwt.entity.Expense;
import com.todowithjwt.exception.ResourceNotFoundException;
import com.todowithjwt.service.ExpenseService;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

	@Mock
	private ExpenseService expenseService;

	@InjectMocks
	private ExpenseController expenseController;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetExpenseById_WhenExpenseExists() throws Exception {
		Long expenseId = 1L;
		Expense expense = new Expense();
		expense.setId(expenseId);
		when(expenseService.getExpenseById(expenseId)).thenReturn(expense);
		ResponseEntity<Expense> response = expenseController.getExpenseById(expenseId);
		yakshaAssert(currentTest(),
				response.getStatusCode() == HttpStatus.OK && response.getStatusCode() == HttpStatus.OK,
				businessTestFile);
	}

	@Test
	public void testGetExpenseById_WhenExpenseDoesNotExist() throws Exception {
		Long expenseId = 1L;
		when(expenseService.getExpenseById(expenseId)).thenThrow(new ResourceNotFoundException(""));
		ResponseEntity<Expense> response = expenseController.getExpenseById(expenseId);
		yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.NOT_FOUND && response.getBody() == null,
				businessTestFile);
	}

	@Test
	public void testCreateExpense() throws Exception {
		Expense expense = new Expense();
		int userId = 1;
		when(expenseService.createExpense(expense, userId)).thenReturn(expense);
		ResponseEntity<Expense> response = expenseController.createExpense(expense, userId);
		yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.CREATED && response.getBody() == expense,
				businessTestFile);
	}

	@Test
	public void testUpdateExpense() throws Exception {
		Long expenseId = 1L;
		Expense expenseDetails = new Expense();
		when(expenseService.updateExpense(expenseId, expenseDetails)).thenReturn(expenseDetails);
		ResponseEntity<Expense> response = expenseController.updateExpense(expenseId, expenseDetails);
		yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.OK && response.getBody() == expenseDetails,
				businessTestFile);
	}

	@Test
	public void testDeleteExpense() throws Exception {
		Long expenseId = 1L;
		ResponseEntity<Void> response = expenseController.deleteExpense(expenseId);
		yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.NO_CONTENT, businessTestFile);
	}

	@Test
	public void testGetAllExpensesForUser() throws Exception {
		int userId = 1;
		List<Expense> expenses = new ArrayList<>();
		when(expenseService.getAllExpensesForUser(userId)).thenReturn(expenses);
		ResponseEntity<List<Expense>> response = expenseController.getAllExpensesForUser(userId);
		yakshaAssert(currentTest(), response.getBody() == expenses && response.getStatusCode() == HttpStatus.OK,
				businessTestFile);
	}
}