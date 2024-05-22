package school.sptech.northink.projetonorthink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String message) {
        super(String.format("%S Não encontrado!", message));
    }
}

/* 1. cria uma classe com o nome do erro que será tratado e EXCEPTION NO FINAL
* 2. Adicionar @ResponseStatus(HttpStatus. (inserir o tipo de erro a ser tratado) <- ) <
* 3. Adicionar o extends RuntimeException depois do nome da classe
* 4. apertar alt + insert override methods e pegar o segundo item da lista (run time exception message)
* 5. ai dentro do super adicionar a mensagem que será gerada, exemplo: super(String.format("%S Não encontrado!", message));  */