package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;


@Getter
@Setter
@NoArgsConstructor
public class EstudioCriacaoDto {

    @NotNull
    private String nome;

    @Size(max = 500)
    private String descricao;

    private Usuario fkUsuario;


}
