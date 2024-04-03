package com.example.TechnoMark.controller;

import com.example.TechnoMark.model.Customer;
import com.example.TechnoMark.model.OrderDetail;
import com.example.TechnoMark.model.Product;
import com.example.TechnoMark.repository.CustomerRepository;
import com.example.TechnoMark.repository.OrderDetailRepository;
import com.example.TechnoMark.repository.ProductRepository;
import com.example.TechnoMark.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final AuthenticationService authenticationService;
    private final CustomerRepository customerRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, OrderDetailRepository orderDetailRepository, AuthenticationService authenticationService, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.authenticationService = authenticationService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/orderById/{id}")
    public ResponseEntity<OrderDetail> orderById(@PathVariable int id) {
        String username = authenticationService.getCurrentUsername();

        Product product = productRepository.findById(id).orElseThrow();
        OrderDetail orderDetail = new OrderDetail();
        Customer customer = customerRepository.findCustomerByUsername(username).orElseThrow();

        orderDetail.setProducts(List.of(product));
        orderDetail.setOrderDate(new Date());
        orderDetail.setCustomerId(customer.getId());

        OrderDetail saved = orderDetailRepository.save(orderDetail);
        return ResponseEntity.ok(saved);
    }
}
