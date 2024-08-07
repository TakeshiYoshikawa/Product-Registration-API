package com.example.springbootapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.springbootapi.repository.ProductRepository;

/**
* Service class for managing Product entities.
*/
@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	* Save a product.
	*
	* @param product the entity to save
	* @return the persisted entity
	*/
	public Product saveProduct(Product product) {
		try {
			return productRepository.save(product);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to save product: %s", e.getMessage()));
		}
	}

	/**
	* Get all the products.
	*
	* @return the list of entities
	*/
	public List<Product> getAllProducts() {
		try {
			return productRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to fetch all products: %s", e.getMessage()));
		}
	}

	/**
	* Get one product by ID.
	*
	* @param id the ID of the entity
	* @return the entity
	*/
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	/**
	* Update a product.
	*
	* @param id the ID of the entity
	* @param updatedProduct the updated entity
	* @return the updated entity
	*/
	public Product updateProduct(Long id, Product updatedProduct) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if (existingProduct.isPresent()) {
			Product product = existingProduct.get();
			product.setName(updatedProduct.getName());
			product.setPrice(updatedProduct.getPrice());
			product.setQuantity(updatedProduct.getQuantity());
			return productRepository.save(product);
		} else {
			throw new RuntimeException("Product not found");
		}
	}

	/**
	* Delete the product by ID.
	*
	* @param id the ID of the entity
	*/
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
