package com.sunday.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunday.exception.ItemNotFoundException;
import com.sunday.model.Todo;
import com.sunday.repo.TodoRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepo repo;

	@Transactional(readOnly = true)
	public List<Todo> allTodo() {
		var data = new ArrayList<Todo>();
		repo.findAll().forEach(data::add);
		return data;
	}

	@Transactional(readOnly = true)
	public List<Todo> allTodoByDate(LocalDate date) {
		var data = new ArrayList<Todo>();
		repo.findByDate(date).forEach(data::add);
		return data;
	}

	@Transactional
	public Todo insert(Todo todo) {
		return repo.save(todo);
	}

	@Transactional
	public void deleteAll(LocalDate date) {
		repo.deleteByDate(date);
	}

	@Transactional
	public void deleteByItemName(String itemName) {
		repo.deleteByItemName(itemName);
	}

	@Transactional
	public void update(String itemName, Todo todo) {
		Todo t = repo.findByItemName(itemName)
		        .orElseThrow(() -> new ItemNotFoundException("Item not Found"));
		t.setItemName(todo.getItemName());
		t.setQuatity(todo.getQuatity());
		t.setDone(todo.isDone());
		t.setDate(todo.getDate());
		repo.save(t);
	}
}
