package com.manager.retail;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binay Mishra
 *
 */
@RestController
@ControllerAdvice 
public class GlobalExceptionHandler {
	
	private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);
	
	
	/**
	 * @param e
	 * 
	 * Handles JSON format of message body null with http status code 400 bad request 
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)  
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		
	}
	
	/**
	 * @param e
	 * @return
	 * 
	 * Handles any server side exception with http status code 500 
	 */
	@ExceptionHandler(value = Exception.class)  
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    public String handleException(Exception e){
		LOG.error(e.getMessage(), e);
		return "OOps ! somthing wrong please check the log for details";
	}   

}
