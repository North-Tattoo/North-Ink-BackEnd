package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstudioListagemDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double precoMin;
    private Double anosExperiencia;
}
