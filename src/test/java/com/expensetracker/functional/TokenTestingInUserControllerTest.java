package com.expensetracker.functional;

import static com.expensetracker.utils.TestUtils.businessTestFile;
import static com.expensetracker.utils.TestUtils.currentTest;
import static com.expensetracker.utils.TestUtils.testReport;
import static com.expensetracker.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.expensetracker.controller.UserController;
import com.expensetracker.entity.AuthRequest;
import com.expensetracker.service.impl.JwtService;
import com.expensetracker.service.impl.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenTestingInUserControllerTest {

	@Mock
	private UserInfoService userInfoService;

	@Mock
	private JwtService jwtService;

	@Mock
	private AuthenticationManager authenticationManager;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	void authenticateAndGetToken_ValidCredentials_ReturnsToken() throws Exception {
		try {
			AuthRequest authRequest = new AuthRequest("abc", "abc");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					authRequest.getUsername(), authRequest.getPassword());
			Authentication authentication = Mockito.mock(Authentication.class);
			when(authenticationManager.authenticate(eq(token))).thenReturn(authentication);
			when(authentication.isAuthenticated()).thenReturn(true);
			when(jwtService.generateToken(authRequest.getUsername())).thenReturn("mockedToken");
			String result = mockMvc
					.perform(post("/api/auth/generateToken").contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(authRequest)))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			yakshaAssert(currentTest(), result.contentEquals("mockedToken"), businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
