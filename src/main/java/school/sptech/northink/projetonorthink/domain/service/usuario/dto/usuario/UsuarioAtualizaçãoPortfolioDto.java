package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import lombok.Getter;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;

import java.util.List;

@Getter
@Setter
public class UsuarioAtualizaçãoPortfolioDto {

    private Long id;
    private Double precoMin;
    private String anosExperiencia;
    private String resumo;
    private List<Estilo> estilos;
    private String instagram;

    // @marccelo vai ser preciso ver a logica das imagens pq nessa tela pe a atualização do portfolio, é a mesma tela
    // do upload de imagens
}
