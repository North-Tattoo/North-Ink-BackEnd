package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import lombok.Data;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;

import java.util.List;

@Data

public class UsuarioListagemPortfolioDto {

    private Long id;
    private String fotoPerfil;
    private String nome;
    private String sobrenome;
    private Double valorMin;
    private String anosExperiencia;
    private String resumo;
    private String telefone;
    private String instagram;
    private List<Estilo> estilos;
    private Estudio estudio;

    @Data
    public static class EstiloDto{
        private Integer id;
        private String nome;
    }

    @Data
    public static class estudioDto{

    }

}

