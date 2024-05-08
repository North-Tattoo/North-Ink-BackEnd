package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EstudioCriacaoDto {

    @NotNull
    private String nome;

    @Email
    @NotNull
    private String email;

    @Size(max = 500)
    private String descricao;

    @NotNull
    private Double precoMin;

    @NotNull
    private Double anosExperiencia;


}
