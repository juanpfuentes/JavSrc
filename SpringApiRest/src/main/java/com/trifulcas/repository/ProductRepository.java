package com.trifulcas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trifulcas.model.Product;



@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {
 
}