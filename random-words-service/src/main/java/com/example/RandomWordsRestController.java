package com.example;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class RandomWordsRestController {
	
	@Value("${word_length}")
	private int wordLength;

	@RequestMapping("/word") 
	String getWord() {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < wordLength; i++) {
			char c = (char)(r.nextInt(26) + 'a');		
			sb.append(c);
		}
		return sb.toString();
	}
} 