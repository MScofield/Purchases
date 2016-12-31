package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by scofieldservices on 12/26/16.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
