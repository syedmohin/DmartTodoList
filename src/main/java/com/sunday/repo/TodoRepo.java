package com.sunday.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sunday.model.Todo;

public interface TodoRepo extends CrudRepository<Todo, Integer> {
	void deleteByDate(LocalDate date);

	Optional<Todo> findByItemName(String itemName);

	void deleteByItemName(String itemName);
}
