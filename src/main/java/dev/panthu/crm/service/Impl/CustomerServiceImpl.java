package dev.panthu.crm.service.Impl;

import dev.panthu.crm.dao.CustomerDAO;
import dev.panthu.crm.entity.Customer;
import dev.panthu.crm.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerServiceImpl (CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerDAO.save(customer);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        customerDAO.deleteById(id);
    }
}
