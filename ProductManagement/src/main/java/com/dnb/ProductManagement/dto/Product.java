package com.dnb.ProductManagement.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Range;

import com.dnb.ProductManagement.utils.customProductIdGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="product_seq")
//creates instance of generator and calls generator
@GenericGenerator(name = "product_seq" , strategy= "com.dnb.ProductManagement.utils.customProductIdGenerator", 
	parameters = {@Parameter(name=customProductIdGenerator.INCREMENT_PARAM,value="5"),
			@Parameter(name= customProductIdGenerator.VALUE_PREFIX_PARAMETER, value="PROD_"), 
			@Parameter(name=customProductIdGenerator.NUMBER_FORMAT_PARAMETER, value="%05d")} )
private String productId;
@NotBlank
private String description;
@Min(value=0)
private int price;
@NotBlank
private String name;
private LocalDate expiryDate;
private String category;
}
