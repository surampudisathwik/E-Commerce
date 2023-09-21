package com.dnb.ProductManagement.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dnb.ProductManagement.exceptions.IdNotFoundException;
import com.dnb.ProductManagement.exceptions.NameNotFoundException;
import com.dnb.ProductManagement.exceptions.ValidityNotOverException;
import com.dnb.ProductManagement.exceptions.duplicateNameException;
//ControllerAdvice is used to handle the customs and here easily any required customizations can be made
@ControllerAdvice
public class productAdvice {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> IdNotFoundHandler (IdNotFoundException e){
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(duplicateNameException.class)
	public ResponseEntity<?> DuplicateNameFoundHandler (duplicateNameException e){
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<?> NameNotFoundHandler (NameNotFoundException e){
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidityNotOverException.class)
	public ResponseEntity<?> ValidityFoundHandler (ValidityNotOverException e){
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
