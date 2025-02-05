package dev.panthu.crm.dao;

import dev.panthu.crm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
