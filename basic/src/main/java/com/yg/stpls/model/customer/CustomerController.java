package com.yg.stpls.model.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//we allow cors requests from our frontend environment
//note the curly braces that creates an array of strings ... required by the annotation
@CrossOrigin(origins =  {"${localhost:4232}"})
@RequestMapping("/api")
public class CustomerController {
	
  @Autowired
  CustomerRepository customerRepository;
	  
@GetMapping("/customers")
public ResponseEntity<Map<String, Object>> getAllTutorials(
	        @RequestParam(required = false) String title,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "12") int size
	      ) {

	    try {
	      List<Customer> tutorials = new ArrayList<Customer>();
	      Pageable paging = PageRequest.of(page, size);
	      
	      Page<Customer> pageTuts;
	      if (title == null)
	        pageTuts = customerRepository.findAll(paging);
	      else
	        pageTuts = customerRepository.findByNameContaining(title, paging);
	
	      tutorials = pageTuts.getContent();
	
	      Map<String, Object> response = new HashMap<>();
	      response.put("tutorials", tutorials);
	      response.put("currentPage", pageTuts.getNumber());
	      response.put("totalItems", pageTuts.getTotalElements());
	      response.put("totalPages", pageTuts.getTotalPages());
	
	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
/*
@GetMapping("/customers/{id}")
public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {

	}*/
}
