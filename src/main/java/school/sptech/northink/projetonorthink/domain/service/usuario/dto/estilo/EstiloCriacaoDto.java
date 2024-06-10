package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class EstiloCriacaoDto {

    @NotNull
    private String nome;

}
