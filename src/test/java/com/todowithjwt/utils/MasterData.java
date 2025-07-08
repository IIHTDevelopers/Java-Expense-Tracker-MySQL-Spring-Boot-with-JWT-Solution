package com.todowithjwt.utils;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.todowithjwt.dto.ExpenseDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class MasterData {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	private static Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public static String getToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public static ExpenseDTO getExpenseDTO() {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setId(1L);
		expenseDTO.setName("Groceries");
		expenseDTO.setAmount(50.75);
		expenseDTO.setCategory("Food");
		expenseDTO.setDate(new Date());
		expenseDTO.setNote("Bought groceries for the week");
		expenseDTO.setUserId(1);
		return expenseDTO;
	}

	public static List<ExpenseDTO> getExpenseDTOList() {
		List<ExpenseDTO> expenseDTOList = new ArrayList<>();

		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setId(1L);
		expenseDTO.setName("Groceries");
		expenseDTO.setAmount(50.75);
		expenseDTO.setCategory("Food");
		expenseDTO.setDate(new Date());
		expenseDTO.setNote("Bought groceries for the week");
		expenseDTO.setUserId(1);

		expenseDTOList.add(expenseDTO);

		return expenseDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		String s = "";
		for (int i = 0; i < size; i++) {
			s.concat("A");
		}
		return s;
	}
}