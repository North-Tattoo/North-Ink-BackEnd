package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class EstiloListagemDto {

    private String nome;
    private Usuario fkUsuario;
}
