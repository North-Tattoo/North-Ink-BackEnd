package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioAtualizacaoDto {

    // metodo com os atributos que serão atualizados, exceto o ID
    private String nome;
    private String sobrenome;
    private String cpf;
    private String celular;
    private String email;
    private String senha;
    private String sobreMim;
    private List<String> estilos;

}
