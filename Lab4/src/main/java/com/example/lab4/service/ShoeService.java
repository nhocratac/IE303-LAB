package com.example.lab4.service;

import com.example.lab4.model.Shoe;
import com.example.lab4.repository.ShoeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoeService {

    @Autowired
    private  ShoeRepository shoeRepository;

    public List<Shoe> findAll() {

        System.out.println(shoeRepository.findAll());
        return shoeRepository.findAll();
    }
}
