package com.trifulcas.TestApi.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trifulcas.TestApi.model.Product;
import com.trifulcas.TestApi.repository.ProductRepository;


@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		final List<Product> productList = new ArrayList<Product>();
		Iterable<Product> iterable = productRepository.findAll();
		iterable.forEach(productList::add);
		return productList;
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) {
		Optional<Product> product = productRepository.findById(id);

		return product.isPresent() ? new ResponseEntity<Product>(product.get(), HttpStatus.OK)
				: new ResponseEntity("No data found", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id, @RequestBody Product newProduct) {
		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {
			Product prod = product.get();
			prod.setDescription(newProduct.getDescription());
			prod.setPrice(newProduct.getPrice());
			prod.setTitle(newProduct.getTitle());

			prod = productRepository.save(prod);
			return ResponseEntity.ok().body(prod);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Long id) {
		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {
			productRepository.delete(product.get());
			return new ResponseEntity("Product has been deleted successfully.", HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}