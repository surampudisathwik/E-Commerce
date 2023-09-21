package com.dnb.ProductManagement.exceptions;

public class NoProductFoundException extends Exception {
	public NoProductFoundException(String msg)
	{
		super(msg);
	}
	@Override
	public String toString() {
		return super.toString()+super.getMessage();
}
}