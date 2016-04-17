package com.example;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class StringUtilRestController {

//	@Value("${locale}")
//	private String locale;
	
	@RequestMapping("/substring/{inputString}")
    String retrieveSubstring (@PathVariable String inputString, @RequestParam int start, @RequestParam int end) {
		return inputString.substring(start, end);
	}
	
	@RequestMapping("/concat/{inputString}")
	String retrieveSubstring (@PathVariable String inputString, @RequestParam String otherString) {
		return inputString + otherString;
	}

}