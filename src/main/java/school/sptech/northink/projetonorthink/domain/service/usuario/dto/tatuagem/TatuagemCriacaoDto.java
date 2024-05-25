package school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class TatuagemCriacaoDto {

    @NotNull
    private String estilo;

    @NotNull
    private Double preco;

    private Usuario fkUsuario;

}
