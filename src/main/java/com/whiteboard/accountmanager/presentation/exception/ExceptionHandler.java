package com.whiteboard.accountmanager.presentation.exception;

import com.codegen.rest.model.NewAccountResponsePresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionHandler {
    public static ResponseEntity<NewAccountResponsePresentation> handle(Exception e) {


        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
