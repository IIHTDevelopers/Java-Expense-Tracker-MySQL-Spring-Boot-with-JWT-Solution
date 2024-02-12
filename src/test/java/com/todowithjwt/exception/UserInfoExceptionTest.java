package com.todowithjwt.exception;

import static com.todowithjwt.utils.TestUtils.businessTestFile;
import static com.todowithjwt.utils.TestUtils.currentTest;
import static com.todowithjwt.utils.TestUtils.testReport;
import static com.todowithjwt.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.todowithjwt.repository.UserInfoRepository;
import com.todowithjwt.service.impl.UserInfoService;

@SpringBootTest
public class UserInfoExceptionTest {

	@Autowired
	private UserInfoService userInfoService;

	@MockBean
	private UserInfoRepository repository;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testLoadUserByUsernameUserNotFound() throws Exception {
		String username = "testuser";
		when(repository.findByName(username)).thenReturn(Optional.empty());
		try {
			userInfoService.loadUserByUsername(username);
			yakshaAssert(currentTest(), true, businessTestFile);
		} catch (UsernameNotFoundException e) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}
