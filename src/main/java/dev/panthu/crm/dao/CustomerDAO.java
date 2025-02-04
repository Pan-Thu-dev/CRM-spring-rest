package dev.panthu.crm.dao;

import dev.panthu.crm.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> findAll();

    Customer findById(int id);

    Customer save(Customer customer);

    void deleteById(int id);
}
