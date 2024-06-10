package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import lombok.Data;
import lombok.Getter;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;

import java.util.List;

@Data
public class UsuarioListagemGeralDto {

    private String nome;

    private String rua;

    private String numero;

    private String bairro;

    private Double precoMin;

    private List<Estilo> estilos;

}
