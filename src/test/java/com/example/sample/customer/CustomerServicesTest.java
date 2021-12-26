package com.example.sample.customer;

import com.example.sample.exception.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServicesTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerServices underTest;
    @Captor
    private ArgumentCaptor<Customer> captor;

    @BeforeEach
    void setUp() {
        underTest = new CustomerServices(customerRepository);
    }

    @Test
    @DisplayName("Select All Customers in Database")
    void itShouldSelectAllCustomers() {

        //given
        //when
        underTest.getCustomers();
        //then
        verify(customerRepository).findAll();
    }

    @Test
    void itShouldSelectCustomerWithId() {
        //Given
        final long customerId = 2;
        given(customerRepository.existsById(customerId)).willReturn(true);
        //When
        underTest.getCustomer(customerId);
        //Then
        verify(customerRepository).findById(customerId);
    }


    @Test
    void willThrowWhenGetCustomerIdNotFound() {


        long customerId = 2;

        given(customerRepository.existsById(customerId)).willReturn(false);

        //when
        //then

        assertThatThrownBy(() -> underTest.getCustomer(customerId))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Customer with id " + customerId + " does not exists");



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
        underTest.saveCustomer(customer);
        //Then
        captor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerRepository).save(captor.capture());
        Customer customerCaptor = captor.getValue();
        assertThat(customerCaptor).isEqualTo(customer);
    }

    @Test
    void itShouldDeleteCustomer() {
        //Given
        Customer customer = new Customer(
                2L,
                "Stephine Okafor",
                "okafor.step@gmail.com",
                Gender.FEMALE
        );
        given(customerRepository.existsById(customer.getId())).willReturn(true);
        //When
        underTest.deleteCustomer(customer.getId());
        //Then
        Mockito.verify(customerRepository).deleteById(customer.getId());
    }

    @Test
    void willThrowWhenDeleteCustomerIdNotFound() {


        long customerId = 2;

        given(customerRepository.existsById(customerId)).willReturn(false);

        //when
        //then
        assertThatThrownBy(() -> underTest.deleteCustomer(customerId))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Customer with id " + customerId + " does not exists");

    }

    @Test
    void itShouldUpdateCustomer() {
        Customer customer = new Customer(
                2L,
                "Stephine Okafor",
                "okafor.step@gmail.com",
                Gender.FEMALE
        );
        given(customerRepository.existsById(customer.getId())).willReturn(true);
        //When
        underTest.updateCustomer(customer.getId(), customer);
        //Then
        captor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerRepository).save(captor.capture());
        Customer customerCaptor = captor.getValue();
        assertThat(customerCaptor).isEqualTo(customer);
    }

    @Test
    void willThrowWhenUpdateCustomerIdNotFound() {


        Customer customer = new Customer(
                2L,
                "Stephine Okafor",
                "okafor.step@gmail.com",
                Gender.FEMALE
        );

        given(customerRepository.existsById(customer.getId())).willReturn(false);

        //when
        //then
        assertThatThrownBy(() -> underTest.updateCustomer(customer.getId(), customer))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Customer with id " + customer.getId() + " does not exists");

    }

}