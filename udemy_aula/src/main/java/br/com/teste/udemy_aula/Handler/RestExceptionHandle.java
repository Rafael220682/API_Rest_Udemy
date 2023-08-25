package br.com.teste.udemy_aula.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.teste.udemy_aula.model.error.ErrorMessage;
import br.com.teste.udemy_aula.model.exception.ResourceInternalServerError;
import br.com.teste.udemy_aula.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandle {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){

        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceInternalServerError.class)
    public ResponseEntity<?> handleResourceInternalServerError(ResourceInternalServerError ex){
        
            ErrorMessage error = new ErrorMessage("Internal Server Error", 
                        HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
}
