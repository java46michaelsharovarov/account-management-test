package telran.test.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TestController {
	
	@GetMapping("test")
	String getTest() {
		return "Hello";
	}
	
}
