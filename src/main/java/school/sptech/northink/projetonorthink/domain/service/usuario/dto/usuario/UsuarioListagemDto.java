package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;

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
    private Double precoMinimo;
    private String instagram;
    private List<EstiloListagemDto> estilos;
    private List<Tatuagem> tatuagens;

    @Data
    public static class EstiloDto{
        private Long id;
        private String nome;
    }

    @Data
    public static class TatuagemDto{
        private Long id;
        private String estilo;
        private Double preco;
        private String Descricao;
    }
}
