package com.AlaCartApp.models.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;


public record UserDto(Long id, String name, String email, String password, String lastName, Boolean state, Date startDate) {


    
}
