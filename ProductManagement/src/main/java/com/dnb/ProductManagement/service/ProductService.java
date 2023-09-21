package com.dnb.ProductManagement.service;

import java.util.Optional;

import com.dnb.ProductManagement.dto.Product;
import com.dnb.ProductManagement.exceptions.IdNotFoundException;
import com.dnb.ProductManagement.exceptions.NameNotFoundException;
import com.dnb.ProductManagement.exceptions.NoProductFoundException;
import com.dnb.ProductManagement.exceptions.duplicateNameException;

public interface ProductService {
	public Product createProduct(Product product) ;
    public Optional<Product> getProductById(String productId) throws IdNotFoundException; 
    public boolean deleteProduct(String productId) throws IdNotFoundException ;
    public Iterable<Product> getAllProduct();
    public Optional<Product> getByname(String productName) throws NoProductFoundException;
	Optional<Product> updateExistingProduct(Product product, String productId) throws NameNotFoundException ;
}
