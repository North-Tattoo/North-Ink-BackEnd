package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioListagemDto {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String celular;
    private String email;
    private String senha;
    private String resumo;
    private String anosExperiencia;
    private String precoMinimo;
    private String instagram;
    private List<String> estilos;

    @Data
    public static class EstiloDto{
        private Long id;
        private String nome;
    }
}
