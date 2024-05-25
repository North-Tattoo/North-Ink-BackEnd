package school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TatuagemCriacaoDto {

    @NotNull
    private String estilo;

    @NotNull
    private Double preco;


}
