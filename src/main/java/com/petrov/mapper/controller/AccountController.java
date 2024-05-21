package com.petrov.mapper.controller;

import com.petrov.mapper.dto.CustomerDto;
import com.petrov.mapper.dto.OrderCreationRequestDto;
import com.petrov.mapper.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void crateCustomer(@RequestBody @Validated CustomerDto customerDto) {
        accountService.save(customerDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerDto> getAllCustomers() {
       return accountService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable Long id) {
        accountService.deleteCustomer(id);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable Long id,
            @RequestBody @Validated CustomerDto customerDto) {
        accountService.updateCustomer(id, customerDto);
    }

    @PostMapping("/create_order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderCreationRequestDto orderCreationRequestDto) {
        accountService.createOrder(orderCreationRequestDto);
    }
}
