package telran.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.accountmanagement.model.Account;
import telran.accountmanagement.service.AccountsService;

interface TestConstants {
	String FILE_NAME = "test-accounts.data";
}

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = {
		"app.admin.username=admin",
		"app.admin.password = 123456",
		"app.file.data.name=" + TestConstants.FILE_NAME })
class AccountManagementServiceTest {
	
	static Logger LOG = LoggerFactory.getLogger(AccountManagementServiceTest.class);
	@Autowired
	AccountsService accountingService;

	@BeforeAll
	static void setUpBeforeAll() throws IOException {
		if (Files.deleteIfExists(Path.of(TestConstants.FILE_NAME))) {
			LOG.info("file {} has been deleted", TestConstants.FILE_NAME);
		} else {
			LOG.info("file {} not found", TestConstants.FILE_NAME);
		}
	}

	@Test
	@Order(1)
	void addAccount() {
		Account account = new Account();
		account.username = "test@gmail.com";
		account.password = "12345.com";
		account.role = "TEST";
		assertTrue(accountingService.addAccount(account));
	}

	@Test
	@Order(2)
	void updateAccount() {
		Account account = new Account();
		account.username = "test@gmail.com";
		account.password = "12345678";
		account.role = "TEST";
		assertTrue(accountingService.updateAccount(account));
	}

}
