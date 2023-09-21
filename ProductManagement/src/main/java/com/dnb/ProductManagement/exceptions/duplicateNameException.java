package com.dnb.ProductManagement.exceptions;

public class duplicateNameException extends Exception{
	public duplicateNameException(String msg)
	{
		super(msg);
	}
	@Override
	public String toString() {
		return super.toString()+super.getMessage();
	}
}
