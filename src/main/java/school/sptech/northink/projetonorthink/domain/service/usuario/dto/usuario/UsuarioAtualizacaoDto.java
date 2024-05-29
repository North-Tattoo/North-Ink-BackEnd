package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioAtualizacaoDto {


    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String novaSenha;
    private String celular;


}
