package com.x.jwof.foundation.filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x.jwof.domain.data.entity.APIError;
import com.x.jwof.domain.data.response.Response;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Response> exceptionHandler(Exception e) {
        log.info(e.getMessage(), e);
        
        APIError error = new APIError();
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.setMessage("Internal Server Error.");

        Response response = new Response();
        response.setSuccess(false);;
        response.setError(error);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
