package dev.panthu.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dev.panthu.crm.dao.CustomerRepository;
import dev.panthu.crm.entity.Customer;
import dev.panthu.crm.exception.CustomerNotFoundException;

@Controller
@RequestMapping("/customers")
public class MvcController {

    private CustomerRepository customerRepository;

    @Autowired
    public MvcController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer/list";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/customer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        model.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
        return "redirect:/customers/list";
    }
}
