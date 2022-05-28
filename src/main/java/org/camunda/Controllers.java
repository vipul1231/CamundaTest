package org.camunda;


import org.camunda.dto.Input;
import org.camunda.dto.Output;
import org.camunda.feel.syntaxtree.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class Controllers {

	private final Random random = new Random();

	@GetMapping("/random")
	public ResponseEntity<Boolean> getRandom() {
		return new ResponseEntity<>(random.nextBoolean(), HttpStatus.OK);
	}

	@GetMapping("/connector/{name}")
	public ResponseEntity<Integer> getLength(@PathVariable String name) {
		return new ResponseEntity<>(name.length(), HttpStatus.OK);
	}

	@PostMapping("/connector")
	public ResponseEntity<Integer> getFinalLength(@RequestBody Input input) {
		return new ResponseEntity<>(input.getNameLength()+input.getNum1(), HttpStatus.OK);
	}

	@PostMapping("/connector/add")
	public ResponseEntity<Integer> add(@RequestBody Input input) {
		return new ResponseEntity<>(input.getA()+input.getB(), HttpStatus.OK);
	}

	@GetMapping("/connector/square/{square}")
	public ResponseEntity<Output> square(@PathVariable Integer square) {
		return new ResponseEntity<>(new Output(square*2, "Oops!, Bad request been sent."), HttpStatus.OK);
	}
}
