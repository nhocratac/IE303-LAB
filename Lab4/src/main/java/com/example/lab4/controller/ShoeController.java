package com.example.lab4.controller;

import com.example.lab4.model.Shoe;
import com.example.lab4.service.ShoeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/shoes")
@RequiredArgsConstructor
public class ShoeController {

    @Autowired
    private  ShoeService shoeService;

    @GetMapping("/")
    public ResponseEntity<List<Shoe>> index() {
        List<Shoe> shoes = shoeService.findAll();
        return ResponseEntity.ok(shoes);
    }
}
