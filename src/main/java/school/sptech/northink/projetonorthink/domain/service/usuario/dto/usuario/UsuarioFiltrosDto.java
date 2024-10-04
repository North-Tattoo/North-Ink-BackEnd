package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import lombok.Data;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;

import java.util.List;

@Data
public class UsuarioFiltrosDto {

    private String nome;
    private Double precoMin;
    private List<Estilo> estilos;
    private String cidade;

}
