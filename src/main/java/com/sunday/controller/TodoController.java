package com.sunday.controller;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunday.model.Todo;
import com.sunday.service.TodoService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService service;

	@GetMapping("/all")
	public ResponseEntity<List<Todo>> getAllTodo() {
		return ResponseEntity
		        .ok(service.allTodo());
	}

	@GetMapping("/all/{date}")
	public ResponseEntity<List<Todo>> getAllTodoByDate(@PathVariable("date") LocalDate date) {
		return ResponseEntity
		        .ok(service.allTodoByDate(date));
	}

	@PostMapping("/save")
	public ResponseEntity<Todo> save(@RequestBody Todo todo) {
		return ResponseEntity.ok(service.insert(todo));
	}

	@DeleteMapping("/delete/{date}")
	public void deleteByDate(@PathVariable("date") String date) {
		service.deleteAll(LocalDate.parse(date, ofPattern("dd-MM-yyyy")));
	}

	@DeleteMapping("/deleteitem/{itemName}")
	public void deleteByItemName(@PathVariable("itemName") String itemName) {
		System.out.println(itemName);
		service.deleteByItemName(itemName);
	}

	@PutMapping("/update/{itemName}")
	public void updateByDate(@PathVariable("itemName") String itemName, @RequestBody Todo todo) {
		service.update(itemName, todo);
	}
}
