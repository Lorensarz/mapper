package com.petrov.mapper.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    @NotEmpty(message = "Имя не может быть пустым")
    private String firstName;

    @NotEmpty(message = "Фамилия не может быть пустым")
    private String lastName;

    @NotEmpty(message = "Имя не может быть пустым")
    private String email;

    @NotEmpty(message = "Контактный номер не может быть пустым")
    private String contactNumber;

    @Nullable
    private List<OrderDto> orders;
}
