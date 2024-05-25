package school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class TatuagemListagemDto {


    private String estilo;
    private Double preco;
    private Usuario fkUsuario;
}

