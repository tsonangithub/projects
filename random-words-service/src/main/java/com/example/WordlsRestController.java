package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class WordlsRestController {
	
	@Value("${test}")
	private String test;
	
//	@RequestMapping("/word") 
//	private String getWord() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("word length is " + wordl);
//		Random r = new Random();
//		for (int i = 0; i <= words; i++) {
//			char c = (char)(r.nextInt(26) + 'a');		
//			sb.append(c);
//		}
//		return sb.toString();
//	}
	
	@RequestMapping("/test") 
	private String getTest() { 
		return test;
	}
	
} 