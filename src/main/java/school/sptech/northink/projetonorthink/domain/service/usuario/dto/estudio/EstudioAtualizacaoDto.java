package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstudioAtualizacaoDto {

    private String nome;
    private String descricao;
    private String CEP;
    private String rua;
    private String bairro;
    private String complemento;
    private Integer numero;
    private String estado;
    private String cidade;
}
