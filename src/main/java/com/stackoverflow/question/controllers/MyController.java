package com.stackoverflow.question.controllers;

import com.stackoverflow.question.entities.Name;
import com.stackoverflow.question.repositories.NameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	private final NameRepository nameRepository;

	public MyController(NameRepository scoreRepository) {
		this.nameRepository = scoreRepository;
	}

	@PostMapping("/triggerError")
	public ResponseEntity<Void> trigger() {

		Name name = new Name("Chris");
		nameRepository.save(name);

		return ResponseEntity.ok().build();
	}

}
