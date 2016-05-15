package com.fprochazka.drill.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Michaela Bamburová on 21.05.2016.
 */
public class PersonRepoImpl {

	@Autowired
	private PersonRepo repository;

	public PersonRepoImpl(PersonRepo repository) {
		this.repository = repository;
	}

	public Iterable<Person> getAllPersons() {
		Iterable<Person> persons = repository.searchByAge();
		return persons;
	}

	public Person personByName(String name) {
		Person person = repository.searchByName(name);
		return person;
	}


	public void deletePerson(Person person) {
		repository.delete(person);
	}

	public List<Person> getList() {
		return repository.findAll();
	}

}

