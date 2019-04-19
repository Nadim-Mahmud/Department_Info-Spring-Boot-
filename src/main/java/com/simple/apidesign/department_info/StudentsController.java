package com.simple.apidesign.department_info;

import java.util.List;

import com.simple.apidesign.department_info.model.Students;

import repositories.StudentsRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;



@RestController
@RequestMapping("/Students")

public class StudentsController {
	
  @Autowired
  private StudentsRepository repository;
  
  //Get
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<Students> getAllPets() {
    return repository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Students getPetById(@PathVariable("id") ObjectId id) {
    return repository.findBy_id(id);
  }
  
  //Put
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Students students) {
	  students.set_id(id);
    repository.save(students);
  }
  
  //post
  
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Students createPet(@Valid @RequestBody Students students) {
	  students.set_id(ObjectId.get());
    repository.save(students);
    return students;
  }
  
  //delete
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deletePet(@PathVariable ObjectId id) {
    repository.delete(repository.findBy_id(id));
  }
  
}