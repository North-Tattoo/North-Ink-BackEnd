package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class EstudioListagemDto {


    private String nome;
    private String descricao;
    private Usuario fkUsuario;

}
