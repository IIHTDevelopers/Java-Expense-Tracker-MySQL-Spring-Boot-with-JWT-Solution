package com.expensetracker.service;

import java.util.List;

import com.expensetracker.entity.Expense;
import com.expensetracker.exception.ResourceNotFoundException;

public interface ExpenseService {

	Expense createExpense(Expense expense, int userId) throws ResourceNotFoundException;

	Expense getExpenseById(Long expenseId) throws ResourceNotFoundException;

	Expense updateExpense(Long expenseId, Expense expenseDetails) throws ResourceNotFoundException;

	void deleteExpense(Long expenseId) throws ResourceNotFoundException;

	List<Expense> getAllExpensesForUser(int userId);
}
