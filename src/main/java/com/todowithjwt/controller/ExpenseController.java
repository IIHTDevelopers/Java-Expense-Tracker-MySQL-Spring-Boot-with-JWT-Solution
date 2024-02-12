package com.todowithjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todowithjwt.entity.Expense;
import com.todowithjwt.exception.ResourceNotFoundException;
import com.todowithjwt.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/{expenseId}")
	public ResponseEntity<Expense> getExpenseById(@PathVariable Long expenseId) {
		try {
			Expense expense = expenseService.getExpenseById(expenseId);
			return new ResponseEntity<>(expense, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<Expense> createExpense(@RequestBody Expense expense, @PathVariable int userId) {
		try {
			Expense createdExpense = expenseService.createExpense(expense, userId);
			return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{expenseId}")
	public ResponseEntity<Expense> updateExpense(@PathVariable Long expenseId, @RequestBody Expense expenseDetails) {
		try {
			Expense updatedExpense = expenseService.updateExpense(expenseId, expenseDetails);
			return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{expenseId}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
		try {
			expenseService.deleteExpense(expenseId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Expense>> getAllExpensesForUser(@PathVariable int userId) {
		List<Expense> expenses = expenseService.getAllExpensesForUser(userId);
		return new ResponseEntity<>(expenses, HttpStatus.OK);
	}
}