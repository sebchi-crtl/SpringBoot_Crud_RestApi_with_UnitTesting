package com.example.sample.customer;

import com.example.sample.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServices {

    private final CustomerRepository customerRepository;


    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(Long id) {

        if(!customerRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Customer with id " + id + " does not exists");
        }
        return customerRepository.findById(id);
    }

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }


    public void deleteCustomer(Long id){
        if(!customerRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Customer with id " + id + " does not exists");
        }
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, Customer customer) {
        if(!customerRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Customer with id " + id + " does not exists");
        }
        customerRepository.save(customer);

    }
}
