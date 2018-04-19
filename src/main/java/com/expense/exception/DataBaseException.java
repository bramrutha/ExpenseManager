package com.expense.exception;

import java.sql.SQLException;

public class DataBaseException extends SQLException {

	public DataBaseException(String message, int errorCode) {
		super(message + " " + errorCode);
	}

}
