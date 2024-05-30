package com.jrnoh.springdoc;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	protected PersonRepository repository;
	
	public PersonService(PersonRepository repository) {
		super();
		this.repository = repository;
	}

   
    public void addPersona(Person person) {
        
    	repository.save(person);
    }

    public List<Person> getAllPersonas() {
    	return repository.findAll();
    }

    public Optional<Person> getPersona(Integer id) {
        return repository.findById(id);
    }

}
