package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoListagemDto {

    // endereco (rua, numero e bairro)
    // nessa listagem deve retornar a rua do tatuador

    private Long id;
    private String rua;
    private Integer numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;

}
