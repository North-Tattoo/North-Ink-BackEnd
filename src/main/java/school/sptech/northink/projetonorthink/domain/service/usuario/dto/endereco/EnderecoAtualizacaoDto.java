package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoAtualizacaoDto {

    private String CEP;

    private String rua;

    private String bairro;

    private String complemento;

    private Integer numero;

    private String estado;

    private String cidade;


}
