package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoCriacaoDto {

    @NotNull
    private String rua;

    @NotNull
    private Integer numero;

    @NotNull
    @Size(max = 30)
    private String complemento;

    @NotNull
    @Min(value = 8)
    private String cep;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    private Long fkEstudio;
}
