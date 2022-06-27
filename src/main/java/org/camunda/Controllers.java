package org.camunda;


import org.camunda.dto.Input;
import org.camunda.dto.Output;
import org.camunda.feel.syntaxtree.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

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

	@GetMapping("/version/{artifact}")
	public ResponseEntity<Object> version(@PathVariable String artifact) {
		try {
			//mvn versions:display-dependency-updates
			Manifest manifest = new Manifest(Class.forName(artifact).getResourceAsStream("/META-INF/manifest.mf"));
			Object obj = manifest.getMainAttributes().get(Attributes.Name.IMPLEMENTATION_VERSION);
			System.out.println(obj);
			return new ResponseEntity<>(obj, HttpStatus.OK);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
