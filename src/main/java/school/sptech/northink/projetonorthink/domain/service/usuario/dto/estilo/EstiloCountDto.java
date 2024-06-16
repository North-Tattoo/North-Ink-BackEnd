package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class EstiloCountDto {
    private String nome;
    private Long count;

}
