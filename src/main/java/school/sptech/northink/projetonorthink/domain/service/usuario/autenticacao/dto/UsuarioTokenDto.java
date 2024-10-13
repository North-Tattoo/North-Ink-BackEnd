package school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto;

import lombok.Data;

@Data
public class UsuarioTokenDto {

    private Long userId;
    private String nome;
    private String sobrenome; // Novo campo
    private String cpf; // Novo campo
    private String email;
    private Boolean assinante;
    private String token;
}
