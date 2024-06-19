package com.example.monolithtomicroservices.infrastructure.http.customer;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @PostMapping("/{id}/cart")
    public CustomerCartWriteModel getCartByCustomerId(@PathVariable String id, @Valid @RequestBody CustomerCartWriteModel request) {
        return null;
    }
}
