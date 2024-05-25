package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoListagemDto {

    // endereco (rua, numero e bairro)
    // nessa listagem deve retornar a rua do tatuador

    private String rua;
    private Integer numero;
    private String bairro;

}
