package com.dnb.ProductManagement.exceptions;

public class ValidityNotOverException extends Exception{
	public ValidityNotOverException(String msg)
	{
		super(msg);
	}
	@Override
	public String toString() {
		return super.toString()+super.getMessage();
}
}
