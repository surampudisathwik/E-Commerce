package com.dnb.ProductManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.ProductManagement.dto.Product;
import com.dnb.ProductManagement.exceptions.IdNotFoundException;
import com.dnb.ProductManagement.exceptions.NameNotFoundException;
import com.dnb.ProductManagement.exceptions.NoProductFoundException;
import com.dnb.ProductManagement.exceptions.duplicateNameException;
import com.dnb.ProductManagement.repo.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{
@Autowired
ProductRepository productRepository;
	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProductById(String productId) throws IdNotFoundException {
		Optional<Product> optional = productRepository.findById(productId);
		if(optional.isPresent()) return productRepository.findById(productId);
		else throw new IdNotFoundException("ProductId Not Found");
	}

	@Override
	public boolean deleteProduct(String productId) throws IdNotFoundException {
		
		if(productRepository.existsById(productId))
		{
			 productRepository.deleteById(productId);
		}
		else throw new IdNotFoundException("productId Not Found!!");
		if(productRepository.existsById(productId)) return false;
		else return true;
	}

	@Override
	public Iterable<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getByname(String productName) throws NoProductFoundException {
		// TODO Auto-generated method stub
		if(productRepository.findByName(productName) != null) return productRepository.findByName(productName);
		else throw new NoProductFoundException("No product with the given name Exists");
	}
	@Override
	public Optional<Product> updateExistingProduct(Product product, String productId) throws NameNotFoundException {
		Optional<Product> product2 = productRepository.findByName(product.getName());
		
		if(product2.isPresent() && product2.get().getProductId().equals(productId)){
		
				productRepository.save(product);
		
					return Optional.of(product);
		} else if(product2.isPresent()) {
			throw new NameNotFoundException("Product Name Must be unique");
		}
		else {
			productRepository.save(product);
			
			return Optional.of(product);
			
			
		}
		}

	}



