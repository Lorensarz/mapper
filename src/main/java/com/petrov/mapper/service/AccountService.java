package com.petrov.mapper.service;

import com.petrov.mapper.dto.CustomerDto;
import com.petrov.mapper.dto.OrderCreationRequestDto;

import java.util.List;

public interface AccountService {


    void save(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto findById(Long id);

    void deleteCustomer(Long id);

    void updateCustomer(Long id, CustomerDto customerDto);

    void createOrder(OrderCreationRequestDto orderCreationRequestDto);

}
