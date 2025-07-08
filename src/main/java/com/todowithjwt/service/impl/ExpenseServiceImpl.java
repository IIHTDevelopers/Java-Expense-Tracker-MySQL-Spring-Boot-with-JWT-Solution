package com.todowithjwt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todowithjwt.entity.Expense;
import com.todowithjwt.entity.User;
import com.todowithjwt.exception.ResourceNotFoundException;
import com.todowithjwt.repository.ExpenseRepository;
import com.todowithjwt.repository.UserInfoRepository;
import com.todowithjwt.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private UserInfoRepository userRepository;

	@Override
	public Expense createExpense(Expense expense, int userId) throws ResourceNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			expense.setUser(user);
			return expenseRepository.save(expense);
		} else {
			throw new ResourceNotFoundException("User not found.");
		}
	}

	@Override
	public Expense getExpenseById(Long expenseId) throws ResourceNotFoundException {
		return expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found."));
	}

	@Override
	public Expense updateExpense(Long expenseId, Expense expenseDetails) throws ResourceNotFoundException {
		Expense expense = expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found."));
		expense.setName(expenseDetails.getName());
		expense.setAmount(expenseDetails.getAmount());
		expense.setCategory(expenseDetails.getCategory());
		expense.setDate(expenseDetails.getDate());
		expense.setNote(expenseDetails.getNote());
		return expenseRepository.save(expense);
	}

	@Override
	public void deleteExpense(Long expenseId) throws ResourceNotFoundException {
		Expense expense = expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found."));
		expenseRepository.delete(expense);
	}

	@Override
	public List<Expense> getAllExpensesForUser(int userId) {
		return expenseRepository.findByUserId(userId);
	}
}
