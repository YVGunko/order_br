package com.yg.stpls.model.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {// <1>
	@RestResource(rel = "name-contains", path="name-contains", exported = false)
	Page<Customer> findByNameContaining(String name, Pageable pageable);
}
