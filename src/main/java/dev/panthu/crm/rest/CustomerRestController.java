package dev.panthu.crm.rest;

import dev.panthu.crm.entity.Customer;
import dev.panthu.crm.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController (CustomerService customerService, Customer customer) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer is not found");
        }
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(0);
        return customerService.save(customer);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public String removeCustomer(@PathVariable int customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer is not found");
        }
        customerService.deleteById(customerId);
        return "Deleted Customer!";
    }
}
