package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoCriacaoDto {

    @NotNull
    private String logradouro;
    @NotNull
    private Integer numero;
    @NotNull
    @Size(max = 30)
    private String complemento;

    @NotNull
    private String cep;

}
