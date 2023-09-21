package com.dnb.ProductManagement.exceptions;

public class NameNotFoundException extends Exception{

		public NameNotFoundException(String msg)
		{
			super(msg);
		}
		@Override
		public String toString() {
			return super.toString()+super.getMessage();
	}

}
