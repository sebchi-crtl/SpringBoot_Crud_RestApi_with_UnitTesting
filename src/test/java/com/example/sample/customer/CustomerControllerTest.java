package com.example.sample.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerServices customerServices;
    private CustomerController underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerController(customerServices);
    }


    @Test
    void itShouldGetAllCustomer() {
        //Given
        //When
        underTest.getAllCustomer();
        //Then
        Mockito.verify(customerServices).getCustomers();
    }

    @Test
    void itShouldGetCustomerById() {
         long customerId = 2;
        //When
        underTest.getCustomerId(customerId);
        //Then
        verify(customerServices).getCustomer(customerId);
        assertThat(underTest).isNotNull();
    }

    @Test
    void itShouldAddCustomer() {
        //Given
        Customer customer = new Customer(
                "Stephine Okafor",
                "okafor.step@gmail.com",
                Gender.FEMALE
        );
        //When
        underTest.addCustomer(customer);
        //Then
        verify(customerServices).saveCustomer(customer);
    }

    @Test
    void itShouldPutCustomer() {
        Customer customer = new Customer(
                2L,
                "Stephine Okafor",
                "okafor.step@gmail.com",
                Gender.FEMALE
        );
        //When
        underTest.putCustomer(customer, customer.getId());
        //Then
        Mockito.verify(customerServices).updateCustomer(customer.getId(), customer);
    }

    @Test
    void itShouldRemoveCustomer() {
        Customer customer = new Customer(
                2L,
                "Stephine Okafor",
                "okafor.step@gmail.com",
                Gender.FEMALE
        );
        //When
        underTest.removeCustomer(customer.getId());
        //Then
        Mockito.verify(customerServices).deleteCustomer(customer.getId());
    }
}