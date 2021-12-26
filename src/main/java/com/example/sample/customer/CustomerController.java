package com.example.sample.customer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerServices customerServices;


    @GetMapping
    public List<Customer> getAllCustomer(){
        List<Customer> customer = customerServices.getCustomers();
        return customer;
    }

    @GetMapping(path = "/{id}")
    public Optional<Customer> getCustomerId(@PathVariable("id") Long id){
        return customerServices.getCustomer(id);
    }

    @PostMapping
    public String addCustomer(@RequestBody Customer customer){
        String message = "Customer Saved Successfully "+ customer.getName();
        customerServices.saveCustomer(customer);
        return message;
    }

    @PutMapping(path = "/{id}")
    public void putCustomer(@RequestBody Customer customer, @PathVariable("id") Long id){
        customerServices.updateCustomer(id, customer);
    }

    @DeleteMapping(path = "/{id}")
    public void removeCustomer(@PathVariable("id") Long id){
        customerServices.deleteCustomer(id);
    }

}
