package br.com.teste.udemy_aula.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceInternalServerError extends RuntimeException {
    
    public ResourceInternalServerError(String mensagem){
        super(mensagem);
    }
}
