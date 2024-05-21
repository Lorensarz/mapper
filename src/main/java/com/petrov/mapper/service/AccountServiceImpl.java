package com.petrov.mapper.service;


import com.petrov.mapper.dto.CustomerDto;
import com.petrov.mapper.dto.OrderCreationRequestDto;
import com.petrov.mapper.dto.OrderDto;
import com.petrov.mapper.entity.CustomerEntity;
import com.petrov.mapper.entity.OrderEntity;
import com.petrov.mapper.entity.ProductEntity;
import com.petrov.mapper.entity.enums.Status;
import com.petrov.mapper.mapper.CustomerMapper;
import com.petrov.mapper.mapper.OrderMapper;
import com.petrov.mapper.mapper.ProductMapper;
import com.petrov.mapper.repository.CustomerRepository;
import com.petrov.mapper.repository.OrderRepository;
import com.petrov.mapper.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public void save(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerDto);
        customerRepository.save(customerEntity);

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> userEntityList = customerRepository.findAll();
        return customerMapper.toDtoList(userEntityList);
    }

    @Override
    public CustomerDto findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        if (customerEntity == null) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        return customerMapper.toDto(customerEntity);
    }


    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomer(Long id, CustomerDto customerDto) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            CustomerEntity existingCustomer = optionalCustomer.get();

            if (customerDto.getFirstName() != null) {
                existingCustomer.setFirstName(customerDto.getFirstName());
            }

            if (customerDto.getLastName() != null) {
                existingCustomer.setLastName(customerDto.getLastName());
            }

            if (customerDto.getEmail() != null) {
                existingCustomer.setEmail(customerDto.getEmail());
            }

            if (customerDto.getOrders() != null) {
                existingCustomer.setOrders(orderMapper.toEntityList(customerDto.getOrders()));
            }

            if (customerDto.getContactNumber() != null) {
                existingCustomer.setContactNumber(customerDto.getContactNumber());
            }

            customerRepository.save(existingCustomer);

        } else {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
    }

    @Override
    public void createOrder(OrderCreationRequestDto orderCreationRequestDto) {
        Long customerId = orderCreationRequestDto.getCustomerId();
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            CustomerEntity existingCustomer = customer.get();
            List<OrderEntity> orders = checkOrders(existingCustomer);
            List<ProductEntity> products = checkProducts(orderCreationRequestDto.getOrderDto());

            productRepository.saveAll(products);

            OrderEntity order = new OrderEntity();

            order.setCustomerEntity(existingCustomer);
            order.setProducts(products);
            order.setTotalPrice(calculateOrderAmount(products));
            order.setOrderDate(LocalDateTime.now());
            order.setShippingAddres(orderCreationRequestDto.getOrderDto().getShippingAddres());
            order.setOrderStatus(Status.NEW);
            orderRepository.save(order);

            orders.add(order);
            existingCustomer.setOrders(orders);

            customerRepository.save(existingCustomer);

        } else {
            throw new IllegalArgumentException("Customer with id " + customerId + " not found");
        }
    }

    private List<OrderEntity> checkOrders(CustomerEntity userEntity) {
        List<OrderEntity> orders = userEntity.getOrders();
        if (orders != null) {
            return orders;
        } else {
            return new ArrayList<>() {
            };
        }
    }

    private List<ProductEntity> checkProducts(OrderDto orderDto) {
        List<ProductEntity> products = productMapper.toEntityList(orderDto.getProducts());
        if (products != null) {
            return products;
        } else {
            throw new IllegalArgumentException("Add products in order!");
        }
    }

    private BigDecimal calculateOrderAmount(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
