package school.sptech.northink.projetonorthink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DadosInvalidosException extends RuntimeException{

    public DadosInvalidosException(String message) {
        super(String.format("%S Dados invalidos!", message));
    }
}
