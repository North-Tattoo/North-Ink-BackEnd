package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import lombok.Data;
import lombok.Getter;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;

import java.util.List;

@Data
public class UsuarioListagemGeralDto {

    private Long id;
    private String nome;
    private String sobrenome;
    private Double precoMin;
    private List<Estilo> estilos;
    private Estudio estudio;

    @Data
    public static class EstiloDto{
        private Long id;
        private String nome;
    }

    @Data
    public static class EstudioDto{
        public static class EnderecoDto{
            private String rua;

            private String numero;

            private String bairro;
        }
    }

}
