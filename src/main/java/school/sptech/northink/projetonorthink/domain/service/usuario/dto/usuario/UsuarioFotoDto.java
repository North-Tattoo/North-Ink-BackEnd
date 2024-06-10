package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UsuarioFotoDto {
    private Long id;
    private String nome;
    private String fotoPerfil;
}
