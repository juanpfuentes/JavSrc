package com.trifulcas.TestApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trifulcas.TestApi.model.Product;


@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {
 
}