package com.example.lab4.repository;

import com.example.lab4.model.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends MongoRepository<Shoe, String> {
}
