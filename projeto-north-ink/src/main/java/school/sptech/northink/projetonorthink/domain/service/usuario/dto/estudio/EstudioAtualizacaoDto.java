package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstudioAtualizacaoDto {


    private String nome;
    private String email;
    private String descricao;
    private Double precoMin;
    private Double anosExperiencia;
}
