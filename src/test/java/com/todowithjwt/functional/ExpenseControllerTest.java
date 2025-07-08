package com.todowithjwt.functional;

import static com.todowithjwt.utils.TestUtils.businessTestFile;
import static com.todowithjwt.utils.TestUtils.currentTest;
import static com.todowithjwt.utils.TestUtils.testReport;
import static com.todowithjwt.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.lenient;

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
		try {
			Long expenseId = 1L;
			Expense expense = new Expense();
			expense.setId(expenseId);
			lenient().when(expenseService.getExpenseById(expenseId)).thenReturn(expense);
			ResponseEntity<Expense> response = expenseController.getExpenseById(expenseId);
			if (response != null) {
				yakshaAssert(currentTest(),
						response.getStatusCode() == HttpStatus.OK && response.getStatusCode() == HttpStatus.OK,
						businessTestFile);
			} else {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetExpenseById_WhenExpenseDoesNotExist() throws Exception {
		try {
			Long expenseId = 1L;
			lenient().when(expenseService.getExpenseById(expenseId)).thenThrow(new ResourceNotFoundException(""));
			ResponseEntity<Expense> response = expenseController.getExpenseById(expenseId);
			if (response != null) {
				yakshaAssert(currentTest(),
						response.getStatusCode() == HttpStatus.NOT_FOUND && response.getBody() == null,
						businessTestFile);
			} else {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testCreateExpense() throws Exception {
		try {
			Expense expense = new Expense();
			int userId = 1;
			lenient().when(expenseService.createExpense(expense, userId)).thenReturn(expense);
			ResponseEntity<Expense> response = expenseController.createExpense(expense, userId);
			if (response != null) {
				yakshaAssert(currentTest(),
						response.getStatusCode() == HttpStatus.CREATED && response.getBody() == expense,
						businessTestFile);
			} else {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testUpdateExpense() throws Exception {
		try {
			Long expenseId = 1L;
			Expense expenseDetails = new Expense();
			lenient().when(expenseService.updateExpense(expenseId, expenseDetails)).thenReturn(expenseDetails);
			ResponseEntity<Expense> response = expenseController.updateExpense(expenseId, expenseDetails);
			if (response != null) {
				yakshaAssert(currentTest(),
						response.getStatusCode() == HttpStatus.OK && response.getBody() == expenseDetails,
						businessTestFile);
			} else {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeleteExpense() throws Exception {
		try {
			Long expenseId = 1L;
			ResponseEntity<Void> response = expenseController.deleteExpense(expenseId);
			if (response != null) {
				yakshaAssert(currentTest(), response.getStatusCode() == HttpStatus.NO_CONTENT, businessTestFile);
			} else {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetAllExpensesForUser() throws Exception {
		try {
			int userId = 1;
			List<Expense> expenses = new ArrayList<>();
			lenient().when(expenseService.getAllExpensesForUser(userId)).thenReturn(expenses);
			ResponseEntity<List<Expense>> response = expenseController.getAllExpensesForUser(userId);
			if (response != null) {
				yakshaAssert(currentTest(), response.getBody() == expenses && response.getStatusCode() == HttpStatus.OK,
						businessTestFile);
			} else {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}