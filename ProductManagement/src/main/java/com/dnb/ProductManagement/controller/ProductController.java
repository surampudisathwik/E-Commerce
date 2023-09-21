package com.dnb.ProductManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.ProductManagement.dto.Product;
import com.dnb.ProductManagement.exceptions.IdNotFoundException;
import com.dnb.ProductManagement.exceptions.NameNotFoundException;
import com.dnb.ProductManagement.exceptions.NoProductFoundException;
import com.dnb.ProductManagement.exceptions.ValidityNotOverException;
import com.dnb.ProductManagement.exceptions.duplicateNameException;
import com.dnb.ProductManagement.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	ProductService productService;

//By adding create in the end of the link it creates an new product entity object and saves it in the database
//The @Valid annotation will check for all the validation annotations mentioned in the entity object 
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product)
			throws IdNotFoundException, duplicateNameException, NoProductFoundException {
		// By using getByname method we see if the name is unique or not if the name is
		// fetched from previous entity objects then it throws the duplicate name
		// exception

		Optional<Product> optional = productService.getByname(product.getName());

		if (optional.isEmpty())
			return ResponseEntity.ok(productService.createProduct(product));

		else

			throw new duplicateNameException("Same Product already exists");
	}

//By entering the productId in the link field it retrieves the product object accordingly
	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		// Using getProductById we fetch and confirm if the id exists or not
		Optional<Product> optional = productService.getProductById(productId);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return null;
	}

//By adding getall in the end of the path link it retrieves all the available products from database

	@GetMapping("/getall")
	public ResponseEntity<?> getAllProduct() {
		Iterable<Product> listofProd = productService.getAllProduct();
		return ResponseEntity.ok(listofProd);

	}

//A condition is added that if the product expiry date is equal to 2023 it will get deleted when delete is hit
	@DeleteMapping("/del/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId)
			throws IdNotFoundException, ValidityNotOverException {
		// Using getProductById we fetch and confirm if the id exists or not
		Optional<Product> optional = productService.getProductById(productId);
		if (optional.isPresent() && productService.getProductById(productId).get().getExpiryDate().getYear() <= 2023) {
			productService.deleteProduct(productId);
			return ResponseEntity.ok("Deleted");
		}
		throw new ValidityNotOverException("Validty is not over to delete the product");
	}

	@PutMapping("/update/{productId}")

	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("productId") String productId)
			throws NameNotFoundException, IdNotFoundException {

		Optional<Product> OptionalProduct;

		if (productService.getProductById(productId) != null) {

			product.setProductId(productId);

			OptionalProduct = productService.updateExistingProduct(product, productId);
			return ResponseEntity.ok(OptionalProduct);

		}

		else

			throw new IdNotFoundException("Product Id Doesn't exist");

	}

}
