package com.petrov.mapper.controller;

import com.petrov.mapper.dto.CustomerDto;
import com.petrov.mapper.dto.OrderCreationRequestDto;
import com.petrov.mapper.service.AccountService;
import com.petrov.mapper.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JsonUtil jsonUtil;

    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void crateCustomer(@RequestBody String customerJson) {
        CustomerDto customerDto = jsonUtil.toCustomerDto(customerJson);
        accountService.save(customerDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String getAllCustomers() {
        return jsonUtil.toJson(accountService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id) {
        return jsonUtil.toJson(accountService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable Long id) {
        accountService.deleteCustomer(id);
    }

    @PatchMapping("/{id}")
    public String updateCustomer(@PathVariable Long id,
            @RequestBody String customerJson) {
        CustomerDto customerDto = jsonUtil.toCustomerDto(customerJson);
        accountService.updateCustomer(id, customerDto);
        return customerJson;
    }

    @PostMapping("/create_order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOrder(@RequestBody String orderCreationRequestJson) {
        OrderCreationRequestDto orderCreationRequestDto =
                jsonUtil.toOrderCreationRequestDto(orderCreationRequestJson);
        accountService.createOrder(orderCreationRequestDto);
    }
}
